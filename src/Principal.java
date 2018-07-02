import java.io.*;
import java.util.*;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.blocks.MessageDispatcher;
import org.jgroups.blocks.RequestHandler;
import org.jgroups.blocks.atomic.Counter;
import org.jgroups.blocks.atomic.CounterService;
import org.jgroups.util.*;

import controle.LeilaoControl;
import controle.LoginControl;
import controle.MembroControl;
import modelo.Login;
import modelo.Membro;

public class Principal extends ReceiverAdapter implements RequestHandler {

//  user[0] = Tipo de usuario (0 = normal, 1 = cordenador, 2 = adiministrador)
//  user[1] = Adress atual (ao logar enviar este endereco juntamente com seu login)
//	Vector user = new Vector();
	
	JChannel canal;
	MessageDispatcher despachante;
	CounterService counter_service;
	Counter counter_numSala;

    private Vector<Address> cluster;
    private HashMap<String,Address> membros = new HashMap<String,Address>();
    
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
	        
	        canal.setReceiver(this);
	        canal.connect("roro");
	        this.counter_numSala = counter_service.getOrCreateCounter("numSala", 0);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventLoop();
		canal.close();
			
	}
	
	public void eventLoop() {
		LoginControl loginCtrl = new LoginControl();
		Login login = new Login(); 
		login = loginCtrl.login();

		System.out.println("Login realizado com sucesso!");
		
		MembroControl membroCtrl = new MembroControl();
		System.out.println("Login: "+login.getUsuario()+"\nAdress: "+canal.getAddress().toString());
		Membro membro = membroCtrl.novoMembro(login.getUsuario(), canal.getAddress());
		
		LeilaoControl leilaoCtrl = new LeilaoControl(despachante, membro, counter_numSala);
		
		while(true) {
			if(leilaoCtrl.menuPrincipal()) {
				System.out.println("Saindo...");
				System.exit(0);
			}
		}
	}

	public void getEstadoLeilao() {
		
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
	
	public void viewAccepted(View new_view) { //exibe alterações na composição do grupo

        cluster = new Vector<Address>(canal.getView().getMembers());
        Address endereco;
        for (String nome : membros.keySet()) {
            endereco = membros.get(nome);
            if (!cluster.contains(endereco)){
                membros.remove(nome);
            }
        }

        //System.out.println("Membros Ativos: "+membros.keySet());

    }

    public void getState(OutputStream output) throws Exception{
        //Se tiver algum state transfer no XML,
        //Somente o coordenador (membro get(0)) irá invocar automagicamente este método

        ObjectOutputStream novoMembro = new ObjectOutputStream(output);
        novoMembro.writeObject(membros);
        //grupos
        //historicos

    }
    public void setState(InputStream input) throws Exception{
        //Se tiver algum state transfer no XML, somente um novo membro irá chamar

        ObjectInputStream coordenador = new ObjectInputStream(input);
        membros = (HashMap<String, Address>) coordenador.readObject();

        //grupos
        //historico
    }
	
}
