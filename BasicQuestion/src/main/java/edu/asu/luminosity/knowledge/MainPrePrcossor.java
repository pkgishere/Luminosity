package edu.asu.luminosity.knowledge;


import java.util.regex.Pattern;


public class MainPrePrcossor 
{
	String inputtext;
	String outputtext;
	
	public MainPrePrcossor(String text) 
	{
		this.inputtext=text;
		this.outputtext=this.inputtext;
		char c =inputtext.charAt(0);
		if(c == '=')
		{
			this.outputtext=Filtertext(inputtext);
		}
		if(inputtext.contains("\""))
		{
			this.outputtext=outputtext.replaceAll("\"", "");
		}

	}
	
	

	private String Filtertext(String text) {
		text=text.substring(1,text.length());
		text=String.join(" ", text.split(Pattern.quote("+")));
		text=text.replaceAll("%2B", "+");
		text=text.replaceAll("%2F", " divided by ");
		return text;
	}










	public String filterText(String text) {
		// TODO Auto-generated method stub
		return this.outputtext;
	}

}
