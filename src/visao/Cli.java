package visao;
import java.io.*;
import java.util.*;

import modelo.Login;

public class Cli {

	Scanner scan = new Scanner(System.in);
	
	public int startLogin() {
		System.out.println("1. Criar novo usuário");
		System.out.println("2. Fazer login");
		return scan.nextInt();
	}
	
	public Login login() {
		scan = new Scanner(System.in);
		System.out.println("Usuário: ");
		String user = scan.nextLine();
		System.out.println("Senha: ");
		String senha = scan.nextLine();
		scan = new Scanner(System.in);
		
		Login login = new Login(user,senha);

		return login;
	}

	public int menuPrincipal() {
		System.out.println("-----_ MENU _-----");
		System.out.println("1. Cadastrar novo item / Criar nova sala");
		System.out.println("2. Entrar numa sala");
		System.out.println("3. Consultar itens leiloados");
		System.out.println("4. Logout");
		System.out.println("0. Sair da aplicação");

		return scan.nextInt();
	}

	public int menuSala() {
		System.out.println("-----_ MENU _-----");
		System.out.println("1. Dar lance");
		System.out.println("2. Ver lances");
		System.out.println("0. Sair da sala");

		return scan.nextInt();
	}

	public int confirmacaoNovoParticipante(String user) {
		System.out.println("Você permite que o usuário: "+user+", entre na sala?");
		System.out.println("1. Sim");
		System.out.println("0. Não");

		return scan.nextInt();
	}
}
