package Storage;
import java.io.*;

/**
 * Klasse, die daf�r sorgt Probleme auf der Festplatte zu speichern
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class ObjectSerialization
{	
	/**
	 * Methode, die �bergebene Probleme unter dem angegebenen Namen speichert
	 * @param name Name, unter welcher das Objekt gespeichert werden soll
	 * @param obj  Objekt, dass auf der Festplatte gespeichert werden soll
	 */
	public void save (String name, Object obj)
	{
		try
		{
			//Streams, die f�r das Schreiben der Datei ben�tigt werden
			FileOutputStream fs = new FileOutputStream (name + ".tsp");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			
			//Datei wird auf die Festplatte geschrieben
			os.writeObject(obj);
			
			//Streams werden geschlossen
			os.close();
		}
		//Abfangen aller Exception, die bei dem Lade-Vorgang auftreten k�nnen und R�ckgabe einer Fehlermeldung
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Datei konnte nicht gefunden werden.");
		}
		catch (IOException ioe)
		{
			System.err.println(ioe.toString());
		}
	}
}