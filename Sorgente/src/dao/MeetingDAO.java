package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entità.Dipendente;
import entità.Meeting;
import entità.MeetingFisico;
import entità.MeetingTelematico;
import entità.Progetto;
import entità.Sala;

public interface MeetingDAO {
	

    public void insertMeeting (MeetingFisico mf) throws SQLException;
    public void deleteMeeting (MeetingFisico mf) throws SQLException;
    public void updateMeeting (MeetingFisico mf) throws SQLException;
    
    public void insertMeeting (MeetingTelematico mt) throws SQLException;
    public void deleteMeeting (MeetingTelematico mt) throws SQLException;
    public void updateMeeting (MeetingTelematico mt) throws SQLException;
    
    public void insertPartecipanteMeeting (MeetingFisico mf, Dipendente d) throws SQLException;
    public void deletePartecipanteMeeting(MeetingFisico mf, Dipendente d) throws SQLException;
    public void insertPartecipanteMeeting (MeetingTelematico mt, Dipendente d) throws SQLException;
    public void deletePartecipanteMeeting(MeetingTelematico mt, Dipendente d) throws SQLException;
    
	public ArrayList<MeetingFisico> getMeetingFisicoByAttributi(String CodMF, String Data, String OraInizio, String OraFine) throws SQLException;
	public ArrayList<MeetingFisico> getMeetingFisicoByProgetti(String codp, String tipologia, String ambito) throws SQLException;
	public ArrayList<MeetingFisico> getMeetingFisicoBySala(String città, String provincia, String indirizzo, String numCivico, String minPosti, String maxPosti) throws SQLException;
	public ArrayList<MeetingTelematico> getMeetingTelematicoByAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo)throws SQLException;
	public ArrayList<MeetingTelematico> getMeetingTelematicoByProgetti(String codp, String tipologia, String ambito) throws SQLException;
	
	public Sala getSalaMeetingFisico(int codSala) throws SQLException;
	public Progetto getProgettoMeeting(int codp) throws SQLException;
	public ArrayList<Dipendente> getPartecipantiMeetingFisico(int codmf) throws SQLException;
	public ArrayList<Dipendente> getPartecipantiMeetingTelematico(int codmt) throws SQLException;
}

