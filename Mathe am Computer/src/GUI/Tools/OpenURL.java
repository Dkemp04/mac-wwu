package GUI.Tools;
import java.io.IOException;

/**
 * Klasse, die daf�r sorgt, dass eine �bergebene URL im Browser ge�ffnet wird
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class OpenURL
{
	public final static int LOCAL_FILE = 0;
	public final static int WEB_FILE = 1;
	public final static int SAVE_WEB_FILE = 2;
	
	/**
	 * Konstruktor, der die �bergebene URL modifiziert an interne Klasse weiterreicht
	 * @param url URL, die im Browser ge�ffnet werden soll
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
	 * Methode, die die �bergebene URL im Browser �ffnet. Dabei wird auf ein Windows-Betriebssystem gepr�ft und der passende Browser gew�hlt.
	 * @param url URL, die im Browser ge�ffnet werden soll
	 */
	private void launch(String url)
	{
		try
		{
			//�berpr�fe, ob es sich um ein Windows-Betriebssystem handelt.
			if (isWindows())
			{
				//Startet mit dem �bergebenen Windows-Befehl den Browser mit der �bergebenen URL
				Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler "+ url );
			}
			else
			{
				//Ansonsten soll die URL im Mozilla Firefox-Browser ge�ffnet werden
				Runtime.getRuntime().exec("firefox " + url);
			}
		}
		//Abfangen von m�glichen Exception, die bei der Verarbeitung auftreten k�nnen
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Methode, die �berpr�ft, ob es sich um ein Windows-Betriebssystem handelt
	 * @return Gibt zur�ck, ob es sich um ein Windows-Betriebssytem handelt
	 */
	private boolean isWindows()
	{
		//Speicherung der Betriebssystemnames
		String os = System.getProperty("os.name");
		
		//�berpr�fung, ob Windows-Betriebssytem
		return os != null && os.startsWith("Windows");
	}
}