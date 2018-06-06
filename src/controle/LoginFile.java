package controle;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelo.Login;

public class LoginFile {

	public static final String PATH = "/home/vinicius/Documents/Servico-de-Leilao-Eletronico/src/files/logins.json";
	
	public ArrayList<Login> readLogins() {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();
		
		ArrayList<Login> logins = new ArrayList<Login>();
		
		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));
			
			// Coleta array com todos os logins
			obJson = (JSONArray) jsonObject.get("logins");
			
			// Percorre todos os logins
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
				// cria um novo objeto 'Login' com o dado extraido
				// adiciona o login ao array
				logins.add(new Login(
						aux.get("usuario").toString(), 
						aux.get("senha").toString()));
			}
			return logins;
			
		} catch(Exception e) {
			System.out.println("Erro ao ler arquivo 'logins.json'! ");
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeLogins(ArrayList<Login> logins) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;
		
		for (Login l : logins) {
			jsonObject = new JSONObject();
			//Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getUsuario());
			jsonObject.put("senha", l.getSenha());
			//Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		//Armazena Array JSON em um Objeto JSON
		aux.put("logins", obJson);
		
		try{
			writeFile = new FileWriter(PATH);
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		}
		catch(IOException e){
			System.out.println("Erro ao escrever arquivo 'logins.json'! ");
			e.printStackTrace();
		}
	}
	
	public void writeLogin(Login login) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject;
		JSONObject aux = new JSONObject();

		FileWriter writeFile = null;
		
		// Adiciona o novo login no array
		ArrayList<Login> logins = readLogins();
		logins.add(login);
		
		for (Login l : logins) {
			jsonObject = new JSONObject();
			//Armazena dados em um Objeto JSON
			jsonObject.put("usuario", l.getUsuario());
			jsonObject.put("senha", l.getSenha());
			//Armazena Objeto JSON em um Array JSON
			obJson.add(jsonObject);
		}
		//Armazena Array JSON em um Objeto JSON
		aux.put("logins", obJson);
		
		try{
			writeFile = new FileWriter(PATH);
			//Escreve no arquivo conteudo do Objeto JSON
			writeFile.write(aux.toJSONString());
			writeFile.close();
		}
		catch(IOException e){
			System.out.println("Erro ao escrever arquivo 'logins.json'! ");
			e.printStackTrace();
		}
	}
	
	public boolean existeLogin(Login login) {		
		// instancia um novo JSONArray
		JSONArray obJson = new JSONArray();
		// instancia um novo JSONObject
		JSONObject jsonObject, aux;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(PATH));
			
			// Coleta array com todos os logins
			obJson = (JSONArray) jsonObject.get("logins");
			
			// Percorre todos os logins
			for (int i = 0; i < obJson.size(); i++) {
				aux = (JSONObject) obJson.get(i);
//				System.out.println("Usuario: "+aux.get("usuario")+"- Login: "+login.getUsuario()+"\nSenha: "+aux.get("senha"));
				// garante login unico
				if(aux.get("usuario").equals(login.getUsuario())) {
//					System.out.println("Usuario: "+aux.get("usuario")+" == "+login.getUsuario());
					return true;
				}
					
			}
			return false;
		} catch(Exception e) {
			System.out.println("Erro ao ler arquivo 'logins.json'! ");
			e.printStackTrace();
		}
		return false;
	}
	
}
