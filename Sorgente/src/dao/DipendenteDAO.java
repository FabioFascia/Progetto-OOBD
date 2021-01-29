package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entit�.Dipendente;
import entit�.MeetingTelematico;
import entit�.Partecipante;

public interface DipendenteDAO {

	public void insertDipendente(Dipendente d) throws SQLException;
	public void updateDipendente(Dipendente oldDip, Dipendente d) throws SQLException;
	public void deleteDipendente(Dipendente d) throws SQLException;
	
	public ArrayList<Dipendente> getDipendenteByAttributi(String codf, String nome, String cognome, String minSalario, String maxSalario, String minValutazione, String maxValutazione) throws SQLException;
	public ArrayList<Dipendente> getDipendenteByProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException;
	public ArrayList<Dipendente> getDipendenteByMeetingFisici(String codmf, String data, String oraI, String oraF) throws SQLException;
	public ArrayList<Dipendente> getDipendenteByMeetingTelematici(String codmt, String data, String oraI, String oraF) throws SQLException;
}