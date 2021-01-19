package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import dao.DipendenteDAO;
import dao.MeetingDAO;
import dao.ProgettoDAO;
import dao.SalaDAO;
import dao_impl.DipendenteDAOPostgresImpl;
import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SalaDAOPostgresImpl;
import db_config.DBConnection;
import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Partecipante;
import entità.Progetto;
import entità.Sala;
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
	private ModificaMeetingFisicoFrame modificaMeetingFisico;
	private ModificaMeetingTelematicoFrame modificaMeetingTelematico;
	private CercaPartecipanteMeetingFrame cercaPartecipanteMeeting;
	private CercaProgettoMeetingFrame cercaProgettoMeeting;
	private CercaSalaMeetingFrame cercaSalaMeeting;
	
	private CercaSalaFrame cercaSala;
	
	private DipendenteDAO dipendenteDao;
	private ProgettoDAO progettoDao;
	private MeetingDAO meetingDao;
	private SalaDAO salaDao;
	
	private ArrayList<Dipendente> DipendentiSelezionati;
	private ArrayList<Progetto> ProgettiSelezionati;
	private ArrayList<MeetingFisico> MeetingFisiciSelezionati;
	private ArrayList<MeetingTelematico> MeetingTelematiciSelezionati;
	private ArrayList<Sala> SaleRiunioniSelezionate;

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
	public void ApriFrameModificaMeetingFisicoInCercaMeeting(MeetingFisico mf) {
		modificaMeetingFisico = new ModificaMeetingFisicoFrame(this, mf);
		cercaMeeting.setEnabled(false);
		modificaMeetingFisico.setVisible(true);
	}
	public void ChiudiFrameModificaMeetingFisicoInCercaMeeting() {
		modificaMeetingFisico.dispose();
		cercaMeeting.toFront();
		cercaMeeting.setEnabled(true);
	}
	public void ApriFrameModificaMeetingTelematicoInCercaMeeting(MeetingTelematico mt) {
		modificaMeetingTelematico = new ModificaMeetingTelematicoFrame(this, mt);
		cercaMeeting.setEnabled(false);
		modificaMeetingTelematico.setVisible(true);
	}
	public void ChiudiFrameModificaMeetingTelematicoInCercaMeeting() {
		modificaMeetingTelematico.dispose();
		cercaMeeting.toFront();
		cercaMeeting.setEnabled(true);
	}
	
	public void ApriFrameCercaPartecipanteMeeting() {
		
		cercaPartecipanteMeeting = new CercaPartecipanteMeetingFrame(this);
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.setEnabled(false);
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable())
			modificaMeetingFisico.setEnabled(false);
		else
			modificaMeetingTelematico.setEnabled(false);
		cercaPartecipanteMeeting.setVisible(true);
	}
	public void ChiudiFrameCercaPartecipanteMeeting() {
		
		cercaPartecipanteMeeting.dispose();
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable()) {
			inserisciMeeting.toFront();
			inserisciMeeting.setEnabled(true);
		}
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable()) {
			modificaMeetingFisico.toFront();
			modificaMeetingFisico.setEnabled(true);
		}
		else {
			modificaMeetingTelematico.toFront();
			modificaMeetingTelematico.setEnabled(true);
		}
	}
	public void ApriFrameCercaProgettoMeeting() {
		
		cercaProgettoMeeting = new CercaProgettoMeetingFrame(this);
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.setEnabled(false);
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable())
			modificaMeetingFisico.setEnabled(false);
		else
			modificaMeetingTelematico.setEnabled(false);
		cercaProgettoMeeting.setVisible(true);
	}
	public void ChiudiFrameCercaProgettoMeeting() {
		
		cercaProgettoMeeting.dispose();
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable()) {
			inserisciMeeting.toFront();
			inserisciMeeting.setEnabled(true);
		}
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable()) {
			modificaMeetingFisico.toFront();
			modificaMeetingFisico.setEnabled(true);
		}
		else {
			modificaMeetingTelematico.toFront();
			modificaMeetingTelematico.setEnabled(true);
		}
	}
	public void ApriFrameCercaSalaMeeting() {
		
		cercaSalaMeeting = new CercaSalaMeetingFrame(this);
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.setEnabled(false);
		else
			modificaMeetingFisico.setEnabled(false);
		cercaSalaMeeting.setVisible(true);
	}
	public void ChiudiFrameCercaSalaMeeting() {
		
		cercaSalaMeeting.dispose();
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable()) {
			inserisciMeeting.toFront();
			inserisciMeeting.setEnabled(true);
		}
		else {
			modificaMeetingFisico.toFront();
			modificaMeetingFisico.setEnabled(true);
		}
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
	
	
	
	public void InserimentoMeetingFisico(MeetingFisico mf) throws SQLException {
		meetingDao.insertMeetingFisico(mf);
	}
	public void CancellazioneMeetingFisico(MeetingFisico mf) throws SQLException {
		meetingDao.deleteMeetingFisico(mf);
	}
	public void ModificaMeetingFisico(MeetingFisico mf) throws SQLException {
		meetingDao.updateMeetingFisico(mf);
	}
	public void InserimentoMeetingTelematico(MeetingTelematico mt) throws SQLException {
		meetingDao.insertMeetingTelematico(mt);
	}
	public void CancellazioneMeetingTelematico(MeetingTelematico mt) throws SQLException {
		meetingDao.deleteMeetingTelematico(mt);
	}
	public void ModificaMeetingTelematico(MeetingTelematico mt) throws SQLException {
		meetingDao.updateMeetingTelematico(mt);
	}
	
	public void SelezionePartecipanteMeeting(Dipendente d) throws SQLException {
		
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.addPartecipante(d);
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable())
			modificaMeetingFisico.addPartecipante(d);
		else
			modificaMeetingTelematico.addPartecipante(d);
	}
	public void InserimentoPartecipanteMeetingFisico(MeetingFisico mf, Dipendente d) throws SQLException {
		meetingDao.insertPartecipanteMeetingFisico(mf, d);
		mf.addPartecipante(d);
	}
    public void CancellazionePartecipanteMeetingFisico(MeetingFisico mf, Dipendente d) throws SQLException {
		meetingDao.deletePartecipanteMeetingFisico(mf, d);
		mf.getPartecipanti().remove(d);
	}
    public void InserimentoPartecipanteMeetingTelematico(MeetingTelematico mt, Dipendente d) throws SQLException {
    	meetingDao.insertPartecipanteMeetingTelematico(mt, d);
    	mt.addPartecipante(d);
    }
    public void CancellazionePartecipanteMeetingTelematico(MeetingTelematico mt, Dipendente d) throws SQLException {
    	meetingDao.deletePartecipanteMeetingTelematico(mt, d);
    	mt.getPartecipanti().remove(d);
    }
	public void SelezioneProgettoMeeting(Progetto p) throws SQLException {
		
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.setProgetto(p);
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable())
			modificaMeetingFisico.setProgetto(p);
		else
			modificaMeetingTelematico.setProgetto(p);
	}
	public void SelezioneSalaRiunioni(Sala s) throws SQLException {
		
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.setSala(s);
		else
			modificaMeetingFisico.setSala(s);
	}
	
	public ArrayList<MeetingFisico> RicercaMeetingFisicoPerAttributi(String CodMF, String Data, String OraInizio, String OraFine) throws SQLException {
		
		MeetingFisiciSelezionati = meetingDao.getMeetingFisicoByAttributi(CodMF,Data, OraInizio,  OraFine);

		return MeetingFisiciSelezionati;
	}
	public ArrayList<MeetingFisico> RicercaMeetingFisicoPerProgetti(String codp, String tipologia, String ambito) throws SQLException {
		
		MeetingFisiciSelezionati = meetingDao.getMeetingFisicoByProgetti(codp, tipologia, ambito);
		
		return MeetingFisiciSelezionati;
	}
    public ArrayList<MeetingTelematico> RicercaMeetingTelematicoPerAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo) throws SQLException {
		
		MeetingTelematiciSelezionati = meetingDao.getMeetingTelematicoByAttributi(CodMT, Data, OraInizio,  OraFine, Piattaforma, NumMassimo );
		
		return MeetingTelematiciSelezionati;
	}
    
    
    
    public ArrayList<Sala> RicercaSalaPerAttributi(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException {
    	
    	SaleRiunioniSelezionate = salaDao.getSalaByAttributi(città, provincia, indirizzo, numCivico, minPosti, maxPosti);
    	
    	return SaleRiunioniSelezionate;
    }
	
    
    
	public Dipendente getDipendenteSelezionato(int indice) {
		return DipendentiSelezionati.get(indice);
	}
	public Progetto getProgettoSelezionato(int indice) {
		return ProgettiSelezionati.get(indice);
	}
	public MeetingFisico getMeetingFisicoSelezionato(int indice) {
		return MeetingFisiciSelezionati.get(indice);
	}
	public MeetingTelematico getMeetingTelematicoSelezionato(int indice) {
		return MeetingTelematiciSelezionati.get(indice);
	}
	public Sala getSalaSelezionata(int indice) {
		return SaleRiunioniSelezionate.get(indice);
	}
	
	public void setDaos(String dbms) throws SQLException {
		
		switch (dbms) {
		case "postgresql":
			dipendenteDao = new DipendenteDAOPostgresImpl(connection);
			progettoDao = new ProgettoDAOPostgresImpl(connection);
			meetingDao = new MeetingDAOPostgresImpl(connection);
			salaDao = new SalaDAOPostgresImpl(connection);
			break;
		}
	}
	
}
	
