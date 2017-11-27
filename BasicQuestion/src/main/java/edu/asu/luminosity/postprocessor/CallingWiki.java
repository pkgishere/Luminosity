package edu.asu.luminosity.postprocessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CallingWiki {

	String url;
	String s = "perl temp.pl -t ";
	private Process p;
	BufferedReader stdInput;
	String answer="";

	public CallingWiki(String text, String path) 
	{
		text="\""+text+"\"";
		this.s="perl \""+path+"\""+" -t ";
		Subroutine(text);
	}

	public CallingWiki(String text) 
	{
		text="\""+text+"\"";
		this.s="perl /home/nlp/eclipse-workspace/BasicQuestion/src/main/java/edu/asu/luminosity/postprocessor/temp.pl -t ";
		Subroutine(text);
	}

	private void Subroutine(String text)
	{
		try 
		{
			this.s+=text;
			System.out.println("this--------------> "+s);
			this.p = Runtime.getRuntime().exec(s);
			this.stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((s = stdInput.readLine()) != null)
			{
				answer +=s;
			}

		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getAnswer()
	{
		return this.answer;
	}
	public static void main(String sas[])
	{	

				String text="india";
				CallingWiki cw = new CallingWiki(text); 
				//System.out.println(cw.getAnswer().replaceAll("[^\\x00-\\x7F]", ""));
				System.out.println(cw.getAnswer());
				System.out.println("Exit");
				//String b=sc.getRealPath("temp.pl");

//		String s;
//		String answer="";
//		Process p;
//		try {
//			p = Runtime.getRuntime().exec("perl /home/nlp/eclipse-workspace/BasicQuestion/src/main/java/edu/asu/luminosity/postprocessor/temp.pl -t india");
//			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
//			while ( (s = stdInput.readLine()) != null)
//			{
//				answer +=s;
//			}
//			System.out.println(answer);
//		}
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	}
}

