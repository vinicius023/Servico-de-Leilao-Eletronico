package modelo;

import java.util.ArrayList;

import org.jgroups.Address;

public class Membro {
	
	private Login login;
	private Address enderecoAtual;
	
	public Membro(Login login, Address enderecoAtual) {
		this.login = login;
		this.enderecoAtual = enderecoAtual;
	}
	
	public static Membro getMembro(ArrayList<Membro> membros, String nameMembro) {
		for (Membro m : membros) {
			if(m.getLogin().getUsuario().equals(nameMembro)) {
				return m;
			}
		}
		return null;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Address getEnderecoAtual() {
		return enderecoAtual;
	}
	public void setEnderecoAtual(Address enderecoAtual) {
		this.enderecoAtual = enderecoAtual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enderecoAtual == null) ? 0 : enderecoAtual.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		if (enderecoAtual == null) {
			if (other.enderecoAtual != null)
				return false;
		} else if (!enderecoAtual.equals(other.enderecoAtual))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
}
