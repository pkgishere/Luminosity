package edu.asu.luminosity.postprocessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Crawling_Ask {
	String Answer;
	public Crawling_Ask(String Regex) throws Exception
	{   
		this.Answer="";
		Regex=String.join("+", Regex.split("\\s"));
		String link= "https://www.ask.com/web?q=" + Regex;
		URL url = new URL(link);
		URLConnection yc = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
		String inputLine;
		String A;
		int flag=0;
		while ((inputLine = in.readLine()) != null)
		{


			if(inputLine.contains("<div class=\"sa_dictionary_definition_entry\">"))
			{
				flag=1;
				this.Answer+="\n";
				continue;
			}
			if(flag > 0)
			{
				A=inputLine;
				A=A.replaceAll("<.*?>", "");
				A=A.replaceAll("\\s+", " ");
				A= A.replaceAll("[\r\n]+", "\n");
				//A=A.replaceAll("\n+", "\n");
				//System.out.println(A);
				this.Answer+=A;
			}
			if(inputLine.contains("div class=\"sa_dictionary_related_question\""))
			{
				break;
			}
			

		}
		if(Answer.startsWith("\n"))
		{
			Answer=Answer.substring(1);
		}
	}

	public String getAnswer()
	{
		return this.Answer;
	}


	public static void main(String args[])
	{
		try {
			Crawling_Ask cw = new Crawling_Ask("define reservation");
			System.out.println(cw.getAnswer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
