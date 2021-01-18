package entità;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public interface Meeting {
	
	public int getCodice();
	public void setCodice(int CodMeeting);
	public Date getData();
	public void setData(Date date);
	public Time getOraI();
	public void setOraI(Time time);
	public Time getOraF();
	public void setOraF(Time oraF);
	public ArrayList<Dipendente> getPartecipanti();
	public void addPartecipante(Dipendente d);
}
