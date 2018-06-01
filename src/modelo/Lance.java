package modelo;

import java.util.Date;

public class Lance {

	private Date data;
	private double valor;
	private Item item;
	private Membro membro;
	
	public Lance(Date data, double valor, Item item, Membro membro) {
		this.data = data;
		this.valor = valor;
		this.item = item;
		this.membro = membro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Membro getMembro() {
		return membro;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}
}
