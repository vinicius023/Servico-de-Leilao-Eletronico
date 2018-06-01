package visao;

import java.util.Scanner;

public class LeilaoCli {
	
	Scanner scan = new Scanner(System.in);
	
	public int menuPrincipal() {
		System.out.println("\n-----_ MENU _-----");
		System.out.println("1. Criar nova sala (Cadastrar novo item)");
		System.out.println("2. Entrar numa sala");
		System.out.println("3. Consultar itens leiloados");
		System.out.println("4. Logout");
		System.out.println("0. Sair da aplicação");

		return scan.nextInt();
	}
	
	public int menuSala() {
		System.out.println("\n-----_ SALA _-----");
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
	
	public boolean yesno(String str) {
		String yesNo;
		while(true) {
			scan = new Scanner(System.in);
			System.out.println(str);
			yesNo = scan.nextLine();
			scan = new Scanner(System.in);
	
			yesNo = yesNo.toLowerCase();
			if (yesNo.equals("y")) 
				return true;
			else if (yesNo.equals("n"))
				return false;
			else
				System.out.println("Digite uma opção válida! (y/n)");
		}
	}
}
