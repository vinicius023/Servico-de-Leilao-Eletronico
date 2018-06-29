import java.util.ArrayList;

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
import org.jgroups.blocks.atomic.Counter;
import org.jgroups.blocks.atomic.CounterService;

import controle.LeilaoControl;
import controle.LoginControl;
import controle.MembroControl;
import modelo.Leilao;
import modelo.Login;
import modelo.Membro;
import modelo.Sala;

public class Principal extends ReceiverAdapter implements RequestHandler {

//  user[0] = Tipo de usuario (0 = normal, 1 = cordenador, 2 = adiministrador)
//  user[1] = Adress atual (ao logar enviar este endereco juntamente com seu login)
//	Vector user = new Vector();
	
	JChannel canal;
	MessageDispatcher despachante;
	CounterService counter_service;
	Counter counter_numSala;
    
	public void start() throws Exception{
	
		try {
			// cria canal
			canal = new JChannel("cast.xml");
			
			// cria contador global
			this.counter_service = new CounterService(canal);
			
			despachante = new MessageDispatcher(canal, null, null, this);

			despachante.setRequestHandler(this);
	        despachante.setMessageListener(this);
	        despachante.setMembershipListener(this);
	        
	        canal.setReceiver((Receiver) this);
	        canal.connect("RooD");
	        this.counter_numSala = counter_service.getOrCreateCounter("numSala", 1);
	        
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
		
		LeilaoControl leilaoCtrl = new LeilaoControl(membro, counter_numSala);
		
		while(true) {
			leilaoCtrl.menuPrincipal();
		}
	}

	public static void main(String[] args) {
		
		Principal p = new Principal();
		try {
			p.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Object handle(Message arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
