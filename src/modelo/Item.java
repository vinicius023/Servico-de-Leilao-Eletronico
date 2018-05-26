package modelo;
import java.util.HashMap;

public class Item {
	
	private boolean status;
	private String nome;
	private double valor;
	private Membro proprietario;
	private HashMap<Double, Membro> lances; 
	
	public Item() {
		this.status = false;
		this.nome = "";
		this.valor = 0;
	}
	
	public Item(boolean status, String nome, double valor, Membro proprietario) {
		this.status = status;
		this.nome = nome;
		this.valor = valor;
		this.proprietario = proprietario;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Membro getProprietario() {
		return proprietario;
	}

	public void setProprietario(Membro proprietario) {
		this.proprietario = proprietario;
	}

	public HashMap<Double, Membro> getLances() {
		return lances;
	}

	public void setLances(HashMap<Double, Membro> lances) {
		this.lances = lances;
	}
	
	
}
