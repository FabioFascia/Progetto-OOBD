package entit�;

import java.sql.Date;
import java.sql.Time;

public interface Meeting {
	
	public int getCodice();
	public void setCodice(int CodMeeting);
	public Date getData();
	public void setData(Date date);
	public Time getOraI();
	public void setOraI(Time time);
	public Time getOraF();
	public void setOraF(Time oraF);
}
