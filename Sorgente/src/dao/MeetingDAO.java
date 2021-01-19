package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entit�.Dipendente;
import entit�.Meeting;
import entit�.MeetingFisico;
import entit�.MeetingTelematico;
import entit�.Progetto;
import entit�.Sala;

public interface MeetingDAO {
	

    public void insertMeetingFisico (MeetingFisico mf) throws SQLException;
    public void deleteMeetingFisico (MeetingFisico mf) throws SQLException;
    public void updateMeetingFisico (MeetingFisico mf) throws SQLException;
    
    public void insertMeetingTelematico (MeetingTelematico mt) throws SQLException;
    public void deleteMeetingTelematico (MeetingTelematico mt) throws SQLException;
    public void updateMeetingTelematico (MeetingTelematico mt) throws SQLException;
    
    public void insertPartecipanteMeetingFisico (MeetingFisico mf, Dipendente d) throws SQLException;
    public void deletePartecipanteMeetingFisico(MeetingFisico mf, Dipendente d) throws SQLException;
    public void insertPartecipanteMeetingTelematico (MeetingTelematico mt, Dipendente d) throws SQLException;
    public void deletePartecipanteMeetingTelematico(MeetingTelematico mt, Dipendente d) throws SQLException;
    
	public ArrayList<MeetingFisico> getMeetingFisicoByAttributi(String CodMF, String Data, String OraInizio, String OraFine) throws SQLException;
	public ArrayList<MeetingFisico> getMeetingFisicoByProgetti(String codp, String tipologia, String ambito) throws SQLException;
	public ArrayList<MeetingTelematico> getMeetingTelematicoByAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo)throws SQLException;

	public Sala getSalaMeetingFisico(int codSala) throws SQLException;
	public Progetto getProgettoMeeting(int codp) throws SQLException;
	public ArrayList<Dipendente> getPartecipantiMeetingFisico(int codmf) throws SQLException;
}

