package controle;

import java.util.ArrayList;

import modelo.Item;
import modelo.Membro;
import modelo.Sala;
import visao.SalaCli;

public class SalaControl {

	private SalaCli salaCli;

	public Sala novaSala(int numSala, Membro membro, Item item) {
		// Cadastra novo item
		Item novoItem = salaCli.cadastrarItem(membro);
		// Cria nova sala
		Sala sala = new Sala(numSala, membro, novoItem);
		return sala;
	}
	
	public void entrarSala(Membro membro, int numSala, ArrayList<Sala> salas) {
		
	}

	public void exibirSalas(ArrayList<Sala> minhasSalas) {
		salaCli.printSalas(minhasSalas);
		
	}
}
