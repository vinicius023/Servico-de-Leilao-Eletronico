package controle;

import java.util.ArrayList;

import modelo.Item;
import modelo.Membro;
import modelo.Sala;
import visao.LeilaoCli;
import visao.SalaCli;

public class LeilaoControl {

	private Membro membro;
	private int numSalas = 0;
	private ArrayList<Sala> minhasSalas = new ArrayList<>();
	
	LeilaoCli leilaoCli = new LeilaoCli();
	SalaCli salaCli = new SalaCli();
	SalaControl salaControl = new SalaControl();
	
	public LeilaoControl(Membro membro) {
		this.membro = membro;
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
					Sala sala = salaControl.novaSala(this.numSalas+1, this.membro);
					minhasSalas.add(sala);
					break;
				}
				// Entrar numa sala
				case 2: {
					salaControl.exibirSalas(this.minhasSalas);
					salaControl.entrarSala(this.membro, this.numSalas, this.minhasSalas);
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
