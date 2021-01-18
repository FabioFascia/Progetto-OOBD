package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MeetingFisico implements Meeting {

	//		Attributi
	private int Codice;
    private Date Data;
    private Time OraInizio;
    private Time OraFine;

	//		Relazioni
	private Sala salaRiunioni;
	private Progetto ProgettoMeeting;
	private ArrayList<Dipendente> Partecipanti;
	
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
	public Time getOraInizio() {
		return OraInizio;
	}
	public void setOraInizio(Time oraInizio) {
		OraInizio = oraInizio;
	}
	public Time getOraFine() {
		return OraFine;
	}
	public void setOraFine(Time oraFine) {
		OraFine = oraFine;
	}
	public Progetto getProgettoMeeting() {
		return ProgettoMeeting;
	}
	public void setProgettoMeeting(Progetto argomentoMeeting) {
		ProgettoMeeting = argomentoMeeting;
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
	public ArrayList<Dipendente> getPartecipanti() {
		return Partecipanti;
	}
	public void setPartecipanti(ArrayList<Dipendente> partecipanti) {
		Partecipanti = partecipanti;
	}
	public void addPartecipante(Dipendente d) {
		Partecipanti.add(d);
	}
	
	
}
