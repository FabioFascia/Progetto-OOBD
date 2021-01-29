package entità;

import java.util.ArrayList;

public class Dipendente {

	//		Attributi
	private String CodF;
	private String Nome;
	private String Cognome;
	private float Salario;
	private int Valutazione;
	
	//		Relazioni
	public ArrayList<Partecipante> Progetti;
	
	//		Costruttori
	public Dipendente(String codf) {
		super();
		CodF = codf;
	}	
	public Dipendente(String codF, String nome, String cognome, float salario) {
		super();
		CodF = codF;
		Nome = nome;
		Cognome = cognome;
		Salario = salario;
	}
	public Dipendente(String codF, String nome, String cognome, float salario, int valutazione) {
		super();
		CodF = codF;
		Nome = nome;
		Cognome = cognome;
		Salario = salario;
		Valutazione = valutazione;
	}
	
	//		Getter/Setter
	public String getCodF() {
		return CodF;
	}
	public void setCodF(String codF) {
		CodF = codF;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public float getSalario() {
		return Salario;
	}
	public void setSalario(float salario) {
		Salario = salario;
	}
	public int getValutazione() {
		return Valutazione;
	}
	public void setValutazione(int valutazione) {
		Valutazione = valutazione;
	}
	
	
}
