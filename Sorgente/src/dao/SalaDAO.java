package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entità.Sala;

public interface SalaDAO {

	public void insertSala(Sala s) throws SQLException;
	
	public ArrayList<Sala> getSalaByAttributi(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException;
}
