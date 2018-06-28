package controle;

import java.util.ArrayList;

import modelo.Login;
import visao.LoginCli;

public class LoginControl {
	
	LoginCli cli = new LoginCli();
	LoginFile json = new LoginFile();
	
	public Login login() {
		Login login = new Login();
		
		if(criarLogin()) {
			login = novoUsuario();
			System.out.println("Cadastro e Login realizado com sucesso!");
		} else {
			login = realizarLogin();
			System.out.println("Login realizado com sucesso!");
		}
		
		return login;
	}
	
	public boolean criarLogin() {
	int i = 1;
	 	while(true) {
			System.out.println("\n---_ Bem-vindo _---");
			i = cli.startLogin();
			if ((i == 1) || (i == 2)) {
				if (i == 1)	return true;
				else return false;
			}else if(i == 0) {
				System.exit(0);
			}
			System.out.println("Digite um valor válido! (1-2)");
		}
	}
	
	public Login novoUsuario() {
		Login login = new Login();
		String username;
		while(true) {
			System.out.println("\n---_ Cadastro Novo usuário _---");
			login = cli.login();
			username = login.getUsuario();
			// Username comeca com letra
			if (Character.isLetter(username.charAt(0))) { 
				if(!json.existeLogin(login)) {
					json.writeLogin(login);
					return login;
				} else
					System.out.println("Nome do usuário já cadastrado! Tente novamente.");
			}
			System.out.println("Nome do usuário deve começar com uma letra! (a-z;A-Z)");
		}
	}
	
	public Login realizarLogin() {
		Login login = new Login();
		// logins = lista de usuarios do arquivo
		ArrayList<Login> logins = json.readLogins();
		
		while(true) {
			System.out.println("\n---_ Realizar Login _---");
			if (logins.isEmpty()) {
				System.out.println("Não existe usuário cadastrado. Realize o cadastro de um novo usuário.");
				login = novoUsuario();
			} else
				login = cli.login();
			
			for (Login l : logins) {
				if((login.getUsuario().equals(l.getUsuario())) && (login.getSenha().equals(l.getSenha())))	
					return login;
			}
			// login incorreto
			System.out.println("Nome de usuário e/ou senha, incorreto(s)! Digite novamente.");
		}
	}

}
