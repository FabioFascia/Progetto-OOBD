package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entit�.Meeting;
import entit�.MeetingFisico;
import entit�.MeetingTelematico;
import entit�.Sala;

public interface MeetingDAO {
	
    public void insertMeetingFisico (MeetingFisico mf) throws SQLException;
    public void insertMeetingTelematico (MeetingTelematico mt) throws SQLException;
    public void updateMeetingFisico (MeetingFisico mf) throws SQLException;
    public void updateMeetingTelematico (MeetingTelematico mt) throws SQLException;
    public void deleteMeetingFisico (MeetingFisico mf) throws SQLException;
    public void deleteMeetingTelematico (MeetingTelematico mt) throws SQLException;
	
	public  ArrayList<MeetingFisico> getMeetingFisicoByAttributi(String CodMF, String Data, String OraInizio, String OraFine) throws SQLException;
	public ArrayList<MeetingTelematico> getMeetingTelematicoByAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo)throws SQLException;

	public Sala getSalaMeetingFisico(int codSala) throws SQLException;
}
