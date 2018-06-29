package controle;

import java.util.ArrayList;

import org.jgroups.blocks.atomic.Counter;

import modelo.Membro;
import modelo.Sala;
import visao.LeilaoCli;
import visao.SalaCli;

public class LeilaoControl {

	private Membro membro;
	private Counter numSalas;
	private ArrayList<Sala> minhasSalas = new ArrayList<>();
	
	LeilaoCli leilaoCli = new LeilaoCli();
	SalaCli salaCli = new SalaCli();
	SalaControl salaControl = new SalaControl();
	
	public LeilaoControl(Membro membro, Counter counter_numSala) {
		this.membro = membro;
		this.numSalas = counter_numSala;
	}
	
	public boolean menuPrincipal() {
		int op = 0;
		
		//opcao valida
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
					// escolhe a sala e adiciona o membro nas 'minhasSalas'
					salaControl.entrarSala(this.membro, this.minhasSalas);
				}
				// Consultar itens leiloados
				case 3: {

				}
				// Logout
				case 4: {

				}
				default: {
					System.out.println("Digite um valor válido! (0-4)");
				}
			}
		}
		if (sair) return true;
		return false;
	}
}
