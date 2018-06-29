package controle;

import java.util.ArrayList;

import org.jgroups.blocks.atomic.Counter;

import modelo.Item;
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
	
	public void menuPrincipal() {
		int op = 0;
		
		//opcao valida
		while(true) {
			op = leilaoCli.menuPrincipal();

			switch(op) {
				// Sair da aplicação
				case 0: {
					if(leilaoCli.yesno("Deseja realmente sair? (y/n)"))
						// salvar estado atual do usuario aqui?!
						// Nao apenas aqui, mas periodicamente durante a aplicação
						System.exit(0);
					break;
				}
				// Cadastrar novo item / Criar nova sala
				case 1: {
					Sala sala = salaControl.novaSala(this.numSalas.incrementAndGet(), this.membro);
					minhasSalas.add(sala);
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
	}
}
