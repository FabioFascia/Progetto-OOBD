package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MeetingTelematico extends Meeting {

	//		Attributi
	private String Piattaforma;
	private int NumeroLimite;
	
	//	Getter/Setter
	public MeetingTelematico(Date data, Time oraI, Time oraF, Progetto p, String piattaforma, int numLimite) {
		Data = data;
		OraInizio = oraI;
		OraFine = oraF;
		ProgettoMeeting = p;
		Piattaforma = piattaforma;
		NumeroLimite = numLimite;
	}
	public MeetingTelematico(int codice, Date data, Time oraI, Time oraF, Progetto p, String piattaforma, int numLimite) {
		Codice = codice;
		Data = data;
		OraInizio = oraI;
		OraFine = oraF;
		ProgettoMeeting = p;
		Piattaforma = piattaforma;
		NumeroLimite = numLimite;
	}
	public String getPiattaforma() {
		return Piattaforma;
	}
	public void setPiattaforma(String piattaforma) {
		Piattaforma = piattaforma;
	}
	public int getNumeroLimite() {
		return NumeroLimite;
	}
	public void setNumeroLimite(int numeroLimite) {
		NumeroLimite = numeroLimite;
	}

}
