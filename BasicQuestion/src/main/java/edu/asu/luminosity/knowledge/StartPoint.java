package edu.asu.luminosity.knowledge;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.asu.luminosity.postprocessor.MainPostProcessor;

/**
 * Servlet implementation class StartPoint
 */
@WebServlet("/StartPoint")
public class StartPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public StartPoint() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Getting Text from the Client
		String text=request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		System.out.println("INPUT:"+ text);
		ServletContext sc = getServletContext();
		String a=sc.getRealPath("knowledge.py");
		String b=sc.getRealPath("temp.pl");
		System.out.print(a);
		System.out.println("HERE"+b);
		
       // MainPrePrcossor mp = new MainPreprocessor();
       // text=mp.filterText(text);

		//Calling Probabilistic Model
		MainApiAi maa = new MainApiAi("\""+a+"\""+" "+text);
		System.out.println(maa.getText());
		ApiAiMainModel aamm = new ApiAiMainModel(maa);

		System.out.println("AI RESPONSE"+ aamm.getAIResponse().toString());
		MainPostProcessor name = new MainPostProcessor(aamm.getAIResponse(),b);
		System.out.println("Speech"+ aamm.getAIResponse().toString());
		response.getWriter().append(name.getSpeech());
		System.out.println("Calling Probabilistic Model TEXT:\n "+ name.getSpeech());



		//Calling Postprocessing Model
		//				MainPostProcessor mpp = new MainPostProcessor(gv);
		//				response.getWriter().append(gv.getAIResponse().getResult().getFulfillment().getSpeech());
		//				System.out.println("Calling Probabilistic Model TEXT : "+ gv.getAIResponse().getResult().getFulfillment().getSpeech());
		//				

	}

}
