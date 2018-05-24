package users;

import java.util.ArrayList;
import java.util.HashMap;

public class Membro {
	
	private String usuario;
	private String senha;
	private Adress enderecoAtual;
	
	public Membro() {
		this.usuario = "";
		this.senha = "";
		this.enderecoAtual = new Adress();
	}
	public Membro(String usuario, String senha, Adress enderecoAtual) {
		super();
		this.usuario = usuario;
		this.senha = senha;
		this.enderecoAtual = enderecoAtual;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Adress getEnderecoAtual() {
		return enderecoAtual;
	}
	public void setEnderecoAtual(Adress enderecoAtual) {
		this.enderecoAtual = enderecoAtual;
	}
	
	
	
}
