package entit�;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Meeting {
	
	//		Attributi
	private int Codice;
    private Date Data;
    private Time OraInizio;
    private Time OraFine;
    
	//		Relazioni
	private Progetto ProgettoMeeting;
	private ArrayList<Dipendente> Partecipanti;
	
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int CodMeeting) {
		Codice = CodMeeting;
	}
	public Date getData() {
		return Data;
	}
	public void setData(Date date) {
		Data = date;
	}
	public Time getOraInizio() {
		return OraInizio;
	}
	public void setOraInizio(Time oraI) {
		OraInizio = oraI;
	}
	public Time getOraFine() {
		return OraFine;
	}
	public void setOraFine(Time oraF) {
		OraFine = oraF;
	}
	public Progetto getProgettoMeeting() {
		return ProgettoMeeting;
	}
	public void setProgettoMeeting(Progetto progetto) {
		ProgettoMeeting = progetto;
	}
	public ArrayList<Dipendente> getPartecipanti(){
		return Partecipanti;
	}
	public void setPartecipanti(ArrayList<Dipendente> lista) {
		Partecipanti = lista;
	}
	public void addPartecipante(Dipendente d) {
		Partecipanti.add(d);
	}
}
