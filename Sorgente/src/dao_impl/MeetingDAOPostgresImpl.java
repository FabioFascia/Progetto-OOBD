package dao_impl;

import dao.MeetingDAO;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Sala;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class MeetingDAOPostgresImpl implements MeetingDAO {
	
	private Connection connection;
	private PreparedStatement insertMeetingFisicoPS; 
	private PreparedStatement insertMeetingTelematicoPS;
	private PreparedStatement updateMeetingFisicoPS;
	private PreparedStatement updateMeetingTelematicoPS;
	private PreparedStatement deleteMeetingFisicoPS;
	private PreparedStatement deleteMeetingTelematicoPS;
	private PreparedStatement getMeetingFisicoByAttributiPS;
	
	private PreparedStatement getMeetingTelematicoByAttributiPS;

	
	public  MeetingDAOPostgresImpl (Connection c) throws SQLException{
		
		connection = c;
		
		insertMeetingFisicoPS = connection.prepareStatement("CALL Insert_MeetingF(?, ?, ?, ?);");
		insertMeetingTelematicoPS = connection.prepareStatement("CALL Insert_MeetingT(?, ?, ?, ?, ?, ?);");
		
		updateMeetingFisicoPS = connection.prepareStatement("UPDATE MEETINGF SET Data = ?, OraI = ?, OraF = ?, CodSala = ? WHERE CodMF = ?;");
		updateMeetingTelematicoPS = connection.prepareStatement("UPDATE MEETINGT SET Data = ?, OraI = ?, Oraf = ?, Piattaforma = ?, NumLimite = ? WHERE CodMT = ?;");
		
		deleteMeetingFisicoPS = connection.prepareStatement("DELETE FROM MEETINGF WHERE CodMF = ?;");
		deleteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM MEETING WHERE CodMT = ?;");
		
		getMeetingFisicoByAttributiPS = connection.prepareStatement("SELECT * "
				                                                         +"FROM MEETINGF NATURAL JOIN SALA "
				                                                           +"WHERE (CodMF = ? OR ? = -1) AND "
				                                                            +"(? = '2000-01-01'::date OR Data::date = ?) AND "
				                                                              +"((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) AND "
				                                                              + "(? = '' OR SALA.Indirizzo = ?) ORDER BY CodMF; ");
		
		getMeetingTelematicoByAttributiPS = connection.prepareStatement("SELECT * "  
		                                                                         + "FROM MEETINGT "
		                                                                           + "WHERE (? = -1 OR CodMT = ?) AND "
		                                                                            + "(? = '2000-01-01'::date OR Data::date = ?) "
		                                                                              + "  AND((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) AND "
		                                                                                + "((Piattaforma ILIKE ?) AND (NumLimite = ? OR ? = -2)); ");
		   
	}		                                                                            
		                            
		                              
	
    public void insertMeetingFisico (Date Data, Time  OraI, Time OraF, int CodS) throws SQLException {
    	
    	insertMeetingFisicoPS.setDate(1, Data);
    	insertMeetingFisicoPS.setTime(2, OraI);
    	insertMeetingFisicoPS.setTime(3, OraF);
    	insertMeetingFisicoPS.setInt(4, CodS);
    	
    	insertMeetingFisicoPS.execute();
    	
    }
    
    public void insertMeetingTelematico (MeetingTelematico mt) throws SQLException{
    	
    	insertMeetingTelematicoPS.setDate(1, mt.getData());
    	insertMeetingTelematicoPS.setTime(2, mt.getOraI());
    	insertMeetingTelematicoPS.setTime(3, mt.getOraF());
    	insertMeetingTelematicoPS.setString(4, mt.getPiattaforma());
    	insertMeetingTelematicoPS.setInt(5, mt.getNumeroLimite());
    }

    public void updateMeetingFisico (MeetingFisico mf) throws SQLException{
    	
    	updateMeetingFisicoPS.setDate(1, mf.getData());
    	updateMeetingFisicoPS.setTime(2, mf.getOraI());
    	updateMeetingFisicoPS.setTime(3, mf.getOraF());
    	
    	updateMeetingFisicoPS.execute();
    	
    }
    
    public void updateMeetingTelematico (Date Data, Time OraI, Time OraF, String Piattaforma, int NumLimite) throws SQLException{
    	
    	updateMeetingTelematicoPS.setDate(1, Data);
    	updateMeetingTelematicoPS.setTime(2, OraI);
    	updateMeetingTelematicoPS.setTime(3, OraF);
    	updateMeetingTelematicoPS.setString(4, Piattaforma);
    	updateMeetingTelematicoPS.setInt(5, NumLimite);
    	
    	updateMeetingTelematicoPS.execute();
    	
    }
    

    public void deleteMeetingFisico (int CodMF) throws SQLException {
    	
    	deleteMeetingFisicoPS.setInt(1, CodMF);
    	deleteMeetingFisicoPS.execute();
    }
    
    public void deleteMeetingTelematico (int CodMT) throws SQLException {
    	
    	deleteMeetingTelematicoPS.setInt(1, CodMT);
    	deleteMeetingTelematicoPS.execute();
    }
    
  public ArrayList<MeetingFisico> getMeetingFisicoByAttributi (String CodMF, String Data, String OraInizio, String OraFine, String CodSala) throws SQLException {
    	
	    Date data; Time oraInizio, oraFine; int codice;
	    
    	
	    if(CodMF.isBlank())
	    	codice = -1;
	    else
	    	codice = Integer.parseInt(CodMF);
	   
        
	    data = Date.valueOf(Data);
    	oraInizio = Time.valueOf(OraInizio);
    	oraFine = Time.valueOf(OraFine);
    	
    	getMeetingFisicoByAttributiPS.setInt(1, codice);
    	getMeetingFisicoByAttributiPS.setInt(2, codice);
    	getMeetingFisicoByAttributiPS.setDate(3, data);
    	getMeetingFisicoByAttributiPS.setDate(4, data);
    	getMeetingFisicoByAttributiPS.setTime(5, oraFine);
    	getMeetingFisicoByAttributiPS.setTime(6, oraInizio);
    	getMeetingFisicoByAttributiPS.setTime(7, oraInizio);
    	getMeetingFisicoByAttributiPS.setTime(8, oraFine);
    	getMeetingFisicoByAttributiPS.setString(9, CodSala);
    	getMeetingFisicoByAttributiPS.setString(10, CodSala);
    	
    	
    	
    	ResultSet rs = getMeetingFisicoByAttributiPS.executeQuery();
    	
    	
    	ArrayList<MeetingFisico> lista = new ArrayList<MeetingFisico>();
    	
    	while(rs.next()) {
    		
    		MeetingFisico mf = new MeetingFisico();
    		Sala s = new Sala();
    		
    		mf.setCodMeeting(rs.getInt("CodMF"));
    		mf.setData(rs.getDate("Data"));
    		mf.setOraI(rs.getTime("OraI"));
    		mf.setOraF(rs.getTime("OraF"));
    		s.setIndirizzo(rs.getString("Indirizzo"));
            mf.setSalaRiunioni(s);
    		
    		
    		lista.add(mf);
    		
    	}
		return lista;
    	
   }
    
 public ArrayList<MeetingTelematico> getMeetingTelematicoByAttributi (String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo) throws SQLException {
    	
    	int codice, numeroMassimo; Date data; Time oraInizio, oraFine; 
  
    	if(CodMT.isBlank()) 
    		codice = -1;
    	else
    		codice = Integer.parseInt(CodMT);
    	
    	if(NumMassimo.isBlank())
    		numeroMassimo = -2;
    	else
    		numeroMassimo = Integer.parseInt(NumMassimo);
    
    	data = Date.valueOf(Data);
    	oraInizio = Time.valueOf(OraInizio);
    	oraFine = Time.valueOf(OraFine);
 
    	getMeetingTelematicoByAttributiPS.setInt(1, codice);
    	getMeetingTelematicoByAttributiPS.setInt(2, codice);
    	getMeetingTelematicoByAttributiPS.setDate(3, data);
    	getMeetingTelematicoByAttributiPS.setDate(4, data);
    	getMeetingTelematicoByAttributiPS.setTime(5, oraFine);
    	getMeetingTelematicoByAttributiPS.setTime(6, oraInizio);
    	getMeetingTelematicoByAttributiPS.setTime(7, oraInizio);
    	getMeetingTelematicoByAttributiPS.setTime(8, oraFine);
    	getMeetingTelematicoByAttributiPS.setString(9, "%" + Piattaforma + "%");
    	getMeetingTelematicoByAttributiPS.setInt(10, numeroMassimo);
    	getMeetingTelematicoByAttributiPS.setInt(11, numeroMassimo);
    	
    	
    	ResultSet rs = getMeetingTelematicoByAttributiPS.executeQuery();
    	
    	ArrayList<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
    	
    	while(rs.next()) {
    		MeetingTelematico m = new MeetingTelematico();
    		
    		
    		m.setCodMeeting(rs.getInt("CodMT"));
    		m.setData(rs.getDate("Data"));
    		m.setOraI(rs.getTime("OraI"));
    		m.setOraF(rs.getTime("OraF"));
    		m.setPiattaforma(rs.getString("Piattaforma"));
    		m.setNumeroLimite(rs.getInt("NumLimite"));
    		
    		lista.add(m);
    		
    	}
		return lista;
 }
}





