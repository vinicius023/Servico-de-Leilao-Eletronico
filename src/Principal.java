import javax.xml.stream.events.StartDocument;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.MembershipListener;
import org.jgroups.Message;
import org.jgroups.MessageListener;
import org.jgroups.Receiver;
import org.jgroups.ReceiverAdapter;
import org.jgroups.blocks.MessageDispatcher;
import org.jgroups.blocks.RequestHandler;

import controle.LeilaoControl;
import controle.LoginControl;
import controle.MembroControl;
import modelo.Leilao;
import modelo.Login;
import modelo.Membro;

public class Principal extends ReceiverAdapter implements RequestHandler {

//  user[0] = Tipo de usuario (0 = normal, 1 = cordenador, 2 = adiministrador)
//  user[1] = Adress atual (ao logar enviar este endereco juntamente com seu login)
//	Vector user = new Vector();
	
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
		
		eventLoop();
		canal.close();
			
	}
	
	public void eventLoop() {
		LoginControl loginCtrl = new LoginControl();
		Login login = loginCtrl.login();
		
		MembroControl membroCtrl = new MembroControl();
		Membro membro = membroCtrl.novoMembro(login.getUsuario(), canal.getClusterName(), canal.getAddress());
		
		LeilaoControl leilaoCtrl = new LeilaoControl(membro);
		
		while(true) {
			leilaoCtrl.menuPrincipal();
		}
	}

	public static void main(String[] args) {
		
		Principal p = new Principal();
		p.start();
		
	}

	@Override
	public Object handle(Message arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
