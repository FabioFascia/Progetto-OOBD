package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entit�.Sala;

public interface SalaDAO {

	public ArrayList<Sala> getSalaByAttributi(String citt�, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException;
}
