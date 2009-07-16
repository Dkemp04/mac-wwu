package Storage;
import java.awt.Image;
import java.io.*;

import GUI.Main.MainFrame;
import Logic.Logic;

/**
 * Klasse, die dafür sorgt Probleme auf der Festplatte zu speichern
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class ObjectSerialization
{	
	/**
	 * Methode, die übergebene Probleme unter dem angegebenen Namen speichert
	 * @param name Name, unter welcher das Objekt gespeichert werden soll
	 * @param obj  Objekt, dass auf der Festplatte gespeichert werden soll
	 */
	public void save (String name, String ending, Object obj)
	{
		try
		{
			//Streams, die für das Schreiben der Datei benötigt werden
			FileOutputStream fs = new FileOutputStream (MainFrame.WORKSPACE+ name + "." + ending);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			
			//Datei wird auf die Festplatte geschrieben
			os.writeObject(obj);
			
			//Streams werden geschlossen
			os.close();
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten können und Rückgabe einer Fehlermeldung
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Datei konnte nicht gefunden werden.");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.toString());
		}
	}
	
	public Logic openLogic (String name, String ending)
	{
		//Attribut, welches zur Zwischenspeicherung der zu öffnenden Problems dient
		Logic openLogic = null;
		try
		{
			//Streams, die das Object von der Festplatte lesen
			FileInputStream fs = new FileInputStream (MainFrame.WORKSPACE + name + "." + ending);
			ObjectInputStream is = new ObjectInputStream (fs);
			
			//Liest das Object von der Festplatte und wandelt es in ein Problem-Objekt um
			openLogic = (Logic) is.readObject();
			
			//Schließt die Streams
			is.close();
			
			//Überprüfung, ob Problem geladen werden konnte
			if (openLogic == null)
			{
				//Es wird eine FileNotFoundException geworden, wenn die Datei nicht gefunden wurde
				throw new FileNotFoundException();
			}
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten können und Rückgabe einer Fehlermeldung
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
		
		//Gibt das geladene Problem zurück
		return openLogic;
	}
	
	public Image openImage (String name)
	{
		//Attribut, welches zur Zwischenspeicherung der zu öffnenden Problems dient
		Image openImage = null;
		try
		{
			//Streams, die das Object von der Festplatte lesen
			FileInputStream fs = new FileInputStream (name);
			ObjectInputStream is = new ObjectInputStream (fs);
			
			//Liest das Object von der Festplatte und wandelt es in ein Problem-Objekt um
			openImage = (Image) is.readObject();
			
			//Schließt die Streams
			is.close();
			
			//Überprüfung, ob Problem geladen werden konnte
			if (openImage == null)
			{
				//Es wird eine FileNotFoundException geworden, wenn die Datei nicht gefunden wurde
				throw new FileNotFoundException();
			}
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten können und Rückgabe einer Fehlermeldung
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
		
		//Gibt das geladene Problem zurück
		return openImage;
	}
}