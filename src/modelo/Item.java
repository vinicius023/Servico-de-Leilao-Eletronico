package modelo;
import java.util.ArrayList;

public class Item {
	
	private boolean status;
	private String nome;
	private double maiorValor;
	private Membro proprietario;
	// Rever esse hashMap, agora existe a classe Lance
	private ArrayList<Lance> lances = new ArrayList<Lance>();
	
	public Item(String nome, Membro membro) {
		this.status = false;
		this.nome = nome;
		this.maiorValor = 0;
		this.proprietario = membro;
	}
	
	public Item(boolean status, String nome, double valor, Membro proprietario) {
		this.status = status;
		this.nome = nome;
		this.maiorValor = valor;
		this.proprietario = proprietario;
	}

	public static Item getItem(ArrayList<Sala> salas, String nameItem) {
		for (Sala s : salas) {
			if(s.getItem().getNome().equals(nameItem)) {
				return s.getItem();
			}
		}
		return null;
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

	public double getMaiorValor() {
		return maiorValor;
	}

	public void setMaiorValor(double maiorValor) {
		this.maiorValor = maiorValor;
	}

	public Membro getProprietario() {
		return proprietario;
	}

	public void setProprietario(Membro proprietario) {
		this.proprietario = proprietario;
	}

	public ArrayList<Lance> getLances() {
		return lances;
	}

	public void setLances(ArrayList<Lance> lances) {
		this.lances = lances;
	}

	
}
