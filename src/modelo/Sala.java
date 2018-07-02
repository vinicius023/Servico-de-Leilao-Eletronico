package modelo;
import java.io.File;
import java.util.ArrayList;

import org.jgroups.Address;

public class Sala {

	private long id;
	private ArrayList<Membro> membros = new ArrayList<Membro>();
//	private static ArrayList<Membro> membros = new ArrayList<>();
	private Item item;
	private Membro vencedor;
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
		if (this.membros.isEmpty())
			leiloeiro = membro;
		this.membros.add(membro);
	}

	public ArrayList<Address> getListAdress() {
		ArrayList<Address> enderecos = new ArrayList<Address>();
		for (Membro m : this.membros) {
			enderecos.add(m.getEndereco());
		}
		return enderecos;
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

	public Membro getVencedor() {
		return vencedor;
	}

	public void setVencedor(Membro vencedor) {
		this.vencedor = vencedor;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((leiloeiro == null) ? 0 : leiloeiro.hashCode());
		result = prime * result + ((log == null) ? 0 : log.hashCode());
		result = prime * result + ((membros == null) ? 0 : membros.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (leiloeiro == null) {
			if (other.leiloeiro != null)
				return false;
		} else if (!leiloeiro.equals(other.leiloeiro))
			return false;
		if (log == null) {
			if (other.log != null)
				return false;
		} else if (!log.equals(other.log))
			return false;
		if (membros == null) {
			if (other.membros != null)
				return false;
		} else if (!membros.equals(other.membros))
			return false;
		return true;
	}
}
