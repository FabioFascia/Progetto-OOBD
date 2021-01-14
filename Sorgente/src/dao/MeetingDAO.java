package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entit�.Meeting;
import entit�.MeetingFisico;
import entit�.MeetingTelematico;

public interface MeetingDAO {
	
	public  ArrayList<MeetingFisico> getMeetingFisicoByAttributi(String CodMF, String Data, String OraInizio, String OraFine, String CodSala) throws SQLException;
	public ArrayList<MeetingTelematico> getMeetingTelematicoByAttributi(String CodMT, String Data, String OraInizio, String OraFine, String Piattaforma, String NumMassimo)throws SQLException;
}
