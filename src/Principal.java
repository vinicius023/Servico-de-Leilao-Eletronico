import java.io.*;
import java.util.*;

public class Principal {
	

	public static void main(String[] args) {
		Cli cli = new Cli();
		int op;
		op = cli.startLogin();
		System.out.println(op);
		ArrayList<String> login;
		login = cli.login();
		System.out.println(login.toString());
		op = cli.menuPrincipal();
		System.out.println(op);
		op = cli.menuSala();
		System.out.println(op);
		op = cli.confirmacaoNovoParticipante(login.get(0));
		System.out.println(op);
	}
	
}
