package asr.proyectoFinal.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import asr.proyectoFinal.services.ReproduceAudio;



public class TextSpeech {

	public static void TexttoVoice( String palabra) {
		
		IamAuthenticator authenticator = new IamAuthenticator("uKTs9PnzUpy9zmxGtdRTkaz_w00R4UGXYzBro21UNyTQ");
		TextToSpeech textToSpeech = new TextToSpeech(authenticator);
		textToSpeech.setServiceUrl("https://api.us-south.text-to-speech.watson.cloud.ibm.com/instances/2c367ed7-1088-4670-bf74-ba5d960b2976");

		try {
		  SynthesizeOptions synthesizeOptions =
		    new SynthesizeOptions.Builder()
		      .text(palabra)
		      .accept("audio/wav")
		      .voice("en-US_AllisonV3Voice")
		      .build();

		  InputStream inputStream =
		    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
		  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

		  ReproduceAudio.ReproduceArchivo(in);
		  
		  OutputStream out = new FileOutputStream("audio.wav");
		  byte[] buffer = new byte[1024];
		  int length;
		  while ((length = in.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		  }
		    
		  out.close();
		  in.close();
		  inputStream.close();
		} catch (IOException e) {
		  e.printStackTrace();
		}
				
	}
}
