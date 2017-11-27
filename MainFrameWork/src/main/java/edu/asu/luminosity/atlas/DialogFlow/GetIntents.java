package edu.asu.luminosity.atlas.DialogFlow;


import java.io.FileInputStream;
import java.util.Properties;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.asu.luminosity.atlas.ErrorHandling.Status;

public class GetIntents {




	String responseCall;

	public GetIntents() 
	{
		//Read Config File
		String url="";
		String Developer_Token="";
		try {
			Properties prop = new Properties();
			String propFileName = "./src/resources/config/intents.properties";
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
	}


	public JSONArray getJsonArray()
	{
		JSONParser jp = new JSONParser();
		JSONArray ja=new JSONArray();
		Object obj;
		try 
		{
			obj = jp.parse(responseCall);

			ja = (JSONArray)obj;	
			for(int i=0 ;i<ja.size();i++)
			{
				System.out.println(ja.get(i));
				break;
			}


		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ja;
	}

	public void showIntents()
	{
		JSONArray ja = getJsonArray();	
		JSONObject json;
		for(int i=0;i<ja.size();i++)
		{
			json= (JSONObject)ja.get(i);
			//System.out.println(json.get("name").toString());
		}

	}




	public static void main(String args[]) 
	{

		GetIntents gi = new GetIntents();
		gi.showIntents();

	}
}
