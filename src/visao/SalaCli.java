package visao;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Item;
import modelo.Membro;
import modelo.Sala;

public class SalaCli {
	
	Scanner scan = new Scanner(System.in);

	public int menu() {
		System.out.println("\n-----_ SALA _-----");
		System.out.println("1. Dar lance");
		System.out.println("2. Ver lances");
		System.out.println("0. Sair da sala");

		return scan.nextInt();
	}
	
	public Item cadastrarItem(Membro membro) {
		System.out.println("\\n-----_ Cadastrar Item _-----");
		System.out.println("Nome do Item: ");
		String nomeItem = scan.nextLine();
		scan = new Scanner(System.in);
		Item item = new Item(nomeItem, membro);
		return item;
	}

	public void printSalas(ArrayList<Sala> minhasSalas) {
		System.out.println("\\n-----_ Salas disponiveis _-----");
		for (Sala s : minhasSalas) {
			System.out.println("Sala "+s.getId());
		}
		
	}
}
