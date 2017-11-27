package edu.asu.luminosity.knowledge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




public class MainApiAi extends HttpServlet
{
	
	private String s = "python ";
	private Process p;
	private BufferedReader stdInput;
	JsonObject name;
	String jsontext="";
	public MainApiAi(String text) 
	{
		Subroutine(text);
	}
	
	private void Subroutine(String text)
	{
		try 
		{
			this.s+=text;
			//System.out.println("\n\nTemp2:" + s);
			
			this.p = Runtime.getRuntime().exec(s);
			System.out.println("Temp:" + p.toString());
			this.stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String data;
			while ((data = stdInput.readLine()) != null)
			{
				//System.out.println("I am here " + data);
				jsontext +=data;
			}
			//System.out.println("TEMP: " + jsontext);
			this.name = new JsonParser().parse(jsontext).getAsJsonObject();
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public JsonObject getJson()
	{
		return this.name;
	}
	
	public String getText()
	{
		return this.jsontext;
	}
	
	
	public static void main(String sas[])
	{	
		
		String text="define smart";
		MainApiAi maa = new MainApiAi(text);
		System.out.println(maa.getJson());
	}
}
