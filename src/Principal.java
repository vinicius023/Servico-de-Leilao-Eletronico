import javax.xml.stream.events.StartDocument;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.MembershipListener;
import org.jgroups.MessageListener;
import org.jgroups.Receiver;
import org.jgroups.blocks.MessageDispatcher;

import controle.LeilaoControl;
import controle.LoginControl;
import controle.MembroControl;
import modelo.Leilao;
import modelo.Login;
import modelo.Membro;

public class Principal {
	
	JChannel canal;
	MessageDispatcher despachante;
	
	public void start() {
		
		try {
			canal = new JChannel("cast.xml");
			canal.connect("roro");
			
//			despachante = new MessageDispatcher(canal, null, null, despachante);
//
//	        despachante.setRequestHandler(despachante);
//	        despachante.setMessageListener((MessageListener) this);
//	        despachante.setMembershipListener((MembershipListener) this);
//
//	        canal.setReceiver((Receiver) this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginControl loginCtrl = new LoginControl();
		LeilaoControl leilaoCtrl = new LeilaoControl();
		MembroControl membroCtrl = new MembroControl();
		
		Login login = loginCtrl.login();
//		System.out.println("User: "+login.getUsuario());
//		System.out.println("Pass: "+login.getSenha());
		

		System.out.println("Address: "+canal.getAddressAsString());
		System.out.println("Cluster: "+canal.getClusterName());
		Membro membro = membroCtrl.novoMembro(login.getUsuario(), canal.getClusterName(), canal.getAddress());
		System.out.println("Membro\nUser: "+membro.getUsuario());
		System.out.println("Address: "+membro.getEndereco());
		
		leilaoCtrl.menuPrincipal();
		canal.close();
	}

	public static void main(String[] args) {
		
		Principal p = new Principal();
		p.start();
		
	}
	
}
