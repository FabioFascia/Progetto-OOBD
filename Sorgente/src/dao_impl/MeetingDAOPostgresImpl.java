package dao_impl;

import dao.MeetingDAO;
import entità.Meeting;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;


public class MeetingDAOPostgresImpl implements MeetingDAO {
	
	private Connection connection;
	private PreparedStatement insertMeetingFisicoPS; 
	private PreparedStatement insertMeetingTelematicoPS;
	private PreparedStatement updateMeetingFisicoPS;
	private PreparedStatement updateMeetingTelematicoPS;
	private PreparedStatement deleteMeetingFisicoPS;
	private PreparedStatement deleteMeetingTelematicoPS;
	private PreparedStatement getMeetingFisicoByCodiceMeeting;
	private PreparedStatement getMeetingFisicoByData;
	private PreparedStatement getMeetingFisicoByOrario;
	private PreparedStatement getMeetingFisicoByCodiceSala;
	private PreparedStatement getMeetingTelematicoByCodiceMeeting;
	private PreparedStatement getMeetingTelematicoByData;
	private PreparedStatement getMeetingTelematicoByOrario;
	private PreparedStatement getMeetingTelematicoByPiattaforma;
	private PreparedStatement getMeetingTelematicoByNumeroLimite;
	
	public  MeetingDAOPostgresImpl (Connection connection) throws SQLException{
		
		this.connection = connection;
		
		insertMeetingFisicoPS = connection.prepareStatement("CALL Insert_MeetingF(?, ?, ?, ?);");
		insertMeetingTelematicoPS = connection.prepareStatement("CALL Insert_MeetingT(?, ?, ?, ?, ?, ?);");
		
		updateMeetingFisicoPS = connection.prepareStatement("UPDATE MEETINGF SET Data = ?, oraI = ?, oraF = ?, CodSala = ? WHERE CodMF = ?;");
		updateMeetingTelematicoPS = connection.prepareStatement("UPDATE MEETINGT SET Data = ?, oraI = ?, oraf = ?, Piattaforma = ?, NumLimite = ? WHERE CodMT = ?;");
		
		deleteMeetingFisicoPS = connection.prepareStatement("DELETE FROM MEETINGF WHERE CodMF = ?;");
		deleteMeetingTelematicoPS = connection.prepareStatement("DELETE FROM MEETING WHERE CodMT = ?;");
		
		getMeetingFisicoByCodiceMeeting = connection.prepareStatement("SELECT * FROM MEETINGF WHERE CodMF = ? ORDER BY CodMF;");
		getMeetingTelematicoByCodiceMeeting = connection.prepareStatement("SELECT * FROM MEETINGT WHERE CodMT = ? ORDER BY CodMT;");
		
		getMeetingFisicoByData = connection.prepareStatement("SELECT * FROM MEETINGF WHERE Data = ?;");
		getMeetingTelematicoByData = connection.prepareStatement("SELECT * FROM MEETINGF WHERE Data = ?;");
		
		getMeetingFisicoByOrario = connection.prepareStatement("SELECT * FROM MEETINGF WHERE");
	}
	
    public void insertMeetingFisico (Date Data, Time  OraI, Time OraF, int CodS) throws SQLException {
    	
    	insertMeetingFisicoPS.setDate(1, Data);
    	insertMeetingFisicoPS.setTime(2, OraI);
    	insertMeetingFisicoPS.setTime(3, OraF);
    	insertMeetingFisicoPS.setInt(4, CodS);
    	
    	insertMeetingFisicoPS.execute();
    	
    }
    
    public void insertMeetingTelematico (Date Data, Time  OraI, Time OraF, String Piattaforma, int NumLimite) throws SQLException{
    	
    	insertMeetingTelematicoPS.setDate(1, Data);
    	insertMeetingTelematicoPS.setTime(2, OraI);
    	insertMeetingTelematicoPS.setTime(3, OraF);
    	insertMeetingTelematicoPS.setString(4, Piattaforma);
    	insertMeetingTelematicoPS.setInt(5, NumLimite);
    }

    public void updateMeetingFisico (Date Data, Time OraI, Time OraF) throws SQLException{
    	
    	updateMeetingFisicoPS.setDate(1, Data);
    	updateMeetingFisicoPS.setTime(2, OraI);
    	updateMeetingFisicoPS.setTime(3, OraF);
    	
    	updateMeetingFisicoPS.execute();
    	
    }
    
    public void updateMeetingTelematico (Date Data, Time OraI, Time OraF, String Piattaforma, int NumLimite) throws SQLException{
    	
    	updateMeetingTelematicoPS.setDate(1, Data);
    	updateMeetingTelematicoPS.setTime(2, OraI);
    	updateMeetingTelematicoPS.setTime(3, OraF);
    	updateMeetingTelematicoPS.setString(4, Piattaforma);
    	updateMeetingTelematicoPS.setInt(5, NumLimite);
    	
    	updateMeetingTelematicoPS.execute();
    	
    }
    

    public void deleteMeetingFisico (int CodMF) throws SQLException {
    	
    	deleteMeetingFisicoPS.setInt(1, CodMF);
    	deleteMeetingFisicoPS.execute();
    }
    
    public void deleteMeetingTelematico (int CodMT) throws SQLException {
    	
    	deleteMeetingTelematicoPS.setInt(1, CodMT);
    	deleteMeetingTelematicoPS.execute();
;    }
    }
