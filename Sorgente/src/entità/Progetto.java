package entità;

import java.util.ArrayList;

public class Progetto {

	//		Attributi
	private int Codice;
	private String Tipologia;
	private String Descrizione;
	private ArrayList<String> Ambiti = new ArrayList<String>();
	
	//		Relazioni
	private Partecipante ProjectManager;
	private ArrayList<Partecipante> Partecipanti = new ArrayList<Partecipante>();
	
	
	public Progetto(int codice, String tipologia) {
		Codice = codice;
		Tipologia = tipologia;
	}
	public Progetto(String tipologia, String descrizione) {
		Tipologia = tipologia;
		Descrizione = descrizione;
	}
	public Progetto(int codice, String tipologia, String descrizione) {
		Codice = codice;
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
		return Partecipanti;
	}
	public void setPartecipanti(ArrayList<Partecipante> partecipanti) {
		Partecipanti = partecipanti;
	}
	public void addPartecipante(Dipendente d, String ruolo) {
		Partecipante par = new Partecipante(d, this, ruolo);
		Partecipanti.add(par);
	}
	public Dipendente getProjectManager() {
		return ProjectManager.getDipendente();
	}
	public void setProjectManager(Dipendente d) {
		ProjectManager = new Partecipante(d, this, "Project Manager");
	}

}
