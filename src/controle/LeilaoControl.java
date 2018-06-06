package controle;

import visao.LeilaoCli;

public class LeilaoControl {

	LeilaoCli cli = new LeilaoCli();
	
	public void menuPrincipal() {
		int op = 0;
		
		//opcao valida
		while(true) {
			op = cli.menuPrincipal();

			switch(op) {
				// Sair da aplicação
				case 0: {
					if(cli.yesno("Deseja realmente sair? (y/n)"))
						// salvar estado atual do usuario aqui?!
						// Nao apenas aqui, mas periodicamente durante a aplicação
						System.exit(0);
				}
				// Cadastrar novo item / Criar nova sala
				case 1: {

				}
				// Entrar numa sala
				case 2: {

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
