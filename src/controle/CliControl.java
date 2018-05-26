package controle;

import java.util.ArrayList;

import modelo.Login;
import visao.Cli;

public class CliControl {
	
	Cli cli = new Cli();
	FileJson json = new FileJson();
	
	public Login login() {
		Login login = new Login();
		
		if(criarLogin()) {
			login = novoUsuario();
		} else {
			login = novoUsuario();
		}
		
		return login;
	}
	public boolean criarLogin() {
	int i = 1;
		{
			System.out.println("\n---_ Bem-vindo _---");
			i = cli.startLogin();
			if ((i == 1) || (i == 2)) {
				if (i == 1)	return true;
				else return false;
			}
			System.out.println("Digite um valor válido! (1-2)");
		} while(true);
	}
	
	public Login novoUsuario() {
		Login login = new Login();
		String username;
		{
			System.out.println("\n---_ Cadastro Novo usuário _---");
			login = cli.login();
			username = login.getUsuario();
			// Username comeca com letra
			if (Character.isLetter(username.charAt(0))) { 
				if(!json.existeLogin(login)) 
					return login;
				else
					System.out.println("Nome do usuário já cadastrado! Tente novamente.");
			}
			System.out.println("Nome do usuário deve começar com uma letra! (a-z;A-Z)");
		} while(true);
	}
	
	public Login realizarLogin() {
		Login login = new Login();
		// logins = lista de usuarios do arquivo
		ArrayList<Login> logins = json.readLogins();
		
		{
			System.out.println("\n---_ Realizar Login _---");
			if (logins.isEmpty()) {
				System.out.println("Não existe usuário cadastrado. Realize o cadastro de um novo usuário.");
				login = novoUsuario();
			} else
				login = cli.login();
			
			for (Login l : logins) {
				if((login.getUsuario() == l.getUsuario()) && (login.getSenha() == l.getSenha()))	
					return login;
			}
			// login incorreto
			System.out.println("Nome do usuário e/ou senha, incorreto(s)! Digite novamente.");
		} while(true);
	}

}
