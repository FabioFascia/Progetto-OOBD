package dao_impl;

import dao.MeetingDAO;
import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Progetto;
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
	
	private PreparedStatement insertPartecipanteMeetingFisicoPS;
	private PreparedStatement insertPartecipanteMeetingTelematicoPS;
	private PreparedStatement deletePartecipanteMeetingFisicoPS;
	private PreparedStatement deletePartecipanteMeetingTelematicoPS;
	
	private PreparedStatement getMeetingFisicoByAttributiPS;
	private PreparedStatement getMeetingFisicoByProgettiPS;
	private PreparedStatement getMeetingFisicoBySalaPS;
	private PreparedStatement getMeetingTelematicoByAttributiPS;
	private PreparedStatement getMeetingTelematicoByProgettiPS;
	
	private PreparedStatement getSalaMeetingFisicoPS;
	private PreparedStatement getProgettoMeetingPS;
	private PreparedStatement getPartecipantiMeetingFisicoPS;
	private PreparedStatement getPartecipantiMeetingTelematicoPS;

	
	public  MeetingDAOPostgresImpl (Connection c) throws SQLException{
		
		connection = c;
		
		insertMeetingFisicoPS = connection.prepareStatement("CALL Insert_MeetingF(?, ?, ?, ?, ?);");
		deleteMeetingFisicoPS = connection.prepareStatement("DELETE FROM MEETINGF WHERE CodMF = ?;");
		updateMeetingFisicoPS = connection.prepareStatement("UPDATE MEETINGF SET CodP = ?, Data = ?, OraI = ?, OraF = ?, CodSala = ? WHERE CodMF = ?;");
		
		insertMeetingTelematicoPS = connection.prepareStatement("CALL Insert_MeetingT(?, ?, ?, ?, ?, ?);");
		deleteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM MEETINGT WHERE CodMT = ?;");
		updateMeetingTelematicoPS = connection.prepareStatement("UPDATE MEETINGT SET Data = ?, OraI = ?, Oraf = ?, Piattaforma = ?, NumLimite = ? WHERE CodMT = ?;");
		
		insertPartecipanteMeetingFisicoPS = connection.prepareStatement("INSERT INTO PARTECIPAMF(CodMF, CodF) VALUES (?, ?);");
		insertPartecipanteMeetingTelematicoPS = connection.prepareStatement("INSERT INTO PARTECIPAMT(CodMT, CodF) VALUES (?, ?);");
		deletePartecipanteMeetingFisicoPS = connection.prepareStatement("DELETE FROM PARTECIPAMF WHERE CodMF = ? AND CodF = ?;");
		deletePartecipanteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM PARTECIPAMT WHERE CodMT = ? AND CodF = ?;");
		
		getMeetingFisicoByAttributiPS = connection.prepareStatement("SELECT * "
				                                                   +"FROM MEETINGF "
				                                                   +"WHERE (CodMF = ? OR ? = -1) AND "
				                                                   		+"(? = '2000-01-01'::date OR Data::date = ?) AND "
				                                                   		+"((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) "
				                                                   + "ORDER BY CodMF;");
		getMeetingFisicoByProgettiPS = connection.prepareStatement("SELECT MF.CodMF, MF.Data, MF.OraI, MF.OraF, MF.CodP, MF.CodSala "
																+ "FROM MEETINGF AS MF NATURAL JOIN "
																		+ "PROGETTO AS P "
																+ "WHERE (? = -1 OR P.CodP = ?) AND "
																		+ "P.Tipologia ILIKE ? AND "
																		+ "P.CodP IN (SELECT PR.CodP "
																					+ "FROM PROGETTO AS PR LEFT OUTER JOIN "
																							+ "AMBITO AS A ON "
																							+ "PR.CodP = A.CodP "
																					+ "WHERE (? IS NULL OR A.Keyword ILIKE ?));");
		
		getMeetingFisicoBySalaPS = connection.prepareStatement("SELECT MF.CodMF, MF.Data, MF.OraI, MF.OraF, MF.CodP, MF.CodSala "
															+ "FROM MEETINGF AS MF NATURAL JOIN "
																	+ "SALA AS S "
															+ "WHERE S.Città ILIKE ? AND "
																	+ "S.Provincia ILIKE ? AND "
																	+ "S.Indirizzo ILIKE ? AND "
																	+ "(? = -1 OR S.NumeroCivico = ?) AND "
																	+ "S.NumPosti BETWEEN ? AND ?;");
		
		getMeetingTelematicoByAttributiPS = connection.prepareStatement("SELECT * "  
		                                                                         + "FROM MEETINGT "
		                                                                           + "WHERE (? = -1 OR CodMT = ?) AND "
		                                                                            + "(? = '2000-01-01'::date OR Data::date = ?) AND "
		                                                                              + "((OraI BETWEEN ? AND ?) OR (OraF BETWEEN ? AND ?)) AND "
		                                                                                + "((Piattaforma ILIKE ?) AND (NumLimite = ? OR ? = -2)); ");
		
		getMeetingTelematicoByProgettiPS = connection.prepareStatement("SELECT MT.CodMT, MT.Data, MT.OraI, MT.OraF, MT.CodP, MT.Piattaforma, MT.NumLimite "
																	+ "FROM MEETINGT AS MT NATURAL JOIN "
																			+ "PROGETTO AS P "
																	+ "WHERE (? = -1 OR P.CodP = ?) AND "
																			+ "P.Tipologia ILIKE ? AND "
																			+ "P.CodP IN (SELECT PR.CodP "
																						+ "FROM PROGETTO AS PR LEFT OUTER JOIN "
																								+ "AMBITO AS A ON "
																								+ "PR.CodP = A.CodP "
																						+ "WHERE (? IS NULL OR A.Keyword ILIKE ?));");
		
		getSalaMeetingFisicoPS = connection.prepareStatement("SELECT * FROM SALA WHERE CodSala = ?;");
		getProgettoMeetingPS = connection.prepareStatement("SELECT * FROM PROGETTO WHERE CodP = ?;");
		getPartecipantiMeetingFisicoPS = connection.prepareStatement("SELECT D.CodF, D.Nome, D.Cognome, D.Salario, D.Valutazione "
																	+ "FROM PARTECIPAMF AS PMF NATURAL JOIN "
																			+ "DIPENDENTE AS D "
																	+ "WHERE PMF.CodMF = ? "
																	+ "ORDER BY D.Valutazione;");
		getPartecipantiMeetingTelematicoPS = connection.prepareStatement("SELECT D.CodF, D.Nome, D.Cognome, D.Salario, D.Valutazione "
																		+ "FROM PARTECIPAMT AS PMT NATURAL JOIN "
																			+ "DIPENDENTE AS D "
																		+ "WHERE PMT.CodMT = ?;");
		   
	}		                                                                            
		                            
		                              
	
    public void insertMeeting (MeetingFisico mf) throws SQLException {
    	
    	insertMeetingFisicoPS.setInt(1, mf.getProgettoMeeting().getCodice());
    	insertMeetingFisicoPS.setDate(2, mf.getData());
    	insertMeetingFisicoPS.setTime(3, mf.getOraInizio());
    	insertMeetingFisicoPS.setTime(4, mf.getOraFine());
    	insertMeetingFisicoPS.setInt(5, mf.getSalaRiunioni().getCodice());
    	
    	insertMeetingFisicoPS.execute();
    }
    
    public void insertMeeting (MeetingTelematico mt) throws SQLException {
    	
    	insertMeetingTelematicoPS.setInt(1, mt.getProgettoMeeting().getCodice());
    	insertMeetingTelematicoPS.setDate(2, mt.getData());
    	insertMeetingTelematicoPS.setTime(3, mt.getOraInizio());
    	insertMeetingTelematicoPS.setTime(4, mt.getOraFine());
    	insertMeetingTelematicoPS.setString(5, mt.getPiattaforma());
    	if(mt.getNumeroLimite() == -1)
    		insertMeetingTelematicoPS.setNull(6, java.sql.Types.INTEGER);
    	else
    		insertMeetingTelematicoPS.setInt(6, mt.getNumeroLimite());
    	
    	insertMeetingTelematicoPS.execute();
    }
    
    public void deleteMeeting (MeetingFisico mf) throws SQLException {
    	
    	deleteMeetingFisicoPS.setInt(1, mf.getCodice());
    	deleteMeetingFisicoPS.execute();
    }
    
    public void deleteMeeting (MeetingTelematico mt) throws SQLException {
    	
    	deleteMeetingTelematicoPS.setInt(1, mt.getCodice());
    	deleteMeetingTelematicoPS.execute();
    }

    public void updateMeeting (MeetingFisico mf) throws SQLException {
    	
    	updateMeetingFisicoPS.setInt(1, mf.getProgettoMeeting().getCodice());
    	updateMeetingFisicoPS.setDate(2, mf.getData());
    	updateMeetingFisicoPS.setTime(3, mf.getOraInizio());
    	updateMeetingFisicoPS.setTime(4, mf.getOraFine());
    	updateMeetingFisicoPS.setInt(5, mf.getSalaRiunioni().getCodice());
    	updateMeetingFisicoPS.setInt(6, mf.getCodice());
    	
    	updateMeetingFisicoPS.execute();
    }
    
    public void updateMeeting (MeetingTelematico mt) throws SQLException {
    	
    	updateMeetingTelematicoPS.setDate(1, mt.getData());
    	updateMeetingTelematicoPS.setTime(2, mt.getOraInizio());
    	updateMeetingTelematicoPS.setTime(3, mt.getOraFine());
    	updateMeetingTelematicoPS.setString(4, mt.getPiattaforma());
    	updateMeetingTelematicoPS.setInt(5, mt.getNumeroLimite());
    	updateMeetingTelematicoPS.setInt(6, mt.getCodice());
    	
    	updateMeetingTelematicoPS.execute();
    	
    }
    
    public void insertPartecipanteMeeting (MeetingFisico mf, Dipendente d) throws SQLException {
    	
    	insertPartecipanteMeetingFisicoPS.setInt(1, mf.getCodice());
    	insertPartecipanteMeetingFisicoPS.setString(2, d.getCodF());
    	
    	insertPartecipanteMeetingFisicoPS.execute();
    }
    public void deletePartecipanteMeeting (MeetingFisico mf, Dipendente d) throws SQLException {
    	
    	deletePartecipanteMeetingFisicoPS.setInt(1, mf.getCodice());
    	deletePartecipanteMeetingFisicoPS.setString(2, d.getCodF());
    	
    	deletePartecipanteMeetingFisicoPS.execute();
    }
    public void insertPartecipanteMeeting (MeetingTelematico mt, Dipendente d) throws SQLException {
    	
    	insertPartecipanteMeetingTelematicoPS.setInt(1, mt.getCodice());
    	insertPartecipanteMeetingTelematicoPS.setString(2, d.getCodF());
    	
    	insertPartecipanteMeetingTelematicoPS.execute();
    }
    public void deletePartecipanteMeeting (MeetingTelematico mt, Dipendente d) throws SQLException {
    	
    	deletePartecipanteMeetingTelematicoPS.setInt(1, mt.getCodice());
    	deletePartecipanteMeetingTelematicoPS.setString(2, d.getCodF());
    	
    	deletePartecipanteMeetingTelematicoPS.execute();
    }
    

    
    public ArrayList<MeetingFisico> getMeetingFisicoByAttributi (String CodMF, String Data, String OraInizio, String OraFine) throws SQLException {
    	
    	int codice;
    	Time oraInizio, oraFine;
	    Date data;
    	
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
    		
    		int cod = rs.getInt("CodMF");
    		Date date = rs.getDate("Data");
    		Time oraI = rs.getTime("OraI");
    		Time oraF = rs.getTime("OraF");
            Progetto p = getProgettoMeeting(rs.getInt("CodP"));
            Sala s = getSalaMeetingFisico(rs.getInt("CodSala"));
            
            MeetingFisico mf = new MeetingFisico(cod, date, oraI, oraF, p, s);
            
            mf.setPartecipanti(getPartecipantiMeeting(mf));
    		
    		lista.add(mf);
    	}
		return lista;
    	
    }
    
    public ArrayList<MeetingFisico> getMeetingFisicoByProgetti(String codp, String tipologia, String ambito) throws SQLException {
    	
    	int codice;
    	String amb;
    	
    	if(codp.isBlank())
    		codice = -1;
    	else
    		codice = Integer.parseInt(codp);
    	
    	if(ambito.isBlank())
    		amb = null;
    	else
    		amb = "%" + ambito + "%";
    	
    	getMeetingFisicoByProgettiPS.setInt(1, codice);
    	getMeetingFisicoByProgettiPS.setInt(2, codice);
    	getMeetingFisicoByProgettiPS.setString(3, "%" + tipologia + "%");
    	getMeetingFisicoByProgettiPS.setString(4, amb);
    	getMeetingFisicoByProgettiPS.setString(5, amb);
    	
    	ResultSet rs = getMeetingFisicoByProgettiPS.executeQuery();
    	
    	ArrayList<MeetingFisico> lista = new ArrayList<MeetingFisico>();
    	
    	while(rs.next()) {
    		
    		int cod = rs.getInt("CodMF");
    		Date date = rs.getDate("Data");
    		Time oraI = rs.getTime("OraI");
    		Time oraF = rs.getTime("OraF");
            Progetto p = getProgettoMeeting(rs.getInt("CodP"));
            Sala s = getSalaMeetingFisico(rs.getInt("CodSala"));
            
            MeetingFisico mf = new MeetingFisico(cod, date, oraI, oraF, p, s);
            
            mf.setPartecipanti(getPartecipantiMeeting(mf));
    		
    		lista.add(mf);
    	}
		return lista;
    }
    
	public ArrayList<MeetingFisico> getMeetingFisicoBySala(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException {
		
		int civico, minimo, massimo;
		
		if(numCivico.isBlank())
			civico = -1;
		else
			civico = Integer.parseInt(numCivico);
		
		if(minPosti.isBlank())
			minimo = Integer.MIN_VALUE;
		else
			minimo = Integer.parseInt(minPosti);
		
		if(maxPosti.isBlank())
			massimo = Integer.MAX_VALUE;
		else
			massimo = Integer.parseInt(maxPosti);
		
		getMeetingFisicoBySalaPS.setString(1, "%" + città + "%");
		getMeetingFisicoBySalaPS.setString(2, "%" + provincia + "%");
		getMeetingFisicoBySalaPS.setString(3, "%" + indirizzo + "%");
		getMeetingFisicoBySalaPS.setInt(4, civico);
		getMeetingFisicoBySalaPS.setInt(5, civico);
		getMeetingFisicoBySalaPS.setInt(6, minimo);
		getMeetingFisicoBySalaPS.setInt(7, massimo);
		
		ResultSet rs = getMeetingFisicoBySalaPS.executeQuery();
		
    	ArrayList<MeetingFisico> lista = new ArrayList<MeetingFisico>();
    	
    	while(rs.next()) {
    		
    		int cod = rs.getInt("CodMF");
    		Date date = rs.getDate("Data");
    		Time oraI = rs.getTime("OraI");
    		Time oraF = rs.getTime("OraF");
            Progetto p = getProgettoMeeting(rs.getInt("CodP"));
            Sala s = getSalaMeetingFisico(rs.getInt("CodSala"));
            
            MeetingFisico mf = new MeetingFisico(cod, date, oraI, oraF, p, s);
            
            mf.setPartecipanti(getPartecipantiMeeting(mf));
    		
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
    		
    		int cod = rs.getInt("CodMF");
    		Date date = rs.getDate("Data");
    		Time oraI = rs.getTime("OraI");
    		Time oraF = rs.getTime("OraF");
            Progetto p = getProgettoMeeting(rs.getInt("CodP"));
            String piattaforma = rs.getString("Piattaforma");
            int numLimite = rs.getInt("NumLimite");
            
            MeetingTelematico mt = new MeetingTelematico(cod, date, oraI, oraF, p, piattaforma, numLimite);
            
            mt.setPartecipanti(getPartecipantiMeeting(mt));
    		
    		lista.add(mt);
    		
    	}
		return lista;
    }
    
    public ArrayList<MeetingTelematico> getMeetingTelematicoByProgetti(String codp, String tipologia, String ambito) throws SQLException {
    	
    	int codice;
    	String amb;
    	
    	if(codp.isBlank())
    		codice = -1;
    	else
    		codice = Integer.parseInt(codp);
    	
    	if(ambito.isBlank())
    		amb = null;
    	else
    		amb = "%" + ambito + "%";
    	
    	getMeetingTelematicoByProgettiPS.setInt(1, codice);
    	getMeetingTelematicoByProgettiPS.setInt(2, codice);
    	getMeetingTelematicoByProgettiPS.setString(3, "%" + tipologia + "%");
    	getMeetingTelematicoByProgettiPS.setString(4, amb);
    	getMeetingTelematicoByProgettiPS.setString(5, amb);
    	
    	ResultSet rs = getMeetingTelematicoByProgettiPS.executeQuery();
    	
    	ArrayList<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
    	
    	while(rs.next()) {
    		
    		int cod = rs.getInt("CodMF");
    		Date date = rs.getDate("Data");
    		Time oraI = rs.getTime("OraI");
    		Time oraF = rs.getTime("OraF");
            Progetto p = getProgettoMeeting(rs.getInt("CodP"));
            String piattaforma = rs.getString("Piattaforma");
            int numLimite = rs.getInt("NumLimite");
            
            MeetingTelematico mt = new MeetingTelematico(cod, date, oraI, oraF, p, piattaforma, numLimite);
            
            mt.setPartecipanti(getPartecipantiMeeting(mt));
    		
    		lista.add(mt);
    		
    	}
		return lista;
    }

    public Sala getSalaMeetingFisico(int codSala) throws SQLException {
		 
		 getSalaMeetingFisicoPS.setInt(1, codSala);
		 
		 ResultSet rs = getSalaMeetingFisicoPS.executeQuery();
		 
		 rs.next();

		 String citta = rs.getString("Città");
		 String prov = (rs.getString("Provincia"));
		 String ind = (rs.getString("Indirizzo"));
		 int civico = rs.getInt("NumeroCivico");
		 int numPosti = rs.getInt("NumPosti");
		 int codice = rs.getInt("CodSala");
		 
		 Sala s = new Sala(codice, citta, prov, ind, civico, numPosti);
		 
		 return s;
	 }
    
    public Progetto getProgettoMeeting(int codp) throws SQLException {
    
    	getProgettoMeetingPS.setInt(1, codp);
    	
    	ResultSet rs = getProgettoMeetingPS.executeQuery();
    	
    	rs.next();
    	
		int cod = rs.getInt("CodP");
		String tipo = rs.getString("Tipologia");
		String desc = rs.getString("Descrizione");
		
		Progetto p = new Progetto(cod, tipo, desc);
    	
    	return p;
    }
    
    public ArrayList<Dipendente> getPartecipantiMeeting(MeetingFisico mf) throws SQLException {
    	
    	getPartecipantiMeetingFisicoPS.setInt(1, mf.getCodice());
    	
    	ResultSet rs = getPartecipantiMeetingFisicoPS.executeQuery();
    	
    	ArrayList<Dipendente> lista = new ArrayList<Dipendente>();
    	
    	while(rs.next()) {
    		
			String codf = rs.getString("CodF");
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			float salario = rs.getFloat("Salario");
			int valutazione = rs.getInt("Valutazione");
			
			Dipendente d = new Dipendente(codf, nome, cognome, salario, valutazione);
    		
    		lista.add(d);
    	}
    	
    	return lista;
    }
    
    public ArrayList<Dipendente> getPartecipantiMeeting(MeetingTelematico mt) throws SQLException {
    	
    	getPartecipantiMeetingTelematicoPS.setInt(1, mt.getCodice());
    	
    	ResultSet rs = getPartecipantiMeetingTelematicoPS.executeQuery();
    	
    	ArrayList<Dipendente> lista = new ArrayList<Dipendente>();
    	
    	while(rs.next()) {
    		
			String codf = rs.getString("CodF");
			String nome = rs.getString("Nome");
			String cognome = rs.getString("Cognome");
			float salario = rs.getFloat("Salario");
			int valutazione = rs.getInt("Valutazione");
			
			Dipendente d = new Dipendente(codf, nome, cognome, salario, valutazione);
    		
    		lista.add(d);
    	}
    	
    	return lista;
    }
}





