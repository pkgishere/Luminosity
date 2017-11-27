package edu.asu.luminosity.postprocessor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DictionaryApi {
	// http://wordnetweb.princeton.edu/perl/webwn?c=0&sub=Change&o2=&o0=&o8=1&o1=&o7=&o5=&o9=&o6=&o3=&o4=&i=-1&h=000000&s=fish

	final String Api_key = "e6daJpqBJpmshFzrlUdVwhvi8B9Tp1ijfm1jsni46zzwg0MEnL";
	String word = "";
	String intent = "";
	String url = "https://wordsapiv1.p.mashape.com/words/" + word + intent;
	String response;
	public DictionaryApi() {

	}

	private void setUrl() {
		this.url = "https://wordsapiv1.p.mashape.com/words/" + this.word + "/" + this.intent;
	}

	public String getDefinitions(String word) {
		this.intent = "definitions";
		this.word = word;
		setUrl();
		String response = hitUrl();
		JSONParser jp = new JSONParser();
		JSONArray ja;
		Object obj;
		JSONObject jb = new JSONObject();
		String Answer = "";

		try {
			obj = jp.parse(response);

			jb = (JSONObject) obj;
			ja = (JSONArray) jb.get("definitions");
			for (int i = 0; i < ja.size(); i++) {

				JSONObject data = (JSONObject) ja.get(i);
				Answer = Answer + " " + (i + 1) + " " + data.get("definition").toString() + "\n";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Answer;
	}

	public String getAntonyms(String word) 
	{
		this.intent = "antonyms";
		this.word = word;
		setUrl();
		this.response = hitUrl();
		return subroutine("antonyms");
		
	}
	public String getExamples(String word) 
	{
		this.intent = "examples";
		this.word = word;
		setUrl();
		this.response = hitUrl();
		return subroutine("examples");
		
	}
	public String getSynonyms(String word) 
	{
		this.intent = "synonyms";
		this.word = word;
		setUrl();
		this.response = hitUrl();
		return subroutine("synonyms");
		
	}
	
	private String subroutine(String key) {
		JSONParser jp = new JSONParser();
		JSONArray ja;
		Object obj;
		JSONObject jb = new JSONObject();
		String Answer = "";

		try {
			obj = jp.parse(this.response);
			jb = (JSONObject) obj;
			ja = (JSONArray) jb.get(key);
			for (int i = 0; i < ja.size(); i++) {

				Answer = Answer + (i + 1) + " " + ja.get(i).toString() + "\n";
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Answer;

	}

	private String hitUrl() {
		HttpResponse<JsonNode> response = null;
		try {
			response = Unirest.get(url).header("X-Mashape-Key", this.Api_key).header("Accept", "application/json")
					.asJson();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.getBody().toString();
	}

	public static void main(String[] args) throws Exception {
		DictionaryApi da = new DictionaryApi();
		System.out.println(da.getAntonyms("fear"));

	}
}
