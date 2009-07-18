package GUI.Main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import GUI.Dialogs.*;
import GUI.Tools.*;

/**Hauptklasse, die zur Erzeugung aller GUI-Elemente des Hauptfensters zuständig ist
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class MainFrame extends JFrame implements WindowListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse MainFrame
	private static final long serialVersionUID = 995601029595640937L;

	public static String WORKSPACE = "C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer";
	public static final String DEFAULT_ENDING = "tsp";
	public static final String JPG_ENDING = "jpg";
	public static final String JPEG_ENDING = "jpeg";
	public static final String GIF_ENDING = "gif";
	public static final String PNG_ENDING = "png";
	public static final String MAP_ENDING = "map";
	public static final int w = 1024, h = 768;
	
	private MapManager manager;
	
	//Deklarierung der GUI-Elemente	
	private DesktopArea desktop;		//Desktop-Bereich, in dem interne Fenster erzeugt werden können. Dieser Bereich dient zur Darstellung der Probleme, der Heuristiken und der Vergleiche der Heuristiken
	
	/**Konstruktor, der die Darstellung ein Einstellung aller GUI-Elemente übernimmt
	 */
	public MainFrame ()
	{
		//Aufruf des Konstruktors der Superklasse und Einstellung des Titels
		super("Traveller");
		
		//try -und catch-Block, der zur Einstellung des Windows-Look-and-Feels dient
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		//Abfangen von Exceptions, die bei der Umschaltung des Look-and-Feels auftreten können und Ausgabe von Fehlermeldungen
		catch (Exception e)
		{	System.err.println(e.toString());}
		
		//Menü-Leiste zum Aufruf der wesentlichen Funktionen des Programmes
		new Controlbar(this);
		
		desktop = new DesktopArea(this);
		//new Summary(this);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(WORKSPACE + "/images/icon.gif"));
		manager = new MapManager();
		
		//Allgemeine Einstellung des Hauptfenster
		this.addWindowListener(this);
		this.setLocation(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2)- (w / 2) , ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - (h / 2) );
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
	
	
	public void windowClosing (WindowEvent e)
	{
		while (getDesktop().toJDesktopPane().getAllFrames().length > 0)
		{
			ChildFrame selectedChild = (ChildFrame) getDesktop().toJDesktopPane().getSelectedFrame();
			selectedChild.internalFrameClosing(new InternalFrameEvent(selectedChild, InternalFrameEvent.INTERNAL_FRAME_CLOSING));
		}
		System.exit(0);
	}
	public void windowDeactivated (WindowEvent e){}
	public void windowDeiconified (WindowEvent e){}
	public void windowOpened (WindowEvent e){}
	public void windowIconified (WindowEvent e){}
	public void windowClosed (WindowEvent e){}
	public void windowActivated (WindowEvent e){}
	
	/**Main-Methode zum Start des Programmes zurückgibt
	 */
	public static void main(String[] args)
	{
		SplashDialog splash = new SplashDialog(WORKSPACE + "/images/logo.jpg", 800, 600, 3000);
		splash.start();
		while (splash.isRunning()){}
		new MainFrame();
	}
}