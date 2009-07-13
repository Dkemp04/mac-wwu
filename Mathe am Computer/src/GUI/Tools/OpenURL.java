package GUI.Tools;
import java.io.IOException;

/**
 * Klasse, die dafür sorgt, dass eine übergebene URL im Browser geöffnet wird
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class OpenURL
{
	public final static int LOCAL_FILE = 0;
	public final static int WEB_FILE = 1;
	public final static int SAVE_WEB_FILE = 2;
	
	/**
	 * Konstruktor, der die übergebene URL modifiziert an interne Klasse weiterreicht
	 * @param url URL, die im Browser geöffnet werden soll
	 */
	public OpenURL(String url, int type)
	{
		if (type == LOCAL_FILE)
			launch("file:///" + url);
		else if (type == WEB_FILE)
			launch("http://" + url);
		else if (type == SAVE_WEB_FILE)
			launch("https://" + url);
	}
	
	/**
	 * Methode, die die übergebene URL im Browser öffnet. Dabei wird auf ein Windows-Betriebssystem geprüft und der passende Browser gewählt.
	 * @param url URL, die im Browser geöffnet werden soll
	 */
	private void launch(String url)
	{
		try
		{
			//Überprüfe, ob es sich um ein Windows-Betriebssystem handelt.
			if (isWindows())
			{
				//Startet mit dem übergebenen Windows-Befehl den Browser mit der übergebenen URL
				Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler "+ url );
			}
			else
			{
				//Ansonsten soll die URL im Mozilla Firefox-Browser geöffnet werden
				Runtime.getRuntime().exec("firefox " + url);
			}
		}
		//Abfangen von möglichen Exception, die bei der Verarbeitung auftreten können
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Methode, die überprüft, ob es sich um ein Windows-Betriebssystem handelt
	 * @return Gibt zurück, ob es sich um ein Windows-Betriebssytem handelt
	 */
	private boolean isWindows()
	{
		//Speicherung der Betriebssystemnames
		String os = System.getProperty("os.name");
		
		//Überprüfung, ob Windows-Betriebssytem
		return os != null && os.startsWith("Windows");
	}
}