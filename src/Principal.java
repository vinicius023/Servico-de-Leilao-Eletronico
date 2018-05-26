import controle.CliControl;
import modelo.Login;

public class Principal {
	

	public static void main(String[] args) {
		CliControl cli = new CliControl();
		
		Login login = new Login();
		
		login = cli.login();
		
		System.out.println("User: "+login.getUsuario());
		System.out.println("Pass: "+login.getSenha());
//		Cli cli = new Cli();
//		int op;
//		op = cli.startLogin();
//		System.out.println(op);
//		Login login = cli.login();
//		System.out.println(login.toString());
//		op = cli.menuPrincipal();
//		System.out.println(op);
//		op = cli.menuSala();
//		System.out.println(op);
//		op = cli.confirmacaoNovoParticipante(login.getUsuario());
//		System.out.println(op);
	}
	
}
