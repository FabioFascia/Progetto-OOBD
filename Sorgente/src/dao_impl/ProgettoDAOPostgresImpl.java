package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Controller;
import dao.ProgettoDAO;
import entità.Dipendente;
import entità.Partecipante;
import entità.Progetto;

public class ProgettoDAOPostgresImpl implements ProgettoDAO {

	private Connection connection;
	private PreparedStatement insertProgettoPS;
	private PreparedStatement deleteProgettoPS;
	private PreparedStatement updateProgettoPS;
	private PreparedStatement updateProjectManagerPS;
	private PreparedStatement insertAmbitoPS;
	private PreparedStatement deleteAmbitoPS;
	private PreparedStatement insertPartecipantePS;
	private PreparedStatement deletePartecipantePS;
	private PreparedStatement getProgettoByAttributiPS;
	private PreparedStatement getProgettoByPartecipantiPS;
	private PreparedStatement getAmbitiProgettoPS;
	private PreparedStatement getPartecipantiProgettoPS;
	private PreparedStatement getCurrentSequenceValuePS;
	
	public ProgettoDAOPostgresImpl(Connection c) throws SQLException {
		
		connection = c;
		
		insertProgettoPS = connection.prepareStatement("CALL Insert_Progetto(?, ?, ?);");
		deleteProgettoPS = connection.prepareStatement("DELETE FROM PROGETTO WHERE CodP = ?");
		updateProgettoPS = connection.prepareStatement("UPDATE PROGETTO SET Tipologia = ?, Descrizione = ? WHERE CodP = ?;");
		
		updateProjectManagerPS = connection.prepareStatement("UPDATE PARTECIPANTE SET CodF = ? WHERE CodP = ? AND Ruolo ILIKE 'Project Manager';");
		insertAmbitoPS = connection.prepareStatement("INSERT INTO AMBITO VALUES (?, ?);");
		deleteAmbitoPS = connection.prepareStatement("DELETE FROM AMBITO WHERE CodP = ? AND Keyword = ?;");
		insertPartecipantePS = connection.prepareStatement("INSERT INTO PARTECIPANTE VALUES (?, ?, ?);");
		deletePartecipantePS = connection.prepareStatement("DELETE FROM PARTECIPANTE WHERE CodP = ? AND CodF = ?");
		
		getProgettoByAttributiPS = connection.prepareStatement("SELECT * "
															+ "FROM PROGETTO AS P "
															+ "WHERE (? = -1 OR P.CodP = ?) AND "
															+ "(? IS NULL OR P.Tipologia ILIKE ?) AND "
																	+ "(? IS NULL OR P.CodP IN (SELECT PR.CodP "
																							+ "FROM PROGETTO AS PR LEFT OUTER JOIN "
																								+ "AMBITO AS A ON "
																								+ "PR.CodP = A.CodP "
																							+ "WHERE A.Keyword ILIKE ?))"
															+ "ORDER BY P.CodP;");
		getProgettoByPartecipantiPS = connection.prepareStatement("SELECT * "
																+ "FROM PROGETTO AS P "
																+ "WHERE P.CodP IN (SELECT PAR.CodP "
																				+ "FROM PARTECIPANTE AS PAR NATURAL JOIN "
																					+ "DIPENDENTE AS D "
																				+ "WHERE D.CodF ILIKE ? AND "
																						+ "D.Nome ILIKE ? AND "
																						+ "D.Cognome ILIKE ? AND "
																						+ "D.Salario BETWEEN ? AND ?) "
																+ "ORDER BY P.CodP;");
		
		getAmbitiProgettoPS = connection.prepareStatement("SELECT * FROM AMBITO WHERE CodP = ?");
		getPartecipantiProgettoPS = connection.prepareStatement("SELECT * FROM PARTECIPANTE AS PAR NATURAL JOIN DIPENDENTE AS D WHERE PAR.CodP = ?");
		
		getCurrentSequenceValuePS = connection.prepareStatement("SELECT CURRVAL('N_PROGETTO');");
	}
	
	public void insertProgetto(Progetto p) throws SQLException {
		
		insertProgettoPS.setString(1, p.getTipologia());
		insertProgettoPS.setString(2, p.getProjectManager().getCodF());
		insertProgettoPS.setString(3, p.getDescrizione());
		
		insertProgettoPS.execute();
		
		for(Partecipante par : p.getPartecipanti()) {
			insertPartecipantePS.setString(1, par.getDipendente().getCodF());
			insertPartecipantePS.setInt(2, getCurrentSequenceValue());
			insertPartecipantePS.setString(3, par.getRuolo());
			
			insertPartecipantePS.execute();
		}
		
		for(String a : p.getAmbiti()) {
			insertAmbitoPS.setInt(1, getCurrentSequenceValue());
			insertAmbitoPS.setString(2, a);
			
			insertAmbitoPS.execute();
		}
	}
	
	public void deleteProgetto(Progetto p) throws SQLException {
		
		deleteProgettoPS.setInt(1, p.getCodice());
		
		deleteProgettoPS.execute();
	}
	public void updateProgetto(Progetto p) throws SQLException {
		
		updateProgettoPS.setString(1, p.getTipologia());
		updateProgettoPS.setString(2, p.getDescrizione());
		updateProgettoPS.setInt(3, p.getCodice());
		
		updateProgettoPS.execute();
	}
	
