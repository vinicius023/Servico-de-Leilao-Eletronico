import org.jgroups.JChannel;

import controle.LeilaoControl;
import controle.LoginControl;
import controle.MembroControl;
import modelo.Login;
import modelo.Membro;

public class Principal {
	

	public static void main(String[] args) {
		
		JChannel canal = null;
		try {
			canal = new JChannel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginControl loginCtrl = new LoginControl();
		LeilaoControl leilaoCtrl = new LeilaoControl();
		MembroControl membroCtrl = new MembroControl();
		
		Login login = loginCtrl.login();
		Membro membro = membroCtrl.novoMembro(login, canal.getAddress()); 
//		System.out.println("User: "+login.getUsuario());
//		System.out.println("Pass: "+login.getSenha());
		leilaoCtrl.menuPrincipal();
	}
	
}
