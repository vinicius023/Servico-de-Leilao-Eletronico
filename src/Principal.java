import controle.LeilaoControl;
import controle.LoginControl;
import modelo.Login;

public class Principal {
	

	public static void main(String[] args) {
		LoginControl loginCtrl = new LoginControl();
		LeilaoControl leilaoCtrl = new LeilaoControl();
		
		Login login = loginCtrl.login();
//		System.out.println("User: "+login.getUsuario());
//		System.out.println("Pass: "+login.getSenha());
		leilaoCtrl.menuPrincipal();

	}
	
}
