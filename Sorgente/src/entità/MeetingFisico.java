package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MeetingFisico implements Meeting {

	//		Attributi
	private int Codice;
    private Date Data;
    private Time OraI;
    private Time OraF;

	//		Relazioni
	private Sala salaRiunioni;
	private Progetto ArgomentoMeeting;
	private ArrayList<Partecipante> Partecipanti;
	
	//		Getter/Setter
	public int getCodice() {
		return Codice;
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
	public void setCodice(int CodMeeting) {
		Codice = CodMeeting;
	}
	public Sala getSalaRiunioni() {
		return salaRiunioni;
	}
	public void setSalaRiunioni(Sala salaRiunioni) {
		this.salaRiunioni = salaRiunioni;
	}
	public ArrayList<Partecipante> getPartecipanti() {
		return Partecipanti;
	}
	public void setPartecipanti(ArrayList<Partecipante> partecipanti) {
		Partecipanti = partecipanti;
	}
	
	
	
}
