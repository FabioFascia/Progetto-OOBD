package entità;

import java.sql.Date;
import java.sql.Time;

public class MeetingFisico extends Meeting {

	//		Relazioni
	private Sala SalaRiunioni;
	
	//		Getter/Setter
	public MeetingFisico(Date data, Time oraI, Time oraF, Progetto p, Sala s) {
		Data = data;
		OraInizio = oraI;
		OraFine = oraF;
		ProgettoMeeting = p;
		SalaRiunioni = s;
	}
	public MeetingFisico(int codice, Date data, Time oraI, Time oraF, Progetto p, Sala s) {
		Codice = codice;
		Data = data;
		OraInizio = oraI;
		OraFine = oraF;
		ProgettoMeeting = p;
		SalaRiunioni = s;
	}
	public Sala getSalaRiunioni() {
		return SalaRiunioni;
	}
	public void setSalaRiunioni(Sala salaRiunioni) {
		SalaRiunioni = salaRiunioni;
	}
	
}
