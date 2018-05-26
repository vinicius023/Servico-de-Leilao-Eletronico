package modelo;

import org.jgroups.Address;

public class Membro {
	
	private Login login;
	private Address enderecoAtual;
	
	public Membro() {
		this.login = null;
		this.enderecoAtual = null;
	}
	
	public Membro(Login login, Address enderecoAtual) {
		this.login = login;
		this.enderecoAtual = enderecoAtual;
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
