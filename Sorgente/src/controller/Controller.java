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
	
	private DBConnection dbConnection;
	
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
	private InserisciSalaFrame inserisciSala;
	private ModificaSalaFrame modificaSala;
	
	private DipendenteDAO dipendenteDao;
	private ProgettoDAO progettoDao;
	private MeetingDAO meetingDao;
	private SalaDAO salaDao;
	
	private ArrayList<Dipendente> DipendentiSelezionati;
	private ArrayList<Progetto> ProgettiSelezionati;
	private ArrayList<MeetingFisico> MeetingFisiciSelezionati;
	private ArrayList<MeetingTelematico> MeetingTelematiciSelezionati;
	private ArrayList<Sala> SaleRiunioniSelezionate;
	
	
	public Controller(DBConnection dbc) {
		
		dbConnection = dbc;
		mainMenu = new MainMenuFrame(this);
		mainMenu.setVisible(true);
	}
	
	
	
	public void ApriFrameCercaDipendenteInMainMenu() {
		
		cercaDipendente = new CercaDipendenteFrame(this);
		mainMenu.setVisible(false);
		cercaDipendente.setVisible(true);
	}
	public void ChiudiFrameCercaDipendenteInMainMenu() {
		
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
	
	
	
	public void ApriFrameCercaProgettoInMainMenu() {
		
		cercaProgetto = new CercaProgettoFrame(this);
		mainMenu.setVisible(false);
		cercaProgetto.setVisible(true);	
	}
	public void ChiudiFrameCercaProgettoInMainMenu() {
		
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
			inserisciProgetto.AttivaButtonInserimento();
			inserisciProgetto.setEnabled(true);
		}
		else {
			modificaProgetto.toFront();
			modificaProgetto.AttivaButtonModifica();
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
	
	
	
	public void ApriFrameCercaMeetingInMainMenu() {
		
		cercaMeeting = new CercaMeetingFrame(this);
		mainMenu.setVisible(false);
		cercaMeeting.setVisible(true);
	}
	public void ChiudiFrameCercaMeetingInMainMenu() {
		
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
	
	
	
	public void ApriFrameCercaSalaInMainMenu() {
		
		cercaSala = new CercaSalaFrame(this);
		mainMenu.setVisible(false);
		cercaSala.setVisible(true);
	}
	public void ChiudiFrameCercaSalaInMainMenu() {
		
		cercaSala.dispose();
		mainMenu.setVisible(true);
	}
	
	public void ApriFrameInserisciSalaInCercaSala() {
		
		inserisciSala = new InserisciSalaFrame(this);
		cercaSala.setEnabled(false);
		inserisciSala.setVisible(true);
	}
	public void ChiudiFrameInserisciSalaInCercaSala() {
		
		inserisciSala.dispose();
		cercaSala.toFront();
		cercaSala.setEnabled(true);
	}
	public void ApriFrameModificaSalaInCercaSala(Sala s) {
		
		modificaSala = new ModificaSalaFrame(this, s);
		cercaSala.setEnabled(false);
		modificaSala.setVisible(true);
	}
	public void ChiudiFrameModificaSalaInCercaSala() {
		
		modificaSala.dispose();
		cercaSala.toFront();
		cercaSala.setEnabled(true);
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
	
	public ArrayList<Dipendente> RicercaDipendentePerAttributi(String codf, String nome, String cognome, String minSal, String maxSal, String minVal, String maxVal) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByAttributi(codf, nome, cognome, minSal ,maxSal, minVal, maxVal);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaDipendentePerProgetti(String codp, String tipologia, String ambito, String ruolo, String minProg, String maxProg) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByProgetti(codp, tipologia, ambito, ruolo, minProg, maxProg);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaDipendentePerMeetingFisici(String codmf, String data, String oraI, String oraF) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByMeetingFisici(codmf, data, oraI, oraF);
		return DipendentiSelezionati;
	}
	public ArrayList<Dipendente> RicercaDipendentePerMeetingTelematici(String codmt, String data, String oraI, String oraF) throws SQLException {
		
		DipendentiSelezionati = dipendenteDao.getDipendenteByMeetingTelematici(codmt, data, oraI, oraF);
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
	public void ModificaProjectManager(Progetto p, Dipendente pm) throws SQLException {
		
		progettoDao.updateProjectManager(p, pm);
		p.setProjectManager(pm);
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
	public ArrayList<Progetto> RicercaProgettoPerPartecipanti(String codf, String nome, String cognome, String minSal, String maxSal, String minVal, String maxVal) throws SQLException {
		
		ProgettiSelezionati = progettoDao.getProgettoByPartecipanti(codf, nome, cognome, minSal, maxSal, minVal, maxVal);
		return ProgettiSelezionati;
	}
	
	
	
	public void InserimentoMeeting(MeetingFisico mf) throws SQLException {
		meetingDao.insertMeeting(mf);
	}
	public void CancellazioneMeeting(MeetingFisico mf) throws SQLException {
		meetingDao.deleteMeeting(mf);
	}
	public void ModificaMeeting(MeetingFisico mf) throws SQLException {
		meetingDao.updateMeeting(mf);
	}
	public void InserimentoMeeting(MeetingTelematico mt) throws SQLException {
		meetingDao.insertMeeting(mt);
	}
	public void CancellazioneMeeting(MeetingTelematico mt) throws SQLException {
		meetingDao.deleteMeeting(mt);
	}
	public void ModificaMeeting(MeetingTelematico mt) throws SQLException {
		meetingDao.updateMeeting(mt);
	}
	
	public void SelezionePartecipanteMeeting(Dipendente d) throws SQLException {
		
		if(inserisciMeeting != null && inserisciMeeting.isDisplayable())
			inserisciMeeting.addPartecipante(d);
		else if(modificaMeetingFisico != null && modificaMeetingFisico.isDisplayable())
			modificaMeetingFisico.addPartecipante(d);
		else
			modificaMeetingTelematico.addPartecipante(d);
	}
	public void InserimentoPartecipanteMeeting(MeetingFisico mf, Dipendente d) throws SQLException {
		meetingDao.insertPartecipanteMeeting(mf, d);
		mf.addPartecipante(d);
	}
    public void CancellazionePartecipanteMeeting(MeetingFisico mf, Dipendente d) throws SQLException {
		meetingDao.deletePartecipanteMeeting(mf, d);
		mf.getPartecipanti().remove(d);
	}
    public void InserimentoPartecipanteMeeting(MeetingTelematico mt, Dipendente d) throws SQLException {
    	meetingDao.insertPartecipanteMeeting(mt, d);
    	mt.addPartecipante(d);
    }
    public void CancellazionePartecipanteMeeting(MeetingTelematico mt, Dipendente d) throws SQLException {
    	meetingDao.deletePartecipanteMeeting(mt, d);
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
	public void SelezioneSalaMeeting(Sala s) throws SQLException {
		
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
	public ArrayList<MeetingFisico> RicercaMeetingFisicoPerSale(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException {
		
		MeetingFisiciSelezionati = meetingDao.getMeetingFisicoBySala(città, provincia, indirizzo, numCivico, minPosti, maxPosti);
		
		return MeetingFisiciSelezionati;
	}
    public ArrayList<MeetingTelematico> RicercaMeetingTelematicoPerAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String limite) throws SQLException {
		
		MeetingTelematiciSelezionati = meetingDao.getMeetingTelematicoByAttributi(CodMT, Data, OraInizio,  OraFine, Piattaforma, limite );
		
		return MeetingTelematiciSelezionati;
	}
    public ArrayList<MeetingTelematico> RicercaMeetingTelematicoPerProgetti(String codp, String tipologia, String ambito) throws SQLException {
    	
    	MeetingTelematiciSelezionati = meetingDao.getMeetingTelematicoByProgetti(codp, tipologia, ambito);
    	
    	return MeetingTelematiciSelezionati;
    }
    
    
    
    public void InserimentoSala(Sala s) throws SQLException {
    	
    	salaDao.insertSala(s);
    }
    public void CancellazioneSala(Sala s) throws SQLException {
    	salaDao.deleteSala(s);
    }
    public void ModificaSala(Sala s) throws SQLException {
    	salaDao.updateSala(s);
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
			dipendenteDao = new DipendenteDAOPostgresImpl(dbConnection.getConnection());
			progettoDao = new ProgettoDAOPostgresImpl(dbConnection.getConnection());
			meetingDao = new MeetingDAOPostgresImpl(dbConnection.getConnection());
			salaDao = new SalaDAOPostgresImpl(dbConnection.getConnection());
			break;
		}
	}
	
}
	
