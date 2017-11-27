package edu.asu.luminosity.intents;

import ai.api.model.Result;
import edu.asu.luminosity.postprocessor.Crawling_Ask;

public class Dictionary {

	private String speech;

	public String getSpeech() {
		return this.speech;
	}


	public Dictionary(Result result) 
	{
		// TODO Auto-generated constructor stub
		
		
			Crawling_Ask cw;
			String text="define ";
			try {
				text+=result.getStringParameter("any");
				cw = new Crawling_Ask(text);
				this.speech=cw.getAnswer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}

	
}
