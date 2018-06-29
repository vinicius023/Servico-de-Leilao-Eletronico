package modelo;
import java.io.File;
import java.util.ArrayList;

public class Sala {

	private long id;
	private ArrayList<Membro> membros;
//	private static ArrayList<Membro> membros = new ArrayList<>();
	private Item item;
	private Membro leiloeiro;
	private File log;
	
	public Sala(long id, Membro membro, Item item) {
		this.id = id;
		this.leiloeiro = membro;
		this.item = item;
	}
	
	public Sala(ArrayList<Membro> membros, Item item, Membro leiloeiro) {
		this.membros = membros;
		this.item = item;
		this.leiloeiro = leiloeiro;
		this.log = null;
	}

	public void AddMembro(Membro membro) {
		this.membros.add(membro);
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Membro> getMembros() {
		return membros;
	}

	public void setMembros(ArrayList<Membro> membros) {
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
