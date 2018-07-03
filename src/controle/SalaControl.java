package controle;

import java.util.ArrayList;

import modelo.Item;
import modelo.Lance;
import modelo.Membro;
import modelo.Sala;
import visao.SalaCli;

public class SalaControl {

	private SalaCli salaCli = new SalaCli();

	public Sala novaSala(ArrayList<Sala> salas, long numSala, Membro membro) {
		// Cadastra novo item
		Item novoItem;
		boolean sair = false;
		while(!sair) {
			novoItem = salaCli.cadastrarItem(membro);
			
			for (Sala s : salas) {
				// garante item unico
				if(s.getItem().getNome().equals(novoItem.getNome())) {
					sair = true;
					break;
				}
			}
			if (sair) {
				System.out.println("Item já cadastrado!\n");
				sair = false;
			}
			else
				return new Sala(numSala, membro, novoItem);
		}
		// Cria nova sala
		return null;
	}

	public Sala entrarSala(Membro membro, ArrayList<Sala> salas) {
		Integer numEscolhido = salaCli.escolherSala(salas);
		for (Sala s: salas) {
			if(s.getId() == numEscolhido) {
				//colocar membro na salas
				System.out.println("Colocando membro "+membro.getUsuario()+" na sala "+s.getId());
				s.AddMembro(membro);
				System.out.println("Membro adicionado.");
				return s;
			}
		}
		return null;
	}
	
	public void menuSala(Membro membro, Sala sala) {
		boolean sair = false;
		while (!sair) {
		Integer op = salaCli.menu();
			switch (op) {
				case 0: {
					sair = true;
					break;
				}
				// dar lance
				case 1: {
					Double lance = salaCli.lance();
					System.out.println("Saiu aqui..");
					if (lance > sala.getItem().getMaiorValor()) {
						System.out.println("Saiu aqui..ENtrou");
						sala.getItem().setMaiorValor(lance);
						sala.setVencedor(membro);
					}
					System.out.println("Saiu aqui..Fora");
					sala.getItem().getLances().add(new Lance(membro, lance));
					System.out.println("Saiu aqui..Deu ruim");
					break;
				}
				// visualizar lances
				case 2: {
					salaCli.printLances(sala.getItem().getLances());
					break;
				}
				
			}
		}
	}

	public void exibirSalas(ArrayList<Sala> minhasSalas) {
		salaCli.printSalas(minhasSalas);
		
	}

	public void atualizaSala(ArrayList<Sala> salas, Sala sala) {
		for (Sala s: salas) {
			if(s.getId() == sala.getId()) {
				s = sala;
			}
		}
	}
}
