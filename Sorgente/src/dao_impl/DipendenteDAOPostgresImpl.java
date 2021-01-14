package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DipendenteDAO;
import entità.Dipendente;
import entità.Partecipante;

public class DipendenteDAOPostgresImpl implements DipendenteDAO {

	private Connection connection;
	private PreparedStatement insertDipendentePS;
	private PreparedStatement updateDipendentePS;
	private PreparedStatement deleteDipendentePS;
	private PreparedStatement getDipendenteByAttributiPS;
	private PreparedStatement getDipendenteByProgettiPS;
	
	public DipendenteDAOPostgresImpl(Connection c) throws SQLException {
		
		connection = c;
		
		insertDipendentePS = connection.prepareStatement("INSERT INTO DIPENDENTE VALUES (?, ?, ?, ?);");
		
		updateDipendentePS = connection.prepareStatement("UPDATE DIPENDENTE SET CodF = ?, Nome = ?, Cognome = ?, Salario = ? WHERE Codf = ?;");
		
		deleteDipendentePS = connection.prepareStatement("DELETE FROM DIPENDENTE WHERE Codf = ?;");
		
		getDipendenteByAttributiPS = connection.prepareStatement("SELECT * "
																+ "FROM DIPENDENTE "
																+ "WHERE CodF ILIKE ? AND "
																	+ "Nome ILIKE ? AND "
																	+ "Cognome ILIKE ? AND "
																	+ "Salario BETWEEN ? AND ? "
																+ "ORDER BY Nome, Cognome;");
		
		getDipendenteByProgettiPS = connection.prepareStatement("SELECT DISTINCT D.CodF, D.Nome, D.Cognome, D.Salario "
																+ "FROM DIPENDENTE AS D LEFT OUTER JOIN "
																	+ "PARTECIPANTE AS PA ON "
																	+ "D.CodF = PA.CodF "
																+ "WHERE D.CodF <> 'AAAAAA00A00A000A' AND "
																	+ "D.CodF IN (SELECT DIP.CodF "
																				+ "FROM (PROGETTO AS P NATURAL JOIN "
																					+ "PARTECIPANTE AS PAR NATURAL JOIN "
																					+ "AMBITO AS A) RIGHT OUTER JOIN "
																					+ "DIPENDENTE AS DIP ON "
																					+ "PAR.CodF = DIP.CodF "
																				+ "WHERE (? IS NULL OR P.Tipologia ILIKE ?) AND "
																					+ "(? IS NULL OR A.Keyword ILIKE ?) AND "
																					+ "(? IS NULL OR PAR.Ruolo ILIKE ?) "
																				+ "GROUP BY DIP.CodF "
																				+ "HAVING COUNT(P.CodP) BETWEEN ? AND ?) "
																				+ "ORDER BY D.Nome, D.Cognome;");
		}
	
	public void insertDipendente(Dipendente d) throws SQLException {
		
		insertDipendentePS.setString(1, d.getCodF());
		insertDipendentePS.setString(2, d.getNome());
		insertDipendentePS.setString(3, d.getCognome());
		insertDipendentePS.setFloat(4, d.getSalario());
		
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
	
	public ArrayList<Dipendente> getDipendenteByAttributi(String codf, String nome, String cognome, String minSalario ,String maxSalario) throws SQLException{
		
		float minimo, massimo;
		
		if(minSalario.isBlank())
			minimo = 0;
		else
			minimo = Float.parseFloat(minSalario);
		
		if(maxSalario.isBlank())
			massimo = Float.POSITIVE_INFINITY;
		else
			massimo = Float.parseFloat(maxSalario);
		
		getDipendenteByAttributiPS.setString(1, "%" + codf + "%");
		getDipendenteByAttributiPS.setString(2, "%" + nome + "%");
		getDipendenteByAttributiPS.setString(3, "%" + cognome + "%");
		getDipendenteByAttributiPS.setFloat(4, minimo);
		getDipendenteByAttributiPS.setFloat(5, massimo);

		ResultSet rs = getDipendenteByAttributiPS.executeQuery();
		
		ArrayList<Dipendente> lista = new ArrayList<Dipendente>();
		
		while(rs.next()) {
			Dipendente row = new Dipendente();
			
			row.setCodF(rs.getString("CodF"));
			row.setNome(rs.getString("Nome"));
			row.setCognome(rs.getString("Cognome"));
			row.setSalario(rs.getFloat("Salario"));
			
			lista.add(row);
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
			Dipendente d = new Dipendente();
			
			d.setCodF(rs.getString("CodF"));
			d.setNome(rs.getString("Nome"));
			d.setCognome(rs.getString("Cognome"));
			d.setSalario(rs.getFloat("Salario"));
			
			lista.add(d);
		}
		
		return lista;
	}
}
