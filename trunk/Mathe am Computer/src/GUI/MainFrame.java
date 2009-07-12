package GUI;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import javax.swing.*;

/**Hauptklasse, die zur Erzeugung aller GUI-Elemente des Hauptfensters zuständig ist
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class MainFrame extends JFrame
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse MainFrame
	private static final long serialVersionUID = 995601029595640937L;

	public static String WORKSPACE = "C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer";
	public static final int w = 1024, h = 768;
	MapManager manager;
	
	//Deklarierung der GUI-Elemente
	private DesktopArea desktop;		//Desktop-Bereich, in dem interne Fenster erzeugt werden können. Dieser Bereich dient zur Darstellung der Probleme, der Heuristiken und der Vergleiche der Heuristiken
	private Controlbar controls;		//Menü-Leiste zum Aufruf der wesentlichen Funktionen des Programmes
	
	/**Konstruktor, der die Darstellung ein Einstellung aller GUI-Elemente übernimmt
	 */
	public MainFrame ()
	{
		//Aufruf des Konstruktors der Superklasse und Einstellung des Titels
		super("Traveller");
		manager = new MapManager();
		
		//try -und catch-Block, der zur Einstellung des Windows-Look-and-Feels dient
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		//Abfangen von Exceptions, die bei der Umschaltung des Look-and-Feels auftreten können und Ausgabe von Fehlermeldungen
		catch (Exception e) {
			System.err.println(e.toString());}
		
		//Initialisierung aller GUI-Elemente und Übergabe des Hauptfensters als Vater-Container
		controls = new Controlbar(this);
		desktop = new DesktopArea(this);
		
		//Test
		controls.toString();
		
		//Allgemeine Einstellung des Hauptfenster
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		this.setLocation(((int) d.getWidth() - w) / 2, ((int) d.getHeight() - h) / 2);
		this.setSize(w, h);
	    this.setVisible(true);
	}
	
	/**Get-Methode, die die DesktopArea des Fensters 
	 * @return Gibt den Desktopbereich des Hauptfensters zurück
	 */
	public DesktopArea getDesktop ()
	{		return desktop;}
	
	public LinkedList<MapManager.Map> getMaps ()
	{		return manager.getMaps();}
	
	public MapManager getMapManager ()
	{		return manager;}
	
	/**Main-Methode zum Start des Programmes zurückgibt
	 */
	public static void main(String[] args)
	{
		Splash test = new Splash(WORKSPACE + "/images/logo.jpg", 800, 600, 3000);
		test.start();
		while (test.isRunning()){}
		new MainFrame();
		
	}
}