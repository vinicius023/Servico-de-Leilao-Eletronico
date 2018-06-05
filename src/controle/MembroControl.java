package controle;

import java.util.ArrayList;

import org.jgroups.Address;

import modelo.Login;
import modelo.Membro;

public class MembroControl {

	MembroFile json = new MembroFile();
	
	public MembroControl() {
		
	}
	
	public Membro novoMembro(Login login, Address endereco) {
		Membro membro = new Membro(login, endereco);
		
		if(!json.existeMembro(membro)) {
			json.writeMembro(membro);
			return membro;
		}
		return null;
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
