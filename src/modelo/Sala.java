package modelo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.jgroups.Address;

public class Sala {

	private int id;
	private HashMap<String, Membro> membros;
//	private static ArrayList<Membro> membros = new ArrayList<>();
	private Item item;
	private Membro leiloeiro;
	private File log;
	
	public Sala(int id, Membro membro, Item item) {
		this.id = id;
		this.leiloeiro = membro;
		this.item = item;
	}
	
	public Sala(HashMap<String, Membro> membros, Item item, Membro leiloeiro) {
		this.membros = membros;
		this.item = item;
		this.leiloeiro = leiloeiro;
		this.log = null;
	}

	public void AddMembro() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<String, Membro> getMembros() {
		return membros;
	}

	public void setMembros(HashMap<String, Membro> membros) {
		this.membros = membros;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Membro getLeiloeiro() {
		return leiloeiro;
	}

	public void setLeiloeiro(Membro leiloeiro) {
		this.leiloeiro = leiloeiro;
	}

	public File getLog() {
		return log;
	}

	public void setLog(File log) {
		this.log = log;
	}

}
