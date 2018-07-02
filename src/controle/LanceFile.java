package controle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Lance;
import modelo.Membro;


public class LanceFile {

public static final String PATH = System.getProperty("user.dir")+"/files/Lances.json";
	
	public ArrayList<Lance> readLances() {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();
		
		ArrayList<Lance> Lances = new ArrayList<Lance>();
		
		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));
			
			// Coleta array com todos os Lances
			obJson = (JSONArray) jsonObject.get("Lances");
			
			// Percorre todos os Lances
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
				// adiciona o Lance ao array
				Lances.add(new Lance(new Membro(aux.get("membro").toString()),
						Double.parseDouble(aux.get("lance").toString())));
			}
			return Lances;
			
		} catch(Exception e) {
			System.out.println("Erro ao ler arquivo 'Lances.json'! ");
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeLances(ArrayList<Lance> Lances) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;
		
		for (Lance l : Lances) {
			jsonObject = new JSONObject();
			//Armazena dados em um Objeto JSON
			jsonObject.put("membro", l.getMembro().getUsuario());
			jsonObject.put("lance", l.getLance());
			//Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		//Armazena Array JSON em um Objeto JSON
		aux.put("Lances", obJson);
		
		try{
			writeFile = new FileWriter(PATH);
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		}
		catch(IOException e){
			System.out.println("Erro ao escrever arquivo 'Lances.json'! ");
			e.printStackTrace();
		}
	}
	
	public void writeLance(Lance Lance) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;
		
		// Adiciona o novo Lance no array
		ArrayList<Lance> Lances = readLances();
		Lances.add(Lance);
		
		for (Lance l : Lances) {
			jsonObject = new JSONObject();
			//Armazena dados em um Objeto JSON
			jsonObject.put("lance", l.getMembro().getUsuario());
			jsonObject.put("lance", l.getLance());
			//Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		//Armazena Array JSON em um Objeto JSON
		aux.put("Lances", obJson);
		
		try{
			writeFile = new FileWriter(PATH);
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		}
		catch(IOException e){
			System.out.println("Erro ao escrever arquivo 'Lances.json'! ");
			e.printStackTrace();
		}
	}
	
	public boolean existeLance(Lance Lance) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));
			
			// Coleta array com todos os Lances
			obJson = (JSONArray) jsonObject.get("Lances");
			
			// Percorre todos os Lances
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
//				System.out.println("Usuario: "+aux.get("usuario")+"- Lance: "+Lance.getUsuario()+"\nSenha: "+aux.get("senha"));
				// garante Lance unico
				if(aux.get("lance").equals(Lance.getLance()) && 
						aux.get("membro").equals(Lance.getMembro().getUsuario())) {
					return true;
				}
			}
			return false;
		} catch(Exception e) {
			System.out.println("Erro ao ler arquivo 'Lances.json'! ");
			e.printStackTrace();
		}
		return false;
	}
}
