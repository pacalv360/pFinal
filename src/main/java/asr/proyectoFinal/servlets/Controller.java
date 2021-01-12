package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.cloud.sdk.core.service.exception.RequestTooLargeException;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.ToneAnalyzer;
import asr.proyectoFinal.services.TextSpeech;
import asr.proyectoFinal.services.ReproduceAudio;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar", "/tono", "/hablar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
					
				break;
				
			case "/tono":
				
				try {

				Authenticator authenticator = new IamAuthenticator("NOvhNn4v5sTeUCPlp78HUjFSXgVvm2Rtw_eDjksdlQVm");
				ToneAnalyzer service = new ToneAnalyzer("2017-09-21", authenticator);
				
				String text = request.getParameter("palabra");
				
				// Call the service and get the tone
				ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(text)
				  .build();

				ToneAnalysis tone = service.tone(toneOptions).execute().getResult();	
				out.println("The tone analysis of the text is :" + tone);
				
					  // Invoke a method
					} catch (NotFoundException e) {
					  // Handle Not Found (404) exception
					} catch (RequestTooLargeException e) {
					  // Handle Request Too Large (413) exception
					} catch (ServiceResponseException e) {
					  // Base class for all exceptions caused by error responses from the service
					  System.out.println("Service returned status code "
					    + e.getStatusCode() + ": " + e.getMessage());
					}
				break;
		
				
			case "/hablar":
				String parametro1 = request.getParameter("palabra");
				String traduccion=Traductor.translate(parametro1, "es", "en", false);
				//String checkbox = request.getParameter("pr");
				if(request.getParameter("pr")!=null) {
					TextSpeech.TexttoVoice(traduccion);
				}
				out.println("Traduccion :" + traduccion  );
				break;
				
				
			case "/insertar":
				Palabra palabra = new Palabra();
				String parametro = request.getParameter("palabra");

				if(parametro==null)
				{
					out.println("usage: /insertar?palabra=palabra_a_traducir");
				}
				else
				{
					if(store.getDB() == null) 
					{
						out.println(String.format("Palabra: %s", palabra));
					}
					else
					{
						parametro = Traductor.translate(parametro, "es", "en", false);
						palabra.setName(parametro);
						store.persist(palabra);
					    out.println(String.format("Almacenada la palabra: %s", palabra.getName()));			    	  
					}
				}
				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
