package entità;

import java.sql.Date;
import java.sql.Time;

public class MeetingFisico implements Meeting {

	//		Attributi
	private int CodMF;
    private Date Data;
    private Time OraI;
    private Time OraF;

	//		Relazioni
	private Sala salaRiunioni;
	private Progetto ArgomentoMeeting;
	
	//		Getter/Setter
	public int getCodMeeting() {
		return CodMF;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public Time getOraI() {
		return OraI;
	}
	public void setOraI(Time oraI) {
		OraI = oraI;
	}
	public Time getOraF() {
		return OraF;
	}
	public void setOraF(Time oraF) {
		OraF = oraF;
	}
	public Progetto getArgomentoMeeting() {
		return ArgomentoMeeting;
	}
	public void setArgomentoMeeting(Progetto argomentoMeeting) {
		ArgomentoMeeting = argomentoMeeting;
	}
	public void setCodMeeting(int CodMeeting) {
		CodMF = CodMeeting;
	}
	public Sala getSalaRiunioni() {
		return salaRiunioni;
	}
	public void setSalaRiunioni(Sala salaRiunioni) {
		this.salaRiunioni = salaRiunioni;
	}
	
	
	
}
