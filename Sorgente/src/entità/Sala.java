package entit�;

public class Sala {

	//		Attributi
	private int Codice;
	private String Citt�;
	private String Provincia;
	private String Indirizzo;
	private int NumeroCivico;
	private int NumeroPosti;
	
	//		Getter/Setter
	public Sala(int codice) {
		Codice = codice;
	}
	
	
	public String getCitt�() {
		return Citt�;
	}
	public void setCitt�(String citt�) {
		Citt� = citt�;
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
