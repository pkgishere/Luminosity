package edu.asu.luminosity.knowledge;

import com.google.gson.Gson;

import ai.api.GsonFactory;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class ApiAiMainModel {
	
	private AIResponse aiResponse;
	
	public ApiAiMainModel(MainApiAi maa) {
		aiResponse= GsonFactory.getDefaultFactory().getGson().fromJson(maa.getJson(),AIResponse.class);
		// TODO Auto-generated constructor stub
	}
	
	

	public Result getResult ()
	{	
		return this.aiResponse.getResult();
	}
	
	public AIResponse getAIResponse()
	{
		return this.aiResponse;
	}




	public static void main(String args[])
	{
		final Gson GSON = GsonFactory.getDefaultFactory().getGson();
		final AIResponse airepsonse= GSON.fromJson(new MainApiAi("move forward").getJson(),AIResponse.class);
		Result r=airepsonse.getResult();
		System.out.println(r.getParameters().get("Direction"));
	}



}
