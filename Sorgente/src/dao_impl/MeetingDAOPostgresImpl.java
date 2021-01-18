package dao_impl;

import dao.MeetingDAO;
import entità.Dipendente;
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
	private PreparedStatement deletePartecipanteMeetingFisicoPS;
	private PreparedStatement deletePartecipanteMeetingTelematicoPS;
	
	private PreparedStatement getMeetingFisicoByAttributiPS;
	private PreparedStatement getMeetingTelematicoByAttributiPS;
	private PreparedStatement getSalaMeetingFisicoPS;

	
	public  MeetingDAOPostgresImpl (Connection c) throws SQLException{
		
		connection = c;
		
		insertMeetingFisicoPS = connection.prepareStatement("CALL Insert_MeetingF(?, ?, ?, ?, ?);");
		insertMeetingTelematicoPS = connection.prepareStatement("CALL Insert_MeetingT(?, ?, ?, ?, ?, ?);");
		
		updateMeetingFisicoPS = connection.prepareStatement("UPDATE MEETINGF SET Data = ?, OraI = ?, OraF = ?, CodSala = ? WHERE CodMF = ?;");
		updateMeetingTelematicoPS = connection.prepareStatement("UPDATE MEETINGT SET Data = ?, OraI = ?, Oraf = ?, Piattaforma = ?, NumLimite = ? WHERE CodMT = ?;");
		
		deleteMeetingFisicoPS = connection.prepareStatement("DELETE FROM MEETINGF WHERE CodMF = ?;");
		deleteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM MEETING WHERE CodMT = ?;");
		
		deletePartecipanteMeetingFisicoPS = connection.prepareStatement("DELETE FROM PARTECIPAMF WHERE CodMF = ? AND CodF = ?;");
		deletePartecipanteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM PARTECIPAMT WHERE CodMT = ? AND CodF = ?;");
		
		getMeetingFisicoByAttributiPS = connection.prepareStatement("SELECT * "
				                                                   +"FROM MEETINGF "
				                                                   +"WHERE (CodMF = ? OR ? = -1) AND "
				                                                   		+"(? = '2000-01-01'::date OR Data::date = ?) AND "
				                                                   		+"((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) "
				                                                   + "ORDER BY CodMF;");
		
		getMeetingTelematicoByAttributiPS = connection.prepareStatement("SELECT * "  
		                                                                         + "FROM MEETINGT "
		                                                                           + "WHERE (? = -1 OR CodMT = ?) AND "
		                                                                            + "(? = '2000-01-01'::date OR Data::date = ?) "
		                                                                              + "  AND((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) AND "
		                                                                                + "((Piattaforma ILIKE ?) AND (NumLimite = ? OR ? = -2)); ");
		
		getSalaMeetingFisicoPS = connection.prepareStatement("SELECT * FROM SALA WHERE CodSala = ?");
		   
	}		                                                                            
		                            
		                              
	
    public void insertMeetingFisico (MeetingFisico mf) throws SQLException {
    	
    	insertMeetingFisicoPS.setInt(1, mf.getProgettoMeeting().getCodice());
    	insertMeetingFisicoPS.setDate(2, mf.getData());
    	insertMeetingFisicoPS.setTime(3, mf.getOraI());
    	insertMeetingFisicoPS.setTime(4, mf.getOraF());
    	insertMeetingFisicoPS.setInt(5, mf.getSalaRiunioni().getCodice());
    	
    	insertMeetingFisicoPS.execute();
    }
    
    public void insertMeetingTelematico (MeetingTelematico mt) throws SQLException {
    	
    	insertMeetingTelematicoPS.setInt(1, mt.getProgettoMeeting().getCodice());
    	insertMeetingTelematicoPS.setDate(2, mt.getData());
    	insertMeetingTelematicoPS.setTime(3, mt.getOraI());
    	insertMeetingTelematicoPS.setTime(4, mt.getOraF());
    	insertMeetingTelematicoPS.setString(5, mt.getPiattaforma());
    	insertMeetingTelematicoPS.setInt(6, mt.getNumeroLimite());
    	
    	insertMeetingTelematicoPS.execute();
    }
    
    public void deleteMeetingFisico (MeetingFisico mf) throws SQLException {
    	
    	deleteMeetingFisicoPS.setInt(1, mf.getCodice());
    	deleteMeetingFisicoPS.execute();
    }
    
    public void deleteMeetingTelematico (MeetingTelematico mt) throws SQLException {
    	
    	deleteMeetingTelematicoPS.setInt(1, mt.getCodice());
    	deleteMeetingTelematicoPS.execute();
    }

    public void updateMeetingFisico (MeetingFisico mf) throws SQLException {
    	
    	updateMeetingFisicoPS.setDate(1, mf.getData());
    	updateMeetingFisicoPS.setTime(2, mf.getOraI());
    	updateMeetingFisicoPS.setTime(3, mf.getOraF());
    	updateMeetingFisicoPS.setInt(4, mf.getSalaRiunioni().getCodice());
    	updateMeetingFisicoPS.setInt(5, mf.getCodice());
    	
    	updateMeetingFisicoPS.execute();
    	
    }
    
    public void updateMeetingTelematico (MeetingTelematico mt) throws SQLException {
    	
    	updateMeetingTelematicoPS.setDate(1, mt.getData());
    	updateMeetingTelematicoPS.setTime(2, mt.getOraI());
    	updateMeetingTelematicoPS.setTime(3, mt.getOraF());
    	updateMeetingTelematicoPS.setString(4, mt.getPiattaforma());
    	updateMeetingTelematicoPS.setInt(5, mt.getNumeroLimite());
    	updateMeetingTelematicoPS.setInt(6, mt.getCodice());
    	
    	updateMeetingTelematicoPS.execute();
    	
    }
    
    public void deletePartecipanteMeeting (Meeting m, Dipendente d) throws SQLException {
    	
    	if(Meeting.class.equals(MeetingFisico.class)) {
    		deletePartecipanteMeetingFisicoPS.setInt(1, m.getCodice());
    		deletePartecipanteMeetingFisicoPS.setString(2, d.getCodF());
    		
    		deletePartecipanteMeetingFisicoPS.execute();
    	}
    	else {
    		deletePartecipanteMeetingTelematicoPS.setInt(1, m.getCodice());
    		deletePartecipanteMeetingTelematicoPS.setString(2, d.getCodF());
    		
    		deletePartecipanteMeetingTelematicoPS.execute();
    	}
    }
    

    
    public ArrayList<MeetingFisico> getMeetingFisicoByAttributi (String CodMF, String Data, String OraInizio, String OraFine) throws SQLException {
    	
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
    	
    	
    	
    	ResultSet rs = getMeetingFisicoByAttributiPS.executeQuery();
    	
    	
    	ArrayList<MeetingFisico> lista = new ArrayList<MeetingFisico>();
    	
    	while(rs.next()) {
    		
    		MeetingFisico mf = new MeetingFisico();
    		Sala s = new Sala();
    		
    		mf.setCodice(rs.getInt("CodMF"));
    		mf.setData(rs.getDate("Data"));
    		mf.setOraI(rs.getTime("OraI"));
    		mf.setOraF(rs.getTime("OraF"));
            mf.setSalaRiunioni(getSalaMeetingFisico(rs.getInt("CodSala")));
    		
    		
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
    		
    		
    		m.setCodice(rs.getInt("CodMT"));
    		m.setData(rs.getDate("Data"));
    		m.setOraI(rs.getTime("OraI"));
    		m.setOraF(rs.getTime("OraF"));
    		m.setPiattaforma(rs.getString("Piattaforma"));
    		m.setNumeroLimite(rs.getInt("NumLimite"));
    		
    		lista.add(m);
    		
    	}
		return lista;
    }

    public Sala getSalaMeetingFisico(int codSala) throws SQLException {
		 
		 getSalaMeetingFisicoPS.setInt(1, codSala);
		 
		 ResultSet rs = getSalaMeetingFisicoPS.executeQuery();
		 
		 rs.next();
		 Sala s = new Sala();
		 s.setCodice(rs.getInt("CodSala"));
		 s.setCittà(rs.getString("Città"));
		 s.setProvincia(rs.getString("Provincia"));
		 s.setIndirizzo(rs.getString("Indirizzo"));
		 s.setNumeroCivico(rs.getInt("NumeroCivico"));
		 
		 return s;
	 }
}





