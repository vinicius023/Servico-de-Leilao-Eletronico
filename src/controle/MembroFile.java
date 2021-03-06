package controle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Membro;

public class MembroFile {

	public static final String PATH = System.getProperty("user.dir")+"/files/Membros.json";

	public ArrayList<Membro> readMembros() {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		// Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		ArrayList<Membro> membros = new ArrayList<Membro>();

		try {
			// Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));

			// Coleta array com todos os Membros
			obJson = (JSONArray) jsonObject.get("membros");

			// Percorre todos os Membros
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
				// cria um novo objeto 'Membro' com o dado extraido
				// adiciona o Membro ao array
				membros.add(new Membro(aux.get("usuario").toString()));
			}
			return membros;
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo 'Membros.json'! ");
			e.printStackTrace();
		}
		return null;
	}

	public void writeMembros(ArrayList<Membro> membros) {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;

		for (Membro l : membros) {
			jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getUsuario());
//			jsonObject.put("senha", l.getLogin().getSenha());
//			jsonObject.put("endereco", l.getEndereco().toString());
			// Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		// Armazena Array JSON em um Objeto JSON
		aux.put("membros", obJson);

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

	public void writeMembro(Membro membro) {
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;

		// Adiciona o novo Membro no array
		ArrayList<Membro> membros = readMembros();
		membros.add(membro);

		for (Membro l : membros) {
			jsonObject = new JSONObject();
			// Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getUsuario());
//			jsonObject.put("senha", l.getLogin().getSenha());
//			jsonObject.put("endereco", l.getEndereco().toString());
			// Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		// Armazena Array JSON em um Objeto JSON
		aux.put("membros", obJson);
		System.out.println("Vai escrever");
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

	public boolean existeMembro(Membro membro) {
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
			obJson = (JSONArray) jsonObject.get("membros");

			// Percorre todos os Membros
			if (obJson.size() == 0 ) {
//				System.out.println("Nenhum membro cadastrado");
				return false;
			}
			else {
				System.out.println("Entro = TAM "+obJson.size());
				for (int i = 0; i < obJson.size(); i++) {
					aux = (JSONObject) obJson.get(i);
					 //System.out.println("Usuario: "+aux.get("usuario")+"- Membro");
					// "+Membro.getUsuario()+"\nSenha: "+aux.get("senha"));
					// garante aquele Membro daquele canal
					if ((aux.get("usuario").equals(membro.getUsuario()))) {
						// System.out.println("Usuario: "+aux.get("usuario")+" == "+Membro.getUsuario());
						//System.out.println("ACHOU");
						return true;
					}
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
