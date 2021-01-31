package dao_impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import dao.DipendenteDAO;
import entità.Dipendente;
import entità.Partecipante;

public class DipendenteDAOPostgresImpl implements DipendenteDAO {

	private Connection connection;
	
	private PreparedStatement insertDipendentePS;
	private PreparedStatement updateDipendentePS;
	private PreparedStatement deleteDipendentePS;
	
	private PreparedStatement getSalarioMedioPS;
	
	private PreparedStatement getDipendenteByAttributiPS;
	private PreparedStatement getDipendenteByProgettiPS;
	private PreparedStatement getDipendenteByMeetingFisiciPS;
	private PreparedStatement getDipendenteByMeetingTelematiciPS;
	
	public DipendenteDAOPostgresImpl(Connection c) throws SQLException {
		
		connection = c;
		
		insertDipendentePS = connection.prepareStatement("INSERT INTO DIPENDENTE VALUES (?, ?, ?, ?, ?);");
		
		updateDipendentePS = connection.prepareStatement("UPDATE DIPENDENTE SET CodF = ?, Nome = ?, Cognome = ?, Salario = ? WHERE Codf = ?;");
		
		deleteDipendentePS = connection.prepareStatement("DELETE FROM DIPENDENTE WHERE Codf = ?;");
		
		getSalarioMedioPS = connection.prepareStatement("SELECT get_Salario_Medio();");
		
		getDipendenteByAttributiPS = connection.prepareStatement("SELECT * "
																+ "FROM DIPENDENTE "
																+ "WHERE CodF <> 'AAAAAA00A00A000A' AND "
																+ "CodF ILIKE ? AND "
																	+ "Nome ILIKE ? AND "
																	+ "Cognome ILIKE ? AND "
																	+ "(Salario BETWEEN ? AND ?) AND "
																	+ "(Valutazione BETWEEN ? AND ?) "
																+ "ORDER BY Valutazione DESC;");
		
		getDipendenteByProgettiPS = connection.prepareStatement("SELECT DISTINCT * "
																+ "FROM DIPENDENTE AS D "
																+ "WHERE D.CodF <> 'AAAAAA00A00A000A' AND "
																	+ "D.CodF IN (SELECT DIP.CodF "
																				+ "FROM (PROGETTO AS P NATURAL JOIN "
																					+ "PARTECIPANTE AS PAR NATURAL JOIN "
																					+ "AMBITO AS A) RIGHT OUTER JOIN "
																					+ "DIPENDENTE AS DIP ON "
																					+ "PAR.CodF = DIP.CodF "
																				+ "WHERE (? = -1 OR P.CodP = ?) AND "
																					+ "(? IS NULL OR P.Tipologia ILIKE ?) AND "
																					+ "(? IS NULL OR A.Keyword ILIKE ?) AND "
																					+ "(? IS NULL OR PAR.Ruolo ILIKE ?) "
																				+ "GROUP BY DIP.CodF "
																				+ "HAVING COUNT(P.CodP) BETWEEN ? AND ?) "
																+ "ORDER BY D.Valutazione DESC;");
		
		getDipendenteByMeetingFisiciPS = connection.prepareStatement("SELECT * "
																	+ "FROM DIPENDENTE AS D "
																	+ "WHERE D.CodF IN (SELECT DIP.CodF "
																					+ "FROM (PARTECIPAMF AS PMF NATURAL JOIN "
																							+ "MEETINGF AS MF) RIGHT OUTER JOIN "
																							+ "DIPENDENTE AS DIP ON "
																							+ "DIP.CodF = PMF.CodF "
																					+ "WHERE (? = -1 OR MF.CodMF = ?) AND "
																							+ "(? = '2000-01-01'::date OR MF.Data::date = ?) AND "
																							+ "((MF.OraI BETWEEN ? AND ?) OR (MF.OraF BETWEEN ? AND ?))) "
																	+ "ORDER BY D.Valutazione DESC;");
		getDipendenteByMeetingTelematiciPS = connection.prepareStatement("SELECT * "
																	+ "FROM DIPENDENTE AS D "
																	+ "WHERE D.CodF IN (SELECT DIP.CodF "
																					+ "FROM (PARTECIPAMT AS PMT NATURAL JOIN "
																							+ "MEETINGT AS MT) RIGHT OUTER JOIN "
																							+ "DIPENDENTE AS DIP ON "
																							+ "DIP.CodF = PMT.CodF "
																					+ "WHERE (? = -1 OR MT.CodMT = ?) AND "
																							+ "(? = '2000-01-01'::date OR MT.Data::date = ?) AND "
																							+ "((MT.OraI BETWEEN ? AND ?) OR (MT.OraF BETWEEN ? AND ?))) "
																	+ "ORDER BY D.Valutazione DESC;");
		}
	
