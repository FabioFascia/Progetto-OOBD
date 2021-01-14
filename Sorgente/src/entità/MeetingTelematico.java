package entità;

public class MeetingTelematico implements Meeting {

	//		Attributi
	private int Codice;
	private String Data;
	private String OraI;
	private String OraF;
	private String Piattaforma;
	private int NumeroLimite;
	
	//		Relazioni
	private Progetto ArgomentoMeeting;
	
	//	Getter/Setter
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int codice) {
		Codice = codice;
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
}
