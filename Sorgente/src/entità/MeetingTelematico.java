package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MeetingTelematico extends Meeting {

	//		Attributi
	private String Piattaforma;
	private int NumeroLimite;
	
	//	Getter/Setter
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
