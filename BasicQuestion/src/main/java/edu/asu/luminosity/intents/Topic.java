package edu.asu.luminosity.intents;

import ai.api.model.Result;
import edu.asu.luminosity.postprocessor.CallingWiki;

public class Topic {

	private String speech;

	public String getSpeech() {
		return this.speech;
	}

	public Topic(Result r, String path) {
		
		String text=r.getStringParameter("any");
		CallingWiki cw = new CallingWiki(text,path); 
		this.speech=cw.getAnswer().replaceAll("[^\\x00-\\x7F]", "");
	}
}
