package edu.asu.luminosity.atlas.DialogFlow;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import edu.asu.luminosity.atlas.ErrorHandling.Status;
import edu.asu.luminosity.atlas.model.EntityListModel;

public class GetEntity {

	String responseCall;

	public GetEntity(String entity_name) 
	{

		EntityListModel elm = new GetEntities().getEntity(entity_name);
		String id = elm.getId();
		//Generate Request

		//Read Config File
		String url="";
		String Developer_Token="";
		try {
			Properties prop = new Properties();
			String propFileName = "./src/resources/config/entities.properties";
			FileInputStream fr = new FileInputStream(propFileName);
			prop.load(fr);
			fr.close();
			url = "https://api.dialogflow.com/v1/entities/"+id+"?v=20150910";
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
		
		System.out.println(responseCall);

	}
	
	public static void main(String args[])
	{
		GetEntity getEntity= new GetEntity("Feedback");
	}

}
