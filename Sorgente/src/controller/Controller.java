package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import dao.DipendenteDAO;
import dao.MeetingDAO;
import dao.ProgettoDAO;
import dao_impl.DipendenteDAOPostgresImpl;
import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import db_config.DBConnection;
import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Partecipante;
import entità.Progetto;
import gui.*;

public class Controller {
	
	private Connection connection;
	private MainMenuFrame mainMenu;
	
	private CercaDipendenteFrame cercaDipendente;
	private InserisciDipendenteFrame inserisciDipendente;
	private ModificaDipendenteFrame modificaDipendente;
	
	private CercaProgettoFrame cercaProgetto;
	private InserisciProgettoFrame inserisciProgetto;
	private ModificaProgettoFrame modificaProgetto;
	
	private CercaProjectManagerFrame cercaProjectManager;
	private CercaPartecipanteFrame cercaPartecipante;
	
	private CercaMeetingFrame cercaMeeting;
	private InserisciMeetingFrame inserisciMeeting;
	private CercaSalaFrame cercaSala;
	
	private DipendenteDAO dipendenteDao;
	private ProgettoDAO progettoDao;
	private MeetingDAO meetingDao;
	
	private ArrayList<Dipendente> DipendentiSelezionati;
	private ArrayList<Progetto> ProgettiSelezionati;
	private ArrayList<Meeting> MeetingSelezionati;

	public static void main(String[] args) {
		
		DBConnection dbConnection = null;
		Connection connection = null;
		
		try {
			
			dbConnection = DBConnection.getInstance();
			connection = dbConnection.getConnection();
			Controller c = new Controller(connection);
			c.setDaos("postgresql");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}	
	}
	
	public Controller(Connection c) {
		
		connection = c;
		mainMenu = new MainMenuFrame(this);
		mainMenu.setVisible(true);
	}
	
	public void CambiaFrameMainMenuInCercaDipendente() {
		
		cercaDipendente = new CercaDipendenteFrame(this);
		mainMenu.setVisible(false);
		cercaDipendente.setVisible(true);
	}
	public void CambiaFrameCercaDipendenteInMainMenu() {
		
		cercaDipendente.dispose();
		mainMenu.setVisible(true);
	}
	public void ApriFrameInserisciDipendenteInCercaDipendente() {
		
		inserisciDipendente = new InserisciDipendenteFrame(this);
		cercaDipendente.setEnabled(false);
		inserisciDipendente.setVisible(true);
	}
	public void ChiudiFrameInserisciDipendenteInCercaDipendente() {
		
		inserisciDipendente.dispose();
		cercaDipendente.toFront();
		cercaDipendente.setEnabled(true);
	}
	public void ApriFrameModificaDipendenteInCercaDipendente(Dipendente d) {
		
		modificaDipendente = new ModificaDipendenteFrame(this, d);
		cercaDipendente.setEnabled(false);
		modificaDipendente.setVisible(true);
	}
	public void ChiudiFrameModificaDipendenteInCercaDipendente() {
		
		modificaDipendente.dispose();
		cercaDipendente.toFront();
		cercaDipendente.setEnabled(true);
	}
	
	
	
