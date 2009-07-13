package Persistenz;
import java.awt.*;
import java.io.*;

import Logic.Logic;

/**
 * Klasse, die daf�r sorgt Probleme von der Festplatte zu lesen
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class ObjectDeserialization
{	
	/**
	 * Methode, die Dateien  bei angegebenem Dateipfad von der Festplatte liest
	 * @param name Dateipfad unter welcher die Datei geladen werden soll
	 */
	public Logic openLogic (String name)
	{
		//Attribut, welches zur Zwischenspeicherung der zu �ffnenden Problems dient
		Logic openLogic = null;
		try
		{
			//Streams, die das Object von der Festplatte lesen
			FileInputStream fs = new FileInputStream (name);
			ObjectInputStream is = new ObjectInputStream (fs);
			
			//Liest das Object von der Festplatte und wandelt es in ein Problem-Objekt um
			openLogic = (Logic) is.readObject();
			
			//Schlie�t die Streams
			is.close();
			
			//�berpr�fung, ob Problem geladen werden konnte
			if (openLogic == null)
			{
				//Es wird eine FileNotFoundException geworden, wenn die Datei nicht gefunden wurde
				throw new FileNotFoundException();
			}
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten k�nnen und R�ckgabe einer Fehlermeldung
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Datei konnte nicht gefunden werden.");
		}
		catch (ClassNotFoundException cnfe)
		{
			System.err.println("Klasse konnte nicht gefunden werden.");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.toString());
		}
		
		//Gibt das geladene Problem zur�ck
		return openLogic;
	}
	
	public Image openImage (String name)
	{
		//Attribut, welches zur Zwischenspeicherung der zu �ffnenden Problems dient
		Image openImage = null;
		try
		{
			//Streams, die das Object von der Festplatte lesen
			FileInputStream fs = new FileInputStream (name);
			ObjectInputStream is = new ObjectInputStream (fs);
			
			//Liest das Object von der Festplatte und wandelt es in ein Problem-Objekt um
			openImage = (Image) is.readObject();
			
			//Schlie�t die Streams
			is.close();
			
			//�berpr�fung, ob Problem geladen werden konnte
			if (openImage == null)
			{
				//Es wird eine FileNotFoundException geworden, wenn die Datei nicht gefunden wurde
				throw new FileNotFoundException();
			}
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten k�nnen und R�ckgabe einer Fehlermeldung
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Datei konnte nicht gefunden werden.");
		}
		catch (ClassNotFoundException cnfe)
		{
			System.err.println("Klasse konnte nicht gefunden werden.");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.toString());
		}
		
		//Gibt das geladene Problem zur�ck
		return openImage;
	}
}