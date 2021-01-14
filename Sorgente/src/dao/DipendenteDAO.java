package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entità.Dipendente;
import entità.Partecipante;

public interface DipendenteDAO {

	public void insertDipendente(Dipendente d) throws SQLException;
	
	public void updateDipendente(Dipendente oldDip, Dipendente d) throws SQLException;
	public void deleteDipendente(Dipendente d) throws SQLException;
	
	public ArrayList<Dipendente> getDipendenteByAttributi(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException;
	public ArrayList<Dipendente> getDipendenteByProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException;
}
