package controle;

import java.util.ArrayList;

import org.jgroups.Address;
import org.jgroups.Message;
import org.jgroups.blocks.MessageDispatcher;
import org.jgroups.blocks.RequestOptions;
import org.jgroups.blocks.ResponseMode;
import org.jgroups.blocks.atomic.Counter;
import org.jgroups.util.RspList;

import modelo.Membro;
import modelo.Sala;
import visao.LeilaoCli;
import visao.SalaCli;

public class LeilaoControl {

	private Membro membro;
	private Counter numSalas;
	private MessageDispatcher despachante;
	private Sala salaAtual;
	private ArrayList<Sala> minhasSalas = new ArrayList<>();
	
	LeilaoCli leilaoCli = new LeilaoCli();
	SalaCli salaCli = new SalaCli();
	SalaControl salaControl = new SalaControl();
	
	public LeilaoControl(MessageDispatcher despachante, Membro membro, Counter counter_numSala) {
		this.membro = membro;
		this.numSalas = counter_numSala;
		this.despachante = despachante;
	}
	
	public boolean menuPrincipal() {
		int op = 0;
		
		// Opcao valida
		boolean sair = false;
		while(!sair) {
			op = leilaoCli.menuPrincipal();

			switch(op) {
				// Sair da aplicação
				case 0: {
					if(leilaoCli.yesno("Deseja realmente sair? (y/n)"))
						// salvar estado atual do usuario aqui?!
						// Nao apenas aqui, mas periodicamente durante a aplicação
						sair = true;
					break;
				}
				// Cadastrar novo item / Criar nova sala
				case 1: {
					System.out.println("Criando nova sala...");
					Sala sala = salaControl.novaSala(this.numSalas.addAndGet(1), this.membro);
					minhasSalas.add(sala);
					System.out.println("Sala "+sala.getId()+" criada.");
					break;
				}
				// Entrar numa sala
				case 2: {
					// exibe as salas disponiveis
					salaControl.exibirSalas(this.minhasSalas);
					if (this.minhasSalas.isEmpty()) {
						System.out.println("Não existe salas cadastradas!");
						break;
					}
					// escolhe a sala e adiciona o membro nas 'minhasSalas'
					this.salaAtual = salaControl.entrarSala(this.membro, this.minhasSalas);
					if (this.salaAtual.equals(null))
						System.out.println("Sala não encontrada!");
					else
						salaControl.menuSala(this.membro, this.salaAtual);
					
					salaControl.atualizaSala(this.minhasSalas, this.salaAtual);
					break;
				}
				// Consultar itens leiloados
				case 3: {
					break;
				}
				// Logout
				case 4: {
					break;
				}
				default: {
					System.out.println("Digite um valor válido! (0-4)");
				}
			}
		}
		if (sair) return true;
		return false;
	}
	
	private void enviaSalasMulticast() throws Exception{

        //System.out.println("cheguei enviaMulticast");

        Address cluster = null; //endereço null significa TODOS os membros do cluster
        Message mensagem = new Message(cluster, null, this.minhasSalas);

        RequestOptions opcoes = new RequestOptions();
        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
        opcoes.setMode(ResponseMode.GET_ALL); // espera receber a resposta de TODOS membros (ALL, MAJORITY, FIRST, NONE)

        opcoes.setAnycasting(false);

        RspList<String> respList = despachante.castMessage(null, mensagem, opcoes); //MULTICAST
        //System.out.println("==> Respostas do cluster ao MULTICAST:\n" +respList+"\n");
    }
	
//	private void enviaMulticast() throws Exception{
//
//        //System.out.println("cheguei enviaMulticast");
//
//        Address cluster = null; //endereço null significa TODOS os membros do cluster
//        Message mensagem = new Message(cluster, null, nickname + " diz: " + conteudo);
//
//        RequestOptions opcoes = new RequestOptions();
//        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
//        opcoes.setMode(ResponseMode.GET_ALL); // espera receber a resposta de TODOS membros (ALL, MAJORITY, FIRST, NONE)
//
//        opcoes.setAnycasting(false);
//
//        RspList<String> respList = despachante.castMessage(null, mensagem, opcoes); //MULTICAST
//        //System.out.println("==> Respostas do cluster ao MULTICAST:\n" +respList+"\n");
//    }
//
//    private void enviaAnycast(Collection<Address> grupo, String conteudo) throws Exception{
//
//        //System.out.println("cheguei enviaAnycast");
//
//        Message mensagem=new Message(null,conteudo); //apesar do endereço ser null, se as opcoes contiverem anycasting==true enviará somente aos destinos listados
//
//        RequestOptions opcoes = new RequestOptions();
//        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
//        opcoes.setMode(ResponseMode.GET_MAJORITY); // espera receber a resposta da maioria do grupo (ALL, MAJORITY, FIRST, NONE)
//
//        opcoes.setAnycasting(true);
//
//        RspList<String> respList = despachante.castMessage(grupo, mensagem, opcoes); //ANYCAST
//        //System.out.println("==> Respostas do grupo ao ANYCAST:\n" +respList+"\n");
//
//    }
//
//    private void enviaUnicast(Address destino, String conteudo, String nick) throws Exception{
//
//        Address endereco = destino; //endereço null significa TODOS os membros do cluster
//
//        Message mensagem = new Message(endereco, null, "# " + nickname + " diz: " + conteudo);
//
//        RequestOptions opcoes = new RequestOptions();
//
//        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
//        opcoes.setMode(ResponseMode.GET_FIRST); // não espera receber a resposta do destino (ALL, MAJORITY, FIRST, NONE)
//
//        String resp = despachante.sendMessage(mensagem, opcoes); //UNICAST
//
//        //System.out.println(resp);
//        setHistorico(nick, mensagem.getObject().toString(), 1);
//        //System.out.println("==> Respostas do membro ao UNICAST:\n" +resp+"\n");
//    }
}
