package entità;

public class Partecipante {
	
	//		Attributi
	private String Ruolo;
	
	//		Relazione
	private Dipendente Dip;
	private Progetto Prog;
	
	//		Getter/Setter
	public Partecipante(Dipendente d, Progetto p, String r) {
		Dip = d;
		Prog = p;
		Ruolo = r;
	}
	public Dipendente getDipendente() {
		return Dip;
	}
	public void setDipendente(Dipendente d) {
		Dip = d;
	}
	public Progetto getProgetto() {
		return Prog;
	}
	public void setProgetto(Progetto p) {
		Prog = p;
	}
	public String getRuolo() {
		return Ruolo;
	}
	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
}