	public void insertDipendente(Dipendente d) throws SQLException {
		
		insertDipendentePS.setString(1, d.getCodF());
		insertDipendentePS.setString(2, d.getNome());
		insertDipendentePS.setString(3, d.getCognome());
		insertDipendentePS.setFloat(4, d.getSalario());
		insertDipendentePS.setInt(5, 0);
		
		insertDipendentePS.execute();
	}
	
	public void updateDipendente(Dipendente oldDip, Dipendente d) throws SQLException {
		
		updateDipendentePS.setString(1, d.getCodF());
		updateDipendentePS.setString(2, d.getNome());
		updateDipendentePS.setString(3, d.getCognome());
		updateDipendentePS.setFloat(4, d.getSalario());
		updateDipendentePS.setString(5, oldDip.getCodF());
		
		updateDipendentePS.execute();
	}
	
	public void deleteDipendente(Dipendente d) throws SQLException {
		
		deleteDipendentePS.setString(1, d.getCodF());
		
		deleteDipendentePS.execute();
	}
	
	public float getSalarioMedio() throws SQLException {
		
		ResultSet rs = getSalarioMedioPS.executeQuery();
		
		rs.next();
		
		return rs.getFloat(1);
	}
	
	public ArrayList<Dipendente> getDipendenteByAttributi(String codf, String nome, String cognome, String minSalario ,String maxSalario, String minValutazione, String maxValutazione) throws SQLException{
		
		float minSal, maxSal;
		int minVal, maxVal;
		
		if(minSalario.isBlank())
			minSal = 0;
		else
			minSal = Float.parseFloat(minSalario);
		
		if(maxSalario.isBlank())
			maxSal = Float.POSITIVE_INFINITY;
		else
			maxSal = Float.parseFloat(maxSalario);
		
		if(minValutazione.isBlank())
			minVal = Integer.MIN_VALUE;
		else
			minVal = Integer.parseInt(minValutazione);
		
		if(maxValutazione.isBlank())
			maxVal = Integer.MAX_VALUE;
		else
			maxVal = Integer.parseInt(maxValutazione);
		
		getDipendenteByAttributiPS.setString(1, "%" + codf + "%");
		getDipendenteByAttributiPS.setString(2, "%" + nome + "%");
		getDipendenteByAttributiPS.setString(3, "%" + cognome + "%");
		getDipendenteByAttributiPS.setFloat(4, minSal);
		getDipendenteByAttributiPS.setFloat(5, maxSal);
		getDipendenteByAttributiPS.setInt(6, minVal);
		getDipendenteByAttributiPS.setInt(7, maxVal);

		ResultSet rs = getDipendenteByAttributiPS.executeQuery();
		
		ArrayList<Dipendente> lista = new ArrayList<Dipendente>();
		
		while(rs.next()) {
			String codF = rs.getString("CodF");
			String Nome = rs.getString("Nome");
			String Cognome = rs.getString("Cognome");
			float salario = rs.getFloat("Salario");
			int valutazione = rs.getInt("Valutazione");
			
			Dipendente d = new Dipendente(codF, Nome, Cognome, salario, valutazione);
			
			lista.add(d);
		}
		
		return lista;
	}
	public ArrayList<Dipendente> getDipendenteByProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException {
		
		int codice, minimo, massimo;
		String tip, amb, ruo;
		
		if(codp.isBlank())
			codice = -1;
		else
			codice = Integer.parseInt(codp);
		
		if(tipologia.isBlank())
			tip = null;
		else
			tip = "%" + tipologia + "%";
		
		if(ambito.isBlank())
			amb = null;
		else
			amb = "%" + ambito + "%";
		
		if(ruolo.isBlank())
			ruo = null;
		else
			ruo = "%" + ruolo + "%";
		
		if(minProgetti.isBlank())
			minimo = 0;
		else
			minimo = Integer.parseInt(minProgetti);
		
		if(maxProgetti.isBlank())
			massimo = Integer.MAX_VALUE;
		else
			massimo = Integer.parseInt(maxProgetti);

		getDipendenteByProgettiPS.setInt(1, codice);
		getDipendenteByProgettiPS.setInt(2, codice);
		getDipendenteByProgettiPS.setString(3, tip);
		getDipendenteByProgettiPS.setString(4, tip);
		getDipendenteByProgettiPS.setString(5, amb);
		getDipendenteByProgettiPS.setString(6, amb);
		getDipendenteByProgettiPS.setString(7, ruo);
		getDipendenteByProgettiPS.setString(8, ruo);
		getDipendenteByProgettiPS.setInt(9, minimo);
		getDipendenteByProgettiPS.setInt(10, massimo);
		
		ResultSet rs = getDipendenteByProgettiPS.executeQuery();
		
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
	
	public ArrayList<Dipendente> getDipendenteByMeetingFisici(String codmf, String data, String oraI, String oraF) throws SQLException {
		
		int codice;
		Date Data;
    	Time oraInizio, oraFine;

    	if(codmf.isBlank())
    		codice = -1;
    	else
    		codice = Integer.parseInt(codmf);
    	
	    Data = Date.valueOf(data);
    	oraInizio = Time.valueOf(oraI);
    	oraFine = Time.valueOf(oraF);
    	
    	getDipendenteByMeetingFisiciPS.setInt(1, codice);
    	getDipendenteByMeetingFisiciPS.setInt(2, codice);
    	getDipendenteByMeetingFisiciPS.setDate(3, Data);
    	getDipendenteByMeetingFisiciPS.setDate(4, Data);
    	getDipendenteByMeetingFisiciPS.setTime(5, oraInizio);
    	getDipendenteByMeetingFisiciPS.setTime(6, oraFine);
    	getDipendenteByMeetingFisiciPS.setTime(7, oraInizio);
    	getDipendenteByMeetingFisiciPS.setTime(8, oraFine);
    	
    	ResultSet rs = getDipendenteByMeetingFisiciPS.executeQuery();
    	
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
	
	public ArrayList<Dipendente> getDipendenteByMeetingTelematici(String codmt, String data, String oraI, String oraF) throws SQLException {
		
		int codice;
		Date Data;
    	Time oraInizio, oraFine;

    	if(codmt.isBlank())
    		codice = -1;
    	else
    		codice = Integer.parseInt(codmt);
    	
	    Data = Date.valueOf(data);
    	oraInizio = Time.valueOf(oraI);
    	oraFine = Time.valueOf(oraF);
    	
    	getDipendenteByMeetingTelematiciPS.setInt(1, codice);
    	getDipendenteByMeetingTelematiciPS.setInt(2, codice);
    	getDipendenteByMeetingTelematiciPS.setDate(3, Data);
    	getDipendenteByMeetingTelematiciPS.setDate(4, Data);
    	getDipendenteByMeetingTelematiciPS.setTime(5, oraInizio);
    	getDipendenteByMeetingTelematiciPS.setTime(6, oraFine);
    	getDipendenteByMeetingTelematiciPS.setTime(7, oraInizio);
    	getDipendenteByMeetingTelematiciPS.setTime(8, oraFine);
    	
    	ResultSet rs = getDipendenteByMeetingTelematiciPS.executeQuery();
    	
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
