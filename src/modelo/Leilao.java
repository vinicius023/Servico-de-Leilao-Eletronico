//package modelo;
///**
// * Created by Vinicius, Rodrigo, Kimberly on 31/05/18.
// */
//
//import org.jgroups.*;
//import org.jgroups.util.*;
//
//import java.io.*;
//import java.util.*;
//import org.jgroups.blocks.*;
//
//
//public class Leilao extends ReceiverAdapter implements RequestHandler {
//
//    JChannel canal;
//    String nickname = "";
//    MessageDispatcher  despachante;
//    final String SAIR = "QUIT";
//    final String MENU = "MENU";
//    final String GRUPO = "GRUPO";
//
//    int op = -1;
//
//    private Vector<Address> cluster;
//
//    private HashMap<String,Address> membros = new HashMap<String,Address>();
//    private static ArrayList<Sala> salas = new ArrayList<>();
//    private static ArrayList<Membro> membros = new ArrayList<>();
//
//    public static ArrayList<Sala> getSalas() {
//    	return salas;
//    }
//    
//    public static ArrayList<Membro> getMembros() {
//		return membros;
//	}
//
//    public void exibiSalas() {
//        System.out.print("Salas: [");
//        for (Sala s : salas) {
//        	System.out.print(" "+s.getId());
//        }
//        System.out.println(" ]");
//    }
//    
//    public void exibiMembros() {
//        System.out.print("Membros: [");
//        for (Membro m : membros) {
//        	System.out.print(" "+m.getUsuario());
//        }
//        System.out.println(" ]");
//    }
//
//    public Integer menuTipoMensagem() {
//
//        int opcao = -1;
//
//        Scanner entradaMenu = new Scanner(System.in);
//        System.out.println("1 - Enviar MULTICAST\n" +
//                "2 - Enviar ANYCAST\n" +
//                "3 - Enviar UNICAST\n" +
//                "\n" +
//                "0 - Sair");
//
//        opcao = entradaMenu.nextInt();
//        return opcao;
//    }
//
//    private void eventLoop(){
//
//        Scanner teclado = new Scanner(System.in);
//        String linhaLidaDoUsuario = "";
//        boolean continua=true;
//
//        try{
//            enviaNickname();
//
//        }catch(Exception e) {
//            System.err.println( "ERRO: Nao consegue enviar nickName)" + e.toString() );
//        }
//
//        System.out.println("MENU - troca o tipo de envio de mensagens");
//        System.out.println("GRUPO - gerenciar salas");
//        System.out.println("QUIT - sair do chat");
//
//        while(continua){
//
//            System.out.print(">");
//            System.out.flush();
//
//            linhaLidaDoUsuario = teclado.nextLine();
//
//            try {
//
//                if (linhaLidaDoUsuario.startsWith(SAIR)) {
//                    canal.close();
//                    System.exit(0);
//                }else if (linhaLidaDoUsuario.startsWith(MENU)) {
//                    op = menuTipoMensagem();
//                }else if (linhaLidaDoUsuario.startsWith(GRUPO)) {
//                    op = 2;
//                }
//
//                switch (op) {
//                    case 1:
//
//                        System.out.print(">");
//                        System.out.flush();
//                        linhaLidaDoUsuario = teclado.nextLine();
//                        enviaMulticast(linhaLidaDoUsuario);
//                        break;
//
//                    case 2:
//
//                        menuSala();
//                        break;
//
//                    case 3:
//
//                        //Recebe endereço do usuario escolhido
//                       Address aux = membroChat();
//                       String nomeEscolhido = "";
//
//                       if (membros.containsValue(aux)){
//
//                           for (String nome: membros.keySet()) {
//                               if (membros.get(nome) == aux){
//                                   nomeEscolhido = nome;
//                               }
//                           }
//                       }
//
//                        do {
//
//                           System.out.print(">");
//                           System.out.flush();
//
//                           linhaLidaDoUsuario = teclado.nextLine();
//
//                           if (linhaLidaDoUsuario.contains("NOVO")) {
//
//                               aux = membroChat();
//                               if (membros.containsValue(aux)){
//                                   for (String nome: membros.keySet()) {
//                                       if (membros.get(nome) == aux){
//                                           nomeEscolhido = nome;
//                                       }
//                                   }
//                               }
//
//                           }else if (!linhaLidaDoUsuario.contains(MENU) && !linhaLidaDoUsuario.contains(GRUPO) && !linhaLidaDoUsuario.contains(SAIR)) {
//
//                               if (membros.containsKey(nomeEscolhido)){
//
//                                   enviaUnicast(aux, linhaLidaDoUsuario, nomeEscolhido);
//                               }else{
//                                   System.out.println("membro "+ nomeEscolhido +" offline");
//                               }
//
//                           }
//
//                        } while (!linhaLidaDoUsuario.contains(MENU) && !linhaLidaDoUsuario.contains(GRUPO) && !linhaLidaDoUsuario.contains(SAIR));
//                        System.out.println("MENU - troca o tipo de envio de mensagens");
//                        System.out.println("GRUPO - gerenciar salas");
//                        System.out.println("QUIT - sair do chat");
//                        break;
//                    default:
//                        enviaMulticast(linhaLidaDoUsuario);
//                        break;
//                }//switch
//
//            } catch (Exception e) {
//                System.err.println("ERRO: " + e.toString());
//            }
//            Util.sleep(100);
//        }//while
//    }
//
//    private Sala getSala(int id) {
//        //Sala saida = new Sala();
//
//        for (Sala s: this.salas) {
//            if (s.getId() == id) {
//                return s;
//            }
//        }
//
//        return null;
//    }
//
//    private void menuSala() throws Exception {
//
//        Scanner ler = new Scanner(System.in);
//        Scanner texto = new Scanner(System.in);
//
//        int escolha = -1;
//
//        String grupo;
//        String nome;
//        Address endereco;
//
//
//        while (escolha!=0) {
//
//
//            System.out.println( "\n1 - Criar um Sala\n" +
//                    "2 - Enviar Mensagem para um Sala\n" +
//                    "3 - Adicionar novo membro a um Sala\n" +
//                    "4 - Remover membro de um Sala\n" +
//                    "0 - Sair do gerenciador");
//
//            escolha = ler.nextInt();
//
//            switch (escolha) {
//                case 1 :
//                    System.out.println("Digite o nome do grupo: ");
//                    grupo = texto.nextLine();
//
//                    grupo += " "+membrosSala();
//
//                    String args[] = grupo.split(" ");
//
//		                Sala novoSala = new Sala();
//
//		                novoSala.setNome(args[0]);
//
//		                HashMap<String, Address> membrosSala = new HashMap<>();
//
//		                for (int i=1; i < args.length; i++){
//		                    for (String s : membros.keySet()) {
//		                        if (args[i].equals(s)) {
//		                            membrosSala.put(args[i], membros.get(s));		                            
//		                        }
//		                    }
//		                }
//
//		                novoSala.setMembros(membrosSala);		            
//
//                    enviaAnycast(novoSala.getEnderecos(), "grupo() "+grupo);
//
//                    break;
//                case 2 :
//                    exibiSalas();
//                    System.out.println("Digite o nome do grupo depois digite a mensagem");
//                    nome = texto.nextLine();
//                    //System.out.println(getSala(nome).getMembros().keySet());
//                    System.out.print(">");
//                    String msg = texto.nextLine();
//                    enviaAnycast(getSala(nome).getEnderecos(),"Sala "+nome+" | "+ nickname +" diz: "+msg);
//                    break;
//                case 3 :
//                    System.out.println("Digite o nome do grupo");
//                    grupo = texto.nextLine();
//                    System.out.println("Membros do chat: "+membros.keySet());
//                    System.out.println("Digite o nome do membro");
//                    nome = texto.nextLine();
//
//                   endereco = this.membros.get(nome);
//
//                   if (!getSala(grupo).getEnderecos().contains(endereco))
//                       getSala(grupo).getEnderecos().add(endereco);
//                   else System.out.println("Este membro já está no grupo!");
//                    break;
//                case 4 :
//                   System.out.println("Digite o nome do grupo");
//                   grupo = texto.nextLine();
//                   System.out.println("Membros do chat: "+membros.keySet());
//                   System.out.println("Digite o nome do membro");
//                   nome = texto.nextLine();
//
//                   endereco = this.membros.get(nome);
//
//                   if (getSala(grupo).getEnderecos().contains(endereco))
//                       getSala(grupo).getEnderecos().remove(endereco);
//                   else System.out.println("Este membro não pertence ao grupo!");
//                    break;
//                case 0 :
//                    break;
//                default:
//                    System.out.println("opcao invalida");
//                    break;
//            }
//        }
//    }    
//
//    // envia o proprio nick pra todos do cluster e passa a conhecer quem esta no cluster
//    private void enviaNickname() throws Exception{
//
//        Address cluster = null; //endereço null significa TODOS os membros do cluster
//        Message mensagem=new Message(cluster, null, "membro()" + " === " + nickname);
//
//        RequestOptions opcoes = new RequestOptions();
//        opcoes.setFlags(Message.DONT_BUNDLE); // envia imediatamente, não agrupa várias mensagens numa só
//        opcoes.setMode(ResponseMode.GET_ALL); // espera receber a resposta de TODOS membros (ALL, MAJORITY, FIRST, NONE)
//
//        opcoes.setAnycasting(false);
//
//        RspList<String> respostasVizinhos = despachante.castMessage(null, mensagem, opcoes); //MULTICAST
//
//        //conhece os vizinhos que estao no cluster
//        String nicknameVizinho;
//        for(Address vizinho : respostasVizinhos.keySet()){
//            nicknameVizinho = (respostasVizinhos.get(vizinho)).getValue();
//            if(!membros.containsKey(nicknameVizinho))
//               membros.put( nicknameVizinho , vizinho );
//        }
//
//        System.out.println("Membros Online:\n" +membros.keySet());
//    }
//
//    private void enviaMulticast(String conteudo) throws Exception{
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
//
//    public void receive(Message msg) { //exibe mensagens recebidas
//
//        System.out.println(msg.getSrc() + "receive< " + msg.getObject());
//        System.out.println(">");
//    }
//
//    private void setHistorico(String user, String mensagem, int tipoCast) throws IOException {
//
//        switch (tipoCast) {
//            case 1 :
//                //System.out.println("arquivo historico: "+user);
//                String nomeArq = "historicoUnicast_"+user+".txt";
//
//                //escreve no arquivo historicoUnicast
//                File arquivo = new File( nomeArq );
//
//                if(arquivo.exists()){
//
//                    FileWriter fw = new FileWriter( arquivo, true );
//                    BufferedWriter bw = new BufferedWriter( fw );
//
//                    bw.write(mensagem);
//
//                    //quebra de linha
//                    bw.newLine();
//                    //enquanto houver mais linhas
//                    bw.close();
//                    fw.close();
//
//                }else{
//
//                    arquivo.createNewFile();
//                    FileWriter fw = new FileWriter( arquivo, true );
//                    BufferedWriter bw = new BufferedWriter( fw );
//
//                    bw.write(mensagem);
//
//                    //quebra de linha
//                    bw.newLine();
//                    //enquanto houver mais linhas
//                    bw.close();
//                    fw.close();
//
//                }
//
//
//                break;
//        }
//
//    }
//
//    //Apenas comandos recebidos de outros
//    public Object handle(Message msg) throws Exception{ // responde requisições recebidas
//
//        String comando = (String) msg.getObject();
//
//        boolean temSala = false;
//
//        if (comando.startsWith("grupo()")) {
//
//        	//System.out.println("comando grupo()");
//            String args[] = comando.split(" ");
//
//            for (Sala g : salas) {
////                if (args[1].equals(g.getNome())){
////                    temSala = true;
////                }
//            }
//
//            if (!temSala) {
//
//                //Sala novoSala = new Sala();
//
////                novoSala.setNome(args[1]);
//
//                HashMap<String, Address> membrosSala = new HashMap<>();
//
//                for (int i=2; i < args.length; i++){
////                    for (String s : membros.keySet()) {
////                        if (args[i].equals(s)) {
////                            membrosSala.put(args[i], membros.get(s));                           
////                        }
////                    }
//                }
//
////                novoSala.setMembros(membrosSala);
////                System.out.println("voce foi add ao grupo: "+novoSala.getNome());
//                //this.salas.add(novoSala);
//            }
//
//            return "grupo add";
//
//        }else if (comando.contains("membro()")) {
//
//            System.out.println(comando+"\n"+">");
//
//            String args[] = comando.split(" === ");
//            // trata comandos do chat usar / ou # ou algo pra identificar comandos
//            if(args[0].equals("membro()") && msg.getSrc() != canal.getAddress() ){
//
////                membros.put(args[1],msg.getSrc());
//                return nickname; //resposta à requisição contida na mensagem
//
//            }
//
//            else
//                return " ? ";
//
//        }else {
//            String args[] = comando.split(" ");
//            System.out.println("<" + comando);
//
//            if(msg.getObject().toString().startsWith("#")){
//
//                try {
//                    setHistorico(args[1],comando.toString(),1);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return "";
//        }
//    }
//
//    public void viewAccepted(View new_view) { //exibe alterações na composição do grupo
//
//        cluster = new Vector<Address>(canal.getView().getMembers());
//        Address endereco;
////        for (String nome : membros.keySet()) {
////            endereco = membros.get(nome);
////            if (!cluster.contains(endereco)){
////                membros.remove(nome);
////            }
////        }
//
//        //System.out.println("Membros Ativos: "+membros.keySet());
//
//    }
//
//    public void getState(OutputStream output) throws Exception{
//        //Se tiver algum state transfer no XML,
//        //Somente o coordenador (membro get(0)) irá invocar automagicamente este método
//
//        ObjectOutputStream novoMembro = new ObjectOutputStream(output);
//        novoMembro.writeObject(membros);
//        //salas
//        //historicos
//
//    }
//    public void setState(InputStream input) throws Exception{
//        //Se tiver algum state transfer no XML, somente um novo membro irá chamar
//
//        ObjectInputStream coordenador = new ObjectInputStream(input);
////        membros = (HashMap<String, Address>) coordenador.readObject();
//
//        //salas
//        //historico
//    }
//
////    public static void main(String[] args) throws Exception{
////
////        ValChat chat = new ValChat();
////        chat.Start();
////    }
//
//}
