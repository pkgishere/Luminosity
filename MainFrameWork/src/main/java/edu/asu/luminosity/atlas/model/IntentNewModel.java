package edu.asu.luminosity.atlas.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IntentNewModel {
	
	String intentName="";
	ArrayList<String> list=new ArrayList<>();
	ArrayList<String> list2=new ArrayList<>();
	String outputone="";
	String Inputone="";
	String a;

	private void setResult()
	{
		this.a="{\n" + 
				" \"events\": [],\n" + 
				" \"fallbackIntent\": false,\n" + 
				" \"name\": \"" + intentName + "\",\n" + 
				" \"priority\": 500000,\n" + 
				" \"responses\": [\n" + 
				"   {\n" + 
				"     \"messages\":[ "+ this.outputone +"]\n" + 
				"   }\n" + 
				" ],\n" + 
				" \"userSays\": [ "+Inputone+"\n" + 
				" ],\n" + 
				" \"webhookForSlotFilling\": false,\n" + 
				" \"webhookUsed\": false\n" + 
				"}";
	
	}

	JSONParser jp = new JSONParser();
	JSONObject ja=new JSONObject();
	Object obj;
	

	public IntentNewModel() 
	{
		try 
		{
			  	setResult();
				obj = jp.parse(this.a);

				ja = (JSONObject)obj;
				
				System.out.println(ja.toString());

				
				JSONArray ja2= (JSONArray)ja.get("responses");
				//JSONObject ja3= JSONObject (ja2.get(0));
				
				//System.out.println(ja2.toString());

			

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void setIntentName(String intent)
	{
		this.intentName=intent;
		setResult();
	}
	
	public void addOutput(String output)
	{
		String[] outputArray={output};
		addOutput(outputArray);
	}
	
	public void addOutput(String[] outputArray)
	{
		String output="[\""+ String.join("\",\"", outputArray)+ "\"]";
		String temp=
				"       {\n" + 
				"         \"platform\": \"google\",\n" + 
				"         \"textToSpeech\": \"" + outputArray[0]+"\",\n" + 
				"         \"type\": \"simple_response\"\n" + 
				"       },\n" + 
				"       {\n" + 
				"         \"speech\": " + output+",\n" + 
				"         \"type\": 0\n" + 
				"       }";
		
		list.add(temp);
		outputone =String.join("," , list);
		 setResult();
	}
	
	
	
	public void addInput(String input)
	{
		
		String temp="   {\n" + 
				"     \"count\": 1,\n" + 
				"     \n" + 
				"     \"data\": [\n" + 
				"       {\n" + 
				"         \"text\": \""+input+"\"\n" + 
				"       }\n" + 
				"     ]\n" + 
				"   }\n"; 
		
		list2.add(temp);
		Inputone =String.join("," , list2);
		setResult();
	}
	
	
	
	
	
	public static void main(String args[])
	{
		IntentNewModel inm = new IntentNewModel();
		inm.setIntentName("Pappu");
		inm.addInput("Are you in the lab");
		inm.addInput("where are you madam");
		String[] abc = {"I am in lab", "I am innnnn"};
		inm.addOutput(abc);
		inm.addOutput("i alab");
		System.out.println(inm.a.toString());
	}
}
