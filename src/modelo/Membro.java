package modelo;

import java.util.ArrayList;

import org.jgroups.Address;

public class Membro {
	
	private String usuario;
	private Address endereco;
	
	public Membro(String usuario, Address endereco) {
		this.usuario = usuario;
		this.endereco = endereco;
	}
	
	public Membro(String usuario) {
		this.usuario = usuario;
	}
	
	public static Membro getMembro(ArrayList<Membro> membros, String nameMembro) {
		for (Membro m : membros) {
			if(m.getUsuario().equals(nameMembro)) {
				return m;
			}
		}
		return null;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Address getEndereco() {
		return endereco;
	}
	public void setEndereco(Address endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Membro other = (Membro) obj;
		
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
