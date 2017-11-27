package edu.asu.luminosity.atlas.DialogFlow;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.asu.luminosity.atlas.ErrorHandling.Status;
import edu.asu.luminosity.atlas.model.EntityListModel;

public class GetEntities {
	
	



	String responseCall;
	private HashMap<String, EntityListModel> hm;
	JSONArray ja;
	public GetEntities() 
	{
		//Read Config File
		String url="";
		String Developer_Token="";
		hm = new HashMap<>();
		try {
			Properties prop = new Properties();
			String propFileName = "./src/resources/config/entities.properties";
			FileInputStream fr = new FileInputStream(propFileName);
			prop.load(fr);
			fr.close();
			url = prop.getProperty("URL");
			Developer_Token= prop.getProperty("developerToken");		
		} 
		catch (Exception e) 
		{
			System.out.println("Exception: " + e);
		} 

		Client client = Client.create();
		WebResource webResource =   client.resource(url);
		ClientResponse response = webResource    
				.header("Authorization", "Bearer "+Developer_Token)
				.type("application/json")
				.accept("application/json")
				.get(ClientResponse.class);

		int statusCode = response.getStatus();
		if(statusCode!=200)
		{
			new Status(statusCode);
			return;
		}

		this.responseCall = response.getEntity(String.class);
		JSONParser jp = new JSONParser();
		JSONArray ja=new JSONArray(); 
		Object obj;
		JSONObject jb;
		try 
		{
			obj = jp.parse(responseCall);

			ja = (JSONArray)obj;
			
			for(int i=0 ;i<ja.size();i++)
			{
				JSONObject js =(JSONObject)ja.get(i);
				EntityListModel elm = new EntityListModel();
				elm.setId(js.get("id").toString());
				elm.setCount(Integer.parseInt(js.get("count").toString()));
				elm.setName(js.get("name").toString());
				elm.setPreview(js.get("preview").toString());
				//System.out.println(elm.getName());
				hm.put(elm.getName(),elm);
			}


		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public JSONArray getJsonArray()
	{
		
		return ja;
	}
	
	
	public EntityListModel getEntity(String entity_name)
	{
		return hm.get(entity_name);
	}
	
	
	

	public void showIntents()
	{
		JSONArray ja = getJsonArray();	
		JSONObject json;
		for(int i=0;i<ja.size();i++)
		{
			json= (JSONObject)ja.get(i);
			System.out.println(json.get("name").toString());
		}

	}




	public static void main(String args[]) 
	{

		GetEntities gi = new GetEntities();
		EntityListModel elm =gi.getEntity("Diet");
	}
}