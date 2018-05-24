import java.util.ArrayList;

import users.Membro;

public class Item {
	
	private boolean status;
	private String nome;
	private double valor;
	private Membro proprietario;
	private HashMap<double, Membro> lances; 
	
	public Item() {
		
	}
	
	public Item(boolean status, String nome, double valor, Membro proprietario) {
		this.status = status;
		this.nome = nome;
		this.valor = valor;
		this.proprietario = proprietario;
	}
}