	public void CambiaFrameMainMenuInCercaProgetto() {
		
		cercaProgetto = new CercaProgettoFrame(this);
		mainMenu.setVisible(false);
		cercaProgetto.setVisible(true);	
	}
	public void CambiaFrameCercaProgettoInMainMenu() {
		
		cercaProgetto.dispose();
		mainMenu.setVisible(true);
	}
	public void ApriFrameInserisciProgettoInCercaProgetto() {
		
		inserisciProgetto = new InserisciProgettoFrame(this);
		cercaProgetto.setEnabled(false);
		inserisciProgetto.setVisible(true);
	}
	public void ChiudiFrameInserisciProgettoInCercaProgetto() {
		
		inserisciProgetto.dispose();
		cercaProgetto.toFront();
		cercaProgetto.setEnabled(true);
	}
	public void ApriFrameModificaProgettoInCercaProgetto(Progetto p) {
		
		modificaProgetto = new ModificaProgettoFrame(this, p);
		cercaProgetto.setEnabled(false);
		modificaProgetto.setVisible(true);
	}
	public void ChiudiFrameModificaProgettoInCercaProgetto() {
		
		modificaProgetto.dispose();
		cercaProgetto.toFront();
		cercaProgetto.setEnabled(true);
	}
	public void ApriFrameCercaProjectManager() {
		
		cercaProjectManager = new CercaProjectManagerFrame(this);
		if(inserisciProgetto != null && inserisciProgetto.isDisplayable())
			inserisciProgetto.setEnabled(false);
		else
			modificaProgetto.setEnabled(false);
		cercaProjectManager.setVisible(true);
	}
	public void ChiudiFrameCercaProjectManager() {
		
		cercaProjectManager.dispose();
		if(inserisciProgetto != null && inserisciProgetto.isDisplayable()) {
			inserisciProgetto.toFront();
			inserisciProgetto.ToggleInsertButton();
			inserisciProgetto.setEnabled(true);
		}
		else {
			modificaProgetto.toFront();
			modificaProgetto.ToggleUpdateButton();
			modificaProgetto.setEnabled(true);
		}
	}
	public void ApriFrameCercaPartecipante() {
		
		cercaPartecipante = new CercaPartecipanteFrame(this);
		if(inserisciProgetto != null && inserisciProgetto.isDisplayable())
			inserisciProgetto.setEnabled(false);
		else
			modificaProgetto.setEnabled(false);
		cercaPartecipante.setVisible(true);
	}
	public void ChiudiFrameCercaPartecipante() {
		
		cercaPartecipante.dispose();
		if(inserisciProgetto != null && inserisciProgetto.isDisplayable()) {
			inserisciProgetto.toFront();
			inserisciProgetto.setEnabled(true);
		}
		else {
			modificaProgetto.toFront();
			modificaProgetto.setEnabled(true);
		}
	}
	
	
	
	public void CambiaFrameMainMenuInCercaMeeting() {
		
		cercaMeeting = new CercaMeetingFrame(this);
		mainMenu.setVisible(false);
		cercaMeeting.setVisible(true);
	}
	public void CambiaFrameCercaMeetingInMainMenu() {
		
		cercaMeeting.dispose();
		mainMenu.setVisible(true);
	}
	public void ApriFrameInserisciMeetingInCercaMeeting() {

		inserisciMeeting = new InserisciMeetingFrame(this);
		cercaMeeting.setEnabled(false);
		inserisciMeeting.setVisible(true);
	}
public void ChiudiFrameInserisciMeetingInCercaMeeting() {
		
		inserisciMeeting.dispose();
		cercaMeeting.toFront();
		cercaMeeting.setEnabled(true);
	}
	
	
	
	public void CambiaFrameMainMenuInCercaSala() {
		
		cercaSala = new CercaSalaFrame(this);
		mainMenu.setVisible(false);
		cercaSala.setVisible(true);
	}
	public void CambiaFrameCercaSalaInMainMenu() {
		
		cercaSala.dispose();
		mainMenu.setVisible(true);
	}
	
	
	
	
	
	
	public void InserimentoDipendente(Dipendente d) throws SQLException {
		
		dipendenteDao.insertDipendente(d);
	}
	public void ModificaDipendente(Dipendente oldDip, Dipendente d) throws SQLException {
		
		dipendenteDao.updateDipendente(oldDip, d);
	}
	public void CancellazioneDipendente(Dipendente d) throws SQLException {
		
		dipendenteDao.deleteDipendente(d);
	}
	public ArrayList<Dipendente> RicercaDipendentePerAttributi(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByAttributi(codf, nome, cognome, minSalario ,maxSalario);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaDipendentePerProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByProgetti(codp, tipologia, ambito, ruolo, minProgetti, maxProgetti);
		return DipendentiSelezionati;
	}
	
