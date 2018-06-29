package controle;

import java.util.ArrayList;

import modelo.Item;
import modelo.Membro;
import modelo.Sala;
import visao.SalaCli;

public class SalaControl {

	private SalaCli salaCli;

	public Sala novaSala(long numSala, Membro membro) {
		// Cadastra novo item
		Item novoItem = salaCli.cadastrarItem(membro);
		// Cria nova sala
		return new Sala(numSala, membro, novoItem);
	}
	
	public Sala novaSala(long numSala, Membro membro, Item item) {
		// Cria nova sala		 
		return new Sala(numSala, membro, item);
	}
	
	public void entrarSala(Membro membro, ArrayList<Sala> salas) {
		Integer numEscolhido = salaCli.escolherSala(salas);
		for (Sala s: salas) {
			if(s.getId() == numEscolhido) {
				//colocar membro na salas
				s.AddMembro(membro);
			}
		}
	}

	public void exibirSalas(ArrayList<Sala> minhasSalas) {
		salaCli.printSalas(minhasSalas);
		
	}
}
