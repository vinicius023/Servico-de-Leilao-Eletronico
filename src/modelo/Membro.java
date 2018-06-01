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
	
	
	
}