	public void InserimentoProgetto(Progetto p) throws SQLException {
		
		progettoDao.insertProgetto(p);
	}
	public void CancellazioneProgetto(Progetto p) throws SQLException {
		
		progettoDao.deleteProgetto(p);
	}
	public void ModificaProgetto(Progetto p) throws SQLException {
		
		progettoDao.updateProgetto(p);
	}
	public void SelezioneProjectManager(Dipendente pm) throws SQLException {

		if(inserisciProgetto != null && inserisciProgetto.isDisplayable())
			inserisciProgetto.setProjectManager(pm);
		else
			modificaProgetto.setProjectManager(pm);
	}
	public void SelezionePartecipante(Dipendente d, String ruolo) throws SQLException {
		
		if(inserisciProgetto != null && inserisciProgetto.isDisplayable())
			inserisciProgetto.addPartecipante(d, ruolo);
		else
			modificaProgetto.addPartecipante(d, ruolo);
	}
	public void ModificaProjectManager(Progetto p, Dipendente d) throws SQLException {
		
		progettoDao.updateProjectManager(p, d);
		p.setProjectManager(d);
	}
	public void InserimentoAmbito(Progetto p, String ambito) throws SQLException {
		
		progettoDao.insertAmbito(p, ambito);
		p.addAmbito(ambito);
	}
	public void CancellazioneAmbito(Progetto p, String ambito) throws SQLException {
		
		progettoDao.deleteAmbito(p, ambito);
		p.getAmbiti().remove(ambito);
	}
	public void InserimentoPartecipante(Dipendente d, Progetto p, String ruolo) throws SQLException {
		
		progettoDao.insertPartecipante(d, p, ruolo);
		p.addPartecipante(d, ruolo);
	}
	public void CancellazionePartecipante(Progetto p, Partecipante par) throws SQLException {
		
		p.getPartecipanti().remove(par);
		progettoDao.deletePartecipante(p, par.getDipendente());
	}
	
	public ArrayList<Progetto> RicercaProgettoPerAttributi(String codp, String tipologia, String ambito) throws SQLException {
		
		ProgettiSelezionati = progettoDao.getProgettoByAttributi(codp, tipologia, ambito);
		return ProgettiSelezionati;
	}
	public ArrayList<Progetto> RicercaProgettoPerPartecipanti(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException {
		
		ProgettiSelezionati = progettoDao.getProgettoByPartecipanti(codf, nome, cognome, minSalario, maxSalario);
		return ProgettiSelezionati;
	}
	public ArrayList<Dipendente> RicercaProjectManagerPerAttributi(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByAttributi(codf, nome, cognome, minSalario ,maxSalario);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaProjectManagerPerProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByProgetti(codp, tipologia, ambito, ruolo, minProgetti, maxProgetti);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaPartecipantePerAttributi(String codf, String nome, String cognome, String minSalario, String maxSalario) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByAttributi(codf, nome, cognome, minSalario ,maxSalario);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaPartecipantePerProgetti(String codp, String tipologia, String ambito, String ruolo, String minProgetti, String maxProgetti) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByProgetti(codp, tipologia, ambito, ruolo, minProgetti, maxProgetti);
		return DipendentiSelezionati;
	}

	public void RicercaMeetingFisicoPerAttributi(String CodMF, String Data, String OraInizio, String OraFine) throws SQLException {
		ArrayList<MeetingFisico> lista = meetingDao.getMeetingFisicoByAttributi(CodMF,Data, OraInizio,  OraFine);
		cercaMeeting.PopolaTabellaFisico(lista);
	}
    
	public void InserimentoMeetingFisico(MeetingFisico mf)throws SQLException {
		meetingDao.insertMeetingFisico(mf);
	}
	public void InserimentoMeetingTelematico(MeetingTelematico mt)throws SQLException {
		meetingDao.insertMeetingTelematico(mt);
	}


		
	
    public void RicercaMeetingTelematicoPerAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo) throws SQLException {
		
		ArrayList<MeetingTelematico> lista = meetingDao.getMeetingTelematicoByAttributi(CodMT, Data, OraInizio,  OraFine, Piattaforma, NumMassimo );
		
		cercaMeeting.PopolaTabella(lista);
	}
	
	public Dipendente getDipendenteSelezionato(int indice) {
		return DipendentiSelezionati.get(indice);
	}
	public Progetto getProgettoSelezionato(int indice) {
		return ProgettiSelezionati.get(indice);
	}
	public Meeting getMeetingSelezionato(int indice) {
		return MeetingSelezionati.get(indice);
	}
	
	public void setDaos(String dbms) throws SQLException {
		
		switch (dbms) {
		case "postgresql":
			dipendenteDao = new DipendenteDAOPostgresImpl(connection);
			progettoDao = new ProgettoDAOPostgresImpl(connection);
			meetingDao = new MeetingDAOPostgresImpl(connection);
			break;
		}
	}

	    
}
	
