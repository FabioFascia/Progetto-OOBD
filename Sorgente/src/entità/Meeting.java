package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface Meeting {
	
	public int getCodice();
	public void setCodice(int CodMeeting);
	public Date getData();
	public void setData(Date date);
	public Time getOraInizio();
	public void setOraInizio(Time time);
	public Time getOraFine();
	public void setOraFine(Time oraF);
	public ArrayList<Dipendente> getPartecipanti();
	public void addPartecipante(Dipendente d);
}
