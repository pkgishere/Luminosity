package edu.asu.luminosity.postprocessor;

import ai.api.model.Result;
import edu.asu.luminosity.intents.*;

public class LamdaFunction {

	String intentName;
	String speech;
	private String path;

	public void onIntent(Result result) {
		intentName = result.getMetadata().getIntentName();
		// TODO Auto-generated method stub

		if (intentName.equals("") || intentName.isEmpty()) {
			new Default_Fallback_Intent();
			return;
		}
		System.out.println(intentName);
		// float a=result.getScore();

		switch (intentName) {

		case "Topic":
			Topic tp = new Topic(result, this.path);
			this.speech = tp.getSpeech();
			break;
		case "Dictionary_Antonyms":
			this.speech = new DictionaryApi().getAntonyms(result.getStringParameter("any"));
			break;
		case "Dictionary_definitions":
			this.speech = new DictionaryApi().getDefinitions(result.getStringParameter("any"));
			break;
		case "Dictionary_examples":

			this.speech = new DictionaryApi().getExamples(result.getStringParameter("any"));
			break;
		case "Dictionary_synonyms":
			this.speech = new DictionaryApi().getSynonyms(result.getStringParameter("any"));
			break;
		case "Default Fallback Intent":
			new Default_Fallback_Intent();
			this.speech = "not yet implemented";
			break;
		default:
			new Default_Fallback_Intent();
			this.speech = "not yet implemented";
			break;

		}

	}

	public String getSpeech() {
		// TODO Auto-generated method stub
		return this.speech;
	}

	public void setPath(String a) {
		// TODO Auto-generated method stub
		this.path = a;

	}
}
