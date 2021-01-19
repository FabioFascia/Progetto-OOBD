package entità;

import java.util.ArrayList;

public class Progetto {

	//		Attributi
	private int Codice;
	private String Tipologia;
	private String Descrizione;
	private ArrayList<String> Ambiti = new ArrayList<String>();
	
	//		Relazioni
	private ArrayList<Partecipante> Partecipanti = new ArrayList<Partecipante>();
	
	public Progetto() {}
	public Progetto(int codice) {
		Codice = codice;
	}
	public Progetto(int codice, String tipologia) {
		Codice = codice;
		Tipologia = tipologia;
	}
	public Progetto(String tipologia, String descrizione) {
		Tipologia = tipologia;
		Descrizione = descrizione;
	}
	public int getCodice() {
		return Codice;
	}
	public void setCodice(int codice) {
		Codice = codice;
	}
	public String getTipologia() {
		return Tipologia;
	}
	public void setTipologia(String tipologia) {
		Tipologia = tipologia;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String desc) {
		Descrizione = desc;
	}
	public ArrayList<String> getAmbiti() {
		return Ambiti;
	}
	public void setAmbiti(ArrayList<String> ambiti) {
		Ambiti = ambiti;
	}
	public void addAmbito(String ambito) {
		Ambiti.add(ambito);
	}
	public ArrayList<Partecipante> getPartecipanti() {
		ArrayList<Partecipante> par = new ArrayList<Partecipante>(Partecipanti);
		par.remove(0);
		
		return par;
	}
	public void setPartecipanti(ArrayList<Partecipante> partecipanti) {
		Partecipanti = partecipanti;
	}
	public void addPartecipante(Dipendente d, String ruolo) {
		Partecipante par = new Partecipante(d, ruolo);
		par.setProgetto(this);
		Partecipanti.add(par);
	}
	public Dipendente getProjectManager() {
		return Partecipanti.get(0).getDipendente();
	}
	public void setProjectManager(Dipendente d) {
		Partecipante par = new Partecipante(d, this, "Project Manager");
		if(Partecipanti.size() == 0)
			Partecipanti.add(par);
		else
			Partecipanti.set(0, par);
	}

}
