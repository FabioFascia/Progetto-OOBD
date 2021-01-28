package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class MeetingFisico extends Meeting {

	//		Relazioni
	private Sala salaRiunioni;
	
	//		Getter/Setter
	public Sala getSalaRiunioni() {
		return salaRiunioni;
	}
	public void setSalaRiunioni(Sala salaRiunioni) {
		this.salaRiunioni = salaRiunioni;
	}
	
}
