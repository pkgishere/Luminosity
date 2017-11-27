package edu.asu.luminosity.postprocessor;


import ai.api.model.AIResponse;
import ai.api.model.Result;
import edu.asu.luminosity.postprocessor.LamdaFunction;

public class MainPostProcessor {

	private String speechReply;

	public MainPostProcessor(AIResponse aiResponse, String a) {
		// TODO Auto-generated constructor stub
		Result result =aiResponse.getResult();
		LamdaFunction lf = new LamdaFunction();
		lf.setPath(a);
		lf.onIntent(result);
		this.speechReply=lf.getSpeech();
	}
	
	public String getSpeech()
	{
		return this.speechReply;
	}

}