	public void updateProjectManager(Progetto p, Dipendente d) throws SQLException {
		
		updateProjectManagerPS.setString(1, d.getCodF());
		updateProjectManagerPS.setInt(2, p.getCodice());
		
		updateProjectManagerPS.execute();
	}
	public void insertAmbito(Progetto p ,String ambito) throws SQLException {
		
		insertAmbitoPS.setInt(1, p.getCodice());
		insertAmbitoPS.setString(2, ambito);
		
		insertAmbitoPS.execute();
	}
	public void deleteAmbito(Progetto p, String ambito) throws SQLException {
		
		deleteAmbitoPS.setInt(1, p.getCodice());
		deleteAmbitoPS.setString(2, ambito);
		
		deleteAmbitoPS.execute();
	}
	
	public void insertPartecipante(Dipendente d, Progetto p, String ruolo) throws SQLException {
		
		insertPartecipantePS.setString(1, d.getCodF());
		insertPartecipantePS.setInt(2, p.getCodice());
		insertPartecipantePS.setString(3, ruolo);
		
		insertPartecipantePS.execute();
	}
	
	public void deletePartecipante(Progetto p, Dipendente d) throws SQLException {
		
		deletePartecipantePS.setInt(1, p.getCodice());
		deletePartecipantePS.setString(2, d.getCodF());
		
		deletePartecipantePS.execute();
	}
	
	public ArrayList<Progetto> getProgettoByAttributi(String codp, String tipologia, String ambito) throws SQLException {
		
		int codice;
		String tip, amb;
		
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
		
		getProgettoByAttributiPS.setInt(1, codice);
		getProgettoByAttributiPS.setInt(2, codice);
		getProgettoByAttributiPS.setString(3, tip);
		getProgettoByAttributiPS.setString(4, tip);
		getProgettoByAttributiPS.setString(5, amb);
		getProgettoByAttributiPS.setString(6, amb);
		
		ResultSet rs = getProgettoByAttributiPS.executeQuery();
		
		ArrayList<Progetto> lista = new ArrayList<Progetto>();
		
		while(rs.next()) {
			Progetto p = new Progetto();
			
			p.setCodice(rs.getInt("CodP"));
			p.setTipologia(rs.getString("Tipologia"));
			p.setDescrizione(rs.getString("Descrizione"));
			
			p.setAmbiti(getAmbitiProgetto(p));
			p.setPartecipanti(getPartecipantiProgetto(p));
			
			lista.add(p);
		}
		
		return lista;
	}
	public ArrayList<Progetto> getProgettoByPartecipanti(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException {
		
		float minimo, massimo;
		
		if(minSalario.isBlank())
			minimo = 0;
		else
			minimo = Float.parseFloat(minSalario);
		
		if(maxSalario.isBlank())
			massimo = Float.POSITIVE_INFINITY;
		else
			massimo = Float.parseFloat(maxSalario);
		
		getProgettoByPartecipantiPS.setString(1, "%" + codf + "%");
		getProgettoByPartecipantiPS.setString(2, "%" + nome + "%");
		getProgettoByPartecipantiPS.setString(3, "%" + cognome + "%");
		getProgettoByPartecipantiPS.setFloat(4, minimo);
		getProgettoByPartecipantiPS.setFloat(5, massimo);
		
		ResultSet rs = getProgettoByPartecipantiPS.executeQuery();
		
		ArrayList<Progetto> lista = new ArrayList<Progetto>();
		
		while(rs.next()) {
			Progetto p = new Progetto();
			
			p.setCodice(rs.getInt("CodP"));
			p.setTipologia(rs.getString("Tipologia"));
			p.setDescrizione(rs.getString("Descrizione"));
			
			p.setAmbiti(getAmbitiProgetto(p));
			p.setPartecipanti(getPartecipantiProgetto(p));
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public ArrayList<String> getAmbitiProgetto(Progetto p) throws SQLException {
		
		getAmbitiProgettoPS.setInt(1, p.getCodice());
		
		ResultSet rs = getAmbitiProgettoPS.executeQuery();
		
		ArrayList<String> lista = new ArrayList<String>();
		
		while(rs.next()) {
			
			lista.add(rs.getString("Keyword"));
		}
		
		return lista;
	}
	
	public ArrayList<Partecipante> getPartecipantiProgetto(Progetto p) throws SQLException {
		
		getPartecipantiProgettoPS.setInt(1, p.getCodice());
		
		ResultSet rs = getPartecipantiProgettoPS.executeQuery();
		
		ArrayList<Partecipante> lista = new ArrayList<Partecipante>();
		
		while(rs.next()) {
			Dipendente d = new Dipendente();
			
			d.setCodF(rs.getString("CodF"));
			d.setNome(rs.getString("Nome"));
			d.setCognome(rs.getString("Cognome"));
			d.setSalario(rs.getFloat("Salario"));
			
			Partecipante par = new Partecipante(d, p, rs.getString("Ruolo"));
			
			if(par.getRuolo().equalsIgnoreCase("Project Manager"))
				lista.add(0, par);
			else
				lista.add(par);
		}
		
		return lista;
	}
	
	public int getCurrentSequenceValue() throws SQLException {
		
		ResultSet rs = getCurrentSequenceValuePS.executeQuery();
		
		rs.next();
		
		return rs.getInt(1);
	}
}
