package controle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jgroups.Address;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Login;
import modelo.Membro;

public class MembroFile {

	public static final String PATH = "/home/rood/Git/Servico-de-Leilao-Eletronico/src/files/Membros.json";

	public ArrayList<Membro> readMembros() {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		// Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		ArrayList<Membro> Membros = new ArrayList<Membro>();

		try {
			// Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));

			// Coleta array com todos os Membros
			obJson = (JSONArray) jsonObject.get("Membros");

			// Percorre todos os Membros
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
				// cria um novo objeto 'Membro' com o dado extraido
				// adiciona o Membro ao array
//				String to Address, como proceder?
				//Membros.add(new Membro(new Login(aux.get("usuario").toString(), aux.get("senha").toString()), aux.get("endereco").toString()));
			}
			return Membros;

		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo 'Membros.json'! ");
			e.printStackTrace();
		}
		return null;
	}

	public void writeMembros(ArrayList<Membro> Membros) {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;

		for (Membro l : Membros) {
			jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getLogin().getUsuario());
//			jsonObject.put("senha", l.getLogin().getSenha());
			jsonObject.put("endereco", l.getEnderecoAtual().toString());
			// Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		// Armazena Array JSON em um Objeto JSON
		aux.put("Membros", obJson);

		try {
			writeFile = new FileWriter(PATH);
			// Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever arquivo 'Membros.json'! ");
			e.printStackTrace();
		}
	}

	public void writeMembro(Membro Membro) {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;

		// Adiciona o novo Membro no array
		ArrayList<Membro> Membros = readMembros();
		Membros.add(Membro);

		for (Membro l : Membros) {
			jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getLogin().getUsuario());
//			jsonObject.put("senha", l.getLogin().getSenha());
			jsonObject.put("endereco", l.getEnderecoAtual());
			// Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		// Armazena Array JSON em um Objeto JSON
		aux.put("Membros", obJson);

		try {
			writeFile = new FileWriter(PATH);
			// Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		} catch (IOException e) {
			System.out.println("Erro ao escrever arquivo 'Membros.json'! ");
			e.printStackTrace();
		}
	}

	public boolean existeMembro(Membro Membro) {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		// Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		try {
			// Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));

			// Coleta array com todos os Membros
			obJson = (JSONArray) jsonObject.get("Membros");

			// Percorre todos os Membros
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
				// System.out.println("Usuario: "+aux.get("usuario")+"- Membro:
				// "+Membro.getUsuario()+"\nSenha: "+aux.get("senha"));
				// garante Membro unico
				if (aux.get("usuario").equals(Membro.getLogin().getUsuario())) {
					// System.out.println("Usuario: "+aux.get("usuario")+" == "+Membro.getUsuario());
					return true;
				}

			}
			return false;
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo 'Membros.json'! ");
			e.printStackTrace();
		}
		return false;
	}
}
