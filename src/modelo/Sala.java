package modelo;
import java.io.File;
import java.util.HashMap;

public class Sala {

	private HashMap<String, Membro> membros;
	private Item item;
	private Membro leiloeiro;
	private File log;
	
	public Sala() {
		
	}
	
	public Sala(HashMap<String, Membro> membros, Item item, Membro leiloeiro, File log) {
		this.membros = membros;
		this.item = item;
		this.leiloeiro = leiloeiro;
		this.log = log;
	}

	public void AddMembro() {
		
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
