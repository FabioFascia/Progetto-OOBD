package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entità.Dipendente;
import entità.Progetto;

public interface ProgettoDAO {
	
	public void insertProgetto(Progetto p) throws SQLException;
	public void deleteProgetto(Progetto p) throws SQLException;
	public void updateProgetto(Progetto p) throws SQLException;
	
	public void updateProjectManager(Progetto p, Dipendente d) throws SQLException;
	public void insertAmbito(Progetto p, String ambito) throws SQLException;
	public void deleteAmbito(Progetto p, String ambito) throws SQLException;
	public void insertPartecipante(Dipendente d, Progetto p, String ruolo) throws SQLException;
	public void deletePartecipante(Progetto p, Dipendente d) throws SQLException;
	
	public ArrayList<Progetto> getProgettoByAttributi(String codp, String tipologia, String ambito) throws SQLException;
	public ArrayList<Progetto> getProgettoByPartecipanti(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException;
	
	public int getCurrentSequenceValue() throws SQLException;
}
