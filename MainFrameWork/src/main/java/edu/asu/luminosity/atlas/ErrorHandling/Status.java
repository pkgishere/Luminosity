package edu.asu.luminosity.atlas.ErrorHandling;

public class Status {



	public Status(int status_code) {
		// TODO Auto-generated constructor stub

		if(status_code==200){

			System.out.println("Request was successful.");
		}
		else if (status_code==400){

			System.out.println("Some required parameter is missing or has the wrong value.Details will be in the errorDetails field.");

		}else if(status_code==401){

			System.out.println("Internal authorization failed. It might mean missing or wrong credentials.");

		}else if(status_code==404){

			System.out.println("URI is not valid or the resource ID does not correspond to an existing resource.");

		}else if(status_code==405){

			System.out.println("HTTP method not allowed, such as attempting to use a POST request with an endpoint that only accepts GET requests, or vice versa.");

		}else if(status_code==406){

			System.out.println("Can be returned if uploaded files have some errors.");
		}else if(status_code==409){

			System.out.println("The request could not be completed due to a conflict with the current state of the resource. This code is only returned in situations where it is expected that the user might be able to resolve the conflict and resubmit the request. For example, deleting an entity that is used in an intent will return this error.");

		}else{
			System.out.println("Too many requests were sent in the short amount of time.");
		}


	}
}
