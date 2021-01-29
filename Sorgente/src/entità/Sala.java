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
	public Sala(String città, String prov, String ind, int numCivico, int numPosti) {
		Città = città;
		Provincia = prov;
		Indirizzo = ind;
		NumeroCivico = numCivico;
		NumeroPosti = numPosti;
	}
	public Sala(int codice, String città, String prov, String ind, int numCivico, int numPosti) {
		Codice = codice;
		Città = città;
		Provincia = prov;
		Indirizzo = ind;
		NumeroCivico = numCivico;
		NumeroPosti = numPosti;
	}
	
	
	public String getCittà() {
		return Città;
	}
	public void setCittà(String città) {
		Città = città;
	}
	public String getProvincia() {
		return Provincia;
	}
	public void setProvincia(String provincia) {
		Provincia = provincia;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public int getNumeroCivico() {
		return NumeroCivico;
	}
	public void setNumeroCivico(int numeroCivico) {
		NumeroCivico = numeroCivico;
	}
	public int getNumeroPosti() {
		return NumeroPosti;
	}
	public void setNumeroPosti(int numeroPosti) {
		NumeroPosti = numeroPosti;
	}
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int codice) {
		Codice = codice;
	}
	
}
