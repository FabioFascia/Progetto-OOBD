package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SalaDAO;
import entità.Sala;

public class SalaDAOPostgresImpl implements SalaDAO {
	
	private Connection connection;

	private PreparedStatement getSalaByAttributiPS;
	
	public SalaDAOPostgresImpl(Connection c) throws SQLException {
		
		connection = c;
		
		getSalaByAttributiPS = connection.prepareStatement("SELECT * "
															+ "FROM SALA "
															+ "WHERE Città ILIKE ? AND "
																	+ "Provincia ILIKE ? AND "
																	+ "Indirizzo ILIKE ? AND "
																	+ "(? = -1 OR NumeroCivico = ?) AND "
																	+ "NumPosti BETWEEN ? AND ? "
															+ "ORDER BY CodSala;");
	}
	
	public ArrayList<Sala> getSalaByAttributi(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException {
		
		int numCiv, minimo, massimo;
		
		if(numCivico.isBlank())
			numCiv = -1;
		else
			numCiv = Integer.parseInt(numCivico);
		
		if(minPosti.isBlank())
			minimo = Integer.MIN_VALUE;
		else
			minimo = Integer.parseInt(minPosti);
		
		if(maxPosti.isBlank())
			massimo = Integer.MAX_VALUE;
		else
			massimo = Integer.parseInt(maxPosti);
		
		getSalaByAttributiPS.setString(1, "%" + città + "%");
		getSalaByAttributiPS.setString(2, "%" + provincia + "%");
		getSalaByAttributiPS.setString(3, "%" + indirizzo + "%");
		getSalaByAttributiPS.setInt(4, numCiv);
		getSalaByAttributiPS.setInt(5, numCiv);
		getSalaByAttributiPS.setInt(6, minimo);
		getSalaByAttributiPS.setInt(7, massimo);
		
		ResultSet rs = getSalaByAttributiPS.executeQuery();
		
		ArrayList<Sala> lista = new ArrayList<Sala>();
		
		while(rs.next()) {
			
			Sala s = new Sala();
			
			s.setCittà(rs.getString("Città"));
			s.setProvincia(rs.getString("Provincia"));
			s.setIndirizzo(rs.getString("Indirizzo"));
			s.setNumeroCivico(rs.getInt("NumeroCivico"));
			s.setNumeroPosti(rs.getInt("NumPosti"));
			s.setCodice(rs.getInt("CodSala"));
			
			lista.add(s);
		}
		
		return lista;
	}
}
