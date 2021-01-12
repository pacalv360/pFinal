package asr.proyectoFinal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class ReproduceAudio {
	/**
     * Abre un fichero de sonido wav y lo reproduce
     * @param args
     */
    public static void Reproduce() {
        
    	try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("hello_world.wav")));

            // Comienza la reproducción
            sonido.start();
            JOptionPane.showMessageDialog(null, "Click OK to stop music");            
            //while (sonido.isRunning())
            //   Thread.sleep(10000);
            
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    	
    	
    }
    
    
    public static void ReproduceArchivo(InputStream audio) {
        
    	try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(audio));

            // Comienza la reproducción
            sonido.start();
            //JOptionPane.showMessageDialog(null, "Click OK to stop music");            
            do {
            	//Thread.sleep(1000);
                sonido.drain();
            }while (sonido.isRunning());
            
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
  
    	
    }

}
