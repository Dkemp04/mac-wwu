package GUI;
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
	
	MapManager manager;
	
	
	//Deklarierung der GUI-Elemente
	private DesktopArea desktop;		//Desktop-Bereich, in dem interne Fenster erzeugt werden können. Dieser Bereich dient zur Darstellung der Probleme, der Heuristiken und der Vergleiche der Heuristiken
	private Controlbar controls;		//Menü-Leiste zum Aufruf der wesentlichen Funktionen des Programmes
	private TabOrganisation output;		//Verwaltung und Darstellung von Tabs zur Anzeige von Fehler, Warnungen und Hinweisen
	
	/**Konstruktor, der die Darstellung ein Einstellung aller GUI-Elemente übernimmt
	 */
	public MainFrame ()
	{
		//Aufruf des Konstruktors der Superklasse und Einstellung des Titels
		super("Travelling Salesman");
		
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
		output = new TabOrganisation(this);
		desktop = new DesktopArea(this);
		
		//Allgemeine Einstellung des Hauptfenster
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(150,150);
		this.setSize(1024,768);
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
	
	/**Main-Mathode zum Start des Programmes zurückgibt
	 */
	public static void main(String[] args)
	{		new MainFrame();}
}