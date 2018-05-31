package visao;
import java.util.*;

import modelo.Login;

public class LoginCli {

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
}
