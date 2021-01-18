package entità;

public class Sala {

	//		Attributi
	private int Codice;
	private String Città;
	private String Provincia;
	private String Indirizzo;
	private int NumeroCivico;
	private int NumeroPosti;
	
	//		Getter/Setter
	public Sala(int codice) {
		Codice = codice;
	}
	
	
	public String getCittà() {
		return Città;
	}
	public void setCittà(String città) {
		Città = città;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public int getNumeroPosti() {
		return NumeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		NumeroPosti = numeroPosti;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public int getNumeroCivico() {
		return NumeroCivico;
	}
	public void setNumeroCivico(int numeroCivico) {
		NumeroCivico = numeroCivico;
	}
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int codice) {
		Codice = codice;
	}
	
}
