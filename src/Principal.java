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

//  user[0] = Tipo de usuario (0 = normal, 1 = cordenador, 2 = adiministrador)
//  user[1] = Adress atual (ao logar enviar este endereco juntamente com seu login)
//	Vector user = new Vector();

	private void enviaMulticast(String conteudo) throws Exception{

        //System.out.println("cheguei enviaMulticast");

        Address cluster = null; //endereço null significa TODOS os membros do cluster
        Message mensagem = new Message(cluster, null, nickname + " diz: " + conteudo);

        RequestOptions opcoes = new RequestOptions();
        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
        opcoes.setMode(ResponseMode.GET_ALL); // espera receber a resposta de TODOS membros (ALL, MAJORITY, FIRST, NONE)

        opcoes.setAnycasting(false);

        RspList<String> respList = despachante.castMessage(null, mensagem, opcoes); //MULTICAST
        //System.out.println("==> Respostas do cluster ao MULTICAST:\n" +respList+"\n");
    }

    private void enviaAnycast(Collection<Address> grupo, String conteudo) throws Exception{

        //System.out.println("cheguei enviaAnycast");

        Message mensagem=new Message(null,conteudo); //apesar do endereço ser null, se as opcoes contiverem anycasting==true enviará somente aos destinos listados

        RequestOptions opcoes = new RequestOptions();
        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
        opcoes.setMode(ResponseMode.GET_MAJORITY); // espera receber a resposta da maioria do grupo (ALL, MAJORITY, FIRST, NONE)

        opcoes.setAnycasting(true);

        RspList<String> respList = despachante.castMessage(grupo, mensagem, opcoes); //ANYCAST
        //System.out.println("==> Respostas do grupo ao ANYCAST:\n" +respList+"\n");

    }

    private void enviaUnicast(Address destino, String conteudo, String nick) throws Exception{

        Address endereco = destino; //endereço null significa TODOS os membros do cluster

        Message mensagem = new Message(endereco, null, "# " + nickname + " diz: " + conteudo);

        RequestOptions opcoes = new RequestOptions();

        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
        opcoes.setMode(ResponseMode.GET_FIRST); // não espera receber a resposta do destino (ALL, MAJORITY, FIRST, NONE)

        String resp = despachante.sendMessage(mensagem, opcoes); //UNICAST

        //System.out.println(resp);
        setHistorico(nick, mensagem.getObject().toString(), 1);
        //System.out.println("==> Respostas do membro ao UNICAST:\n" +resp+"\n");
    }

    public void receive(Message msg) { //exibe mensagens recebidas

        System.out.println(msg.getSrc() + "receive< " + msg.getObject());
        System.out.println(">");
    }

    //Apenas comandos recebidos de outros
    public Object handle(Message msg) throws Exception{ // responde requisições recebidas

        String comando = (String) msg.getObject();

        boolean temGrupo = false;

        if (comando.startsWith("grupo()")) {

        	//System.out.println("comando grupo()");
            String args[] = comando.split(" ");

            for (Grupo g : grupos) {
                if (args[1].equals(g.getNome())){
                    temGrupo = true;
                }
            }

            if (!temGrupo) {

                Grupo novoGrupo = new Grupo();

                novoGrupo.setNome(args[1]);

                HashMap<String, Address> membrosGrupo = new HashMap<>();

                for (int i=2; i < args.length; i++){
                    for (String s : membros.keySet()) {
                        if (args[i].equals(s)) {
                            membrosGrupo.put(args[i], membros.get(s));
                        }
                    }
                }

                novoGrupo.setMembros(membrosGrupo);
                System.out.println("voce foi add ao grupo: "+novoGrupo.getNome());
                this.grupos.add(novoGrupo);
            }

            return "grupo add";

        }else if (comando.contains("membro()")) {

            System.out.println(comando+"\n"+">");

            String args[] = comando.split(" === ");
            // trata comandos do chat usar / ou # ou algo pra identificar comandos
            if(args[0].equals("membro()") && msg.getSrc() != canal.getAddress() ){

                membros.put(args[1],msg.getSrc());
                return nickname; //resposta à requisição contida na mensagem

            }

            else
                return " ? ";

        }else {
            String args[] = comando.split(" ");
            System.out.println("<" + comando);

            if(msg.getObject().toString().startsWith("#")){

                try {
                    setHistorico(args[1],comando.toString(),1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }
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

    public void setState(InputStream input) throws Exception{
        //Se tiver algum state transfer no XML, somente um novo membro irá chamar

        ObjectInputStream coordenador = new ObjectInputStream(input);
        membros = (HashMap<String, Address>) coordenador.readObject();

        //grupos
        //historico
    }
	
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
