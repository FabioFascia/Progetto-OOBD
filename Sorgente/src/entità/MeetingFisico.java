package entità;

public class MeetingFisico implements Meeting {

	//		Attributi
	private int Codice;
    private String Data;
    private String OraI;
    private String OraF;

	//		Relazioni
	private Sala salaRiunioni;
	private Progetto ArgomentoMeeting;
	
	//		Getter/Setter
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int codice) {
		Codice = codice;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getOraI() {
		return OraI;
	}
	public void setOraI(String oraI) {
		OraI = oraI;
	}
	public String getOraF() {
		return OraF;
	}
	public void setOraF(String oraF) {
		OraF = oraF;
	}
	public Progetto getArgomentoMeeting() {
		return ArgomentoMeeting;
	}
	public void setArgomentoMeeting(Progetto argomentoMeeting) {
		ArgomentoMeeting = argomentoMeeting;
	}
	public Sala getSalaRiunioni() {
		return salaRiunioni;
	}
	public void setSalaRiunioni(Sala salaRiunioni) {
		this.salaRiunioni = salaRiunioni;
	}
}
