package visao;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Item;
import modelo.Lance;
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
		scan = new Scanner(System.in);
		String nomeItem = scan.nextLine();
		scan = new Scanner(System.in);
		Item item = new Item(nomeItem, membro);
		System.out.println("Item criado!");
		return item;
	}
	
	public Integer escolherSala(ArrayList<Sala> minhasSalas) {
		System.out.println("\\n-----_ Escolher Sala _-----");
		System.out.println("ID da sala: ");
		Integer numItem = scan.nextInt();
		scan = new Scanner(System.in);
		return numItem;
	}

	public void printSalas(ArrayList<Sala> minhasSalas) {
		System.out.println("\\n-----_ Salas disponiveis _-----");
		for (Sala s : minhasSalas) {
			System.out.println("Sala "+s.getId());
		}
		
	}

	public Double lance() {
		System.out.println("Valor do lance: ");
		Double lance = scan.nextDouble();
		scan = new Scanner(System.in);
		return lance;
	}
	
	public void printLances(ArrayList<Lance> lances) {
		System.out.println("\\n-----_ Lances _-----");
		if (lances.isEmpty())
			System.out.println("Lista de lances vazia!");
		for (Lance l : lances) {
			System.out.println("Membro: "+l.getMembro().getUsuario()+"\tLance: "+l.getLance());
		}
	}
}
