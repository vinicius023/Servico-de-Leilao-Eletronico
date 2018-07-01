package controle;

import java.util.ArrayList;

import org.jgroups.Address;

import modelo.Membro;

public class MembroControl {

	MembroFile json = new MembroFile();
	
	public MembroControl() {
		
	}
	
	public Membro novoMembro(String usuario, Address endereco) {
		Membro membro = new Membro(usuario, endereco);
		
		// garante membro unico
		if(!json.existeMembro(membro)) {
			json.writeMembro(membro);
		}
		return membro;
	}
	
	public void removeMembro(Membro membro) {
		
		ArrayList<Membro> membros = new ArrayList<Membro>();
		
		if(json.existeMembro(membro)) {
			membros = json.readMembros();
//			Isso funciona? Ou terei que retornar o indice no existeMembro, para remover por indice
			membros.remove(membro);
			json.writeMembros(membros);
		}
	}
}
