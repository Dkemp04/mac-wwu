package GUI.Main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import GUI.Dialogs.*;
import GUI.MapDisplay.*;
import GUI.Tools.*;
import Logic.*;
import Logic.Point;
import Storage.*;

/**
 * Menüleiste, welche im Hauptfenster eingeblendet wird und zur Navigation dient
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class Controlbar implements ActionListener
{
	//Vater-Container, in welchen diese Klasse angezeigt wird
	private Container parent;
	
	//Deklarierung der GUI-Elemente
	private JMenuBar menubar;			//Menüleiste
	private JMenu data;					//Menü "Datei"
	private JMenuItem newProblem;		//Menüelement "Neu" zur Erstellung neuer Probleme
	private JMenuItem open;				//Menüelement "Öffnen" zur Erstellung vorhandener Probleme
	private JMenuItem save;				//Menüelement "Speichern" zur Speicherung geöffneter Probleme
	private JMenuItem save_path;		//Menüelement "Speichern" zur Speicherung geöffneter Probleme unter bestimmten Pfad
	private JMenuItem workspace;
	private JMenuItem k_import;			//Menüelement "Speichern" zur Importierung von Karten
	private JMenuItem exit;				//Menüelement "Speichern" zum Schließen des Programmes
	private JMenu advanced;				//Menü "Erweitert"
	private JMenuItem options;			//Menüelement "Optionen" zum Aufruf der Optionen
	private JMenu help;					//Menü "Erweitert"
	private JMenuItem about;	
	private JMenuItem documentation;	//Menüelement "Dokumentation" zum Aufruf der Dokumentation bzw. Ausarbeitung zum Programm
	private JMenuItem javadoc;			//Menüelement "Java Doc" zum Aufruf der Java Doc mit allen im Programm enthaltenen Klassen
	
	/**
	 * Konstruktor zur Einstelllung und Darstellung der Elemente des Menüs
	 * @param base Vater-Container der ControlBar
	 */
	public Controlbar (Container parent)
	{
		//Vater-Container wird hier in der Klasse für weitere Zwecke zwischengespeichert
		this.parent = parent;
		
		//Erzeugung des Menüs, dessen Untermenüs und Menüelementen
		menubar = new JMenuBar();
		data = new JMenu("Datei");
		newProblem = new JMenuItem("Neu");
		open = new JMenuItem("Öffnen");
		save = new JMenuItem("Speichern");
		save_path = new JMenuItem("Speichern unter...");
		workspace = new JMenuItem("Setze Workspace");
		k_import = new JMenuItem("Import...");
		exit = new JMenuItem("Beenden");
		advanced = new JMenu("Erweitert");
		options = new JMenuItem("Optionen");
		help = new JMenu("Hilfe");
		about = new JMenuItem("Über");
		documentation = new JMenuItem("Dokumentation");
		javadoc = new JMenuItem("Java Doc");
		
		
		//Hinzufügen von Listener, welche auf Aktionen auf das Menü reagieren
		newProblem.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		save_path.addActionListener(this);
		workspace.addActionListener(this);
		k_import.addActionListener(this);
		exit.addActionListener(this);
		options.addActionListener(this);
		about.addActionListener(this);
		documentation.addActionListener(this);
		javadoc.addActionListener(this);
		
		//Hinzufügen der Untermenüs und Menüelemente zu der Menüleiste
		data.add(newProblem);
		data.add(open);
		data.addSeparator();
		data.add(save);
		data.add(save_path);
		data.addSeparator();
		data.add(workspace);
		data.add(k_import);
		data.addSeparator();
		data.add(exit);
		advanced.add(options);
		help.add(about);
		help.addSeparator();
		help.add(documentation);
		help.add(javadoc);
		menubar.add(data);
		menubar.add(advanced);
		menubar.add(help);
		parent.add(menubar, BorderLayout.NORTH);
	}
	
	/**
	 * Methode, die auf Aktionen auf ihr zugeordneter GUI-Elemente reagiert und ausgeführt wird
	 * @param event Ereignis, welches von den Listenern zugeordneten Elementen ausgelöst wurde
	 */
	public void actionPerformed(ActionEvent event)
	{
		//Überprüfung anhand der Beschriftung des Elementes, von welchem Element der Befehl bzw. die Aktion ausgegangen ist
		//Danach werden die elementspezifischen Aktionen aufgerufen
		if (event.getActionCommand().equals("Neu"))
		{
			//Eingabe-Dialog zur Erzeugung eines neuen Problems wird aufgerufen und eingestellt
			NewProblemDialog problem = new NewProblemDialog(parent);
			problem.setAlwaysOnTop(true);
			problem.setModal(true);
		}
		else if (event.getActionCommand().equals("Öffnen"))
		{
			//Eingabe-Dialog zum Öffnen von gespeicherten Problemen wird augerufen
			DataDialog open_dialog = new DataDialog();
			
			//Neues Problem wird erzeugt aus dem Rückgabewert des Dialogs
			Logic  openLogic = open_dialog.openLogic(MainFrame.WORKSPACE);
			
			//Graph zur Darstellung des Problems wird erzeugt
			Graph map = new Graph(MainFrame.WORKSPACE + "/images");
			
			if (openLogic != null)
			{
				for(Point add : openLogic.getProblem().getPoints()){
					map.addEllipse(add.getX(), add.getY());
				}
				StaticGraph display = new StaticGraph(parent, map);
				
				//größte x-und y-Koordinaten aller Punkte im Graphen werden gesucht
				int maxX = 0, maxY = 0;
				for (int i = 0; i < openLogic.getProblem().getPoints().size(); i++)
				{
					int curX = (int) openLogic.getProblem().getPoints().get(i).getX();
					int curY = (int) openLogic.getProblem().getPoints().get(i).getY();
					map.addEllipse(curX, curY);
					if (curX > maxX)
					{	maxX = curX;}
					if (curY > maxY)
					{	maxY = curY;}
				}
				
				//Neues JInternalFrame innerhalb der Arbeitsfläche des Hauptfensters wird erzeugt (maxX*2 bzw. maxY*2 dient zur Zentrierung der Darstellung)
				ChildFrame openChild = ((MainFrame) parent).getDesktop().addChildFrame(parent, map, openLogic, "Test", 10, 10, (maxY * 2) +10, maxX * 2);
				openChild.addTabToChildFrame("Ausgangssituation", display);
			}
		}
		else if (event.getActionCommand().equals("Speichern"))
		{
			ChildFrame selectedChild = (ChildFrame) ((MainFrame) parent).getDesktop().toJDesktopPane().getSelectedFrame();
			new ObjectSerialization().save(selectedChild.getTitle(), MainFrame.DEFAULT_ENDING, selectedChild.getLogic());
		}
		else if (event.getActionCommand().equals("Speichern unter..."))
		{
			ChildFrame selectedChild = (ChildFrame) ((MainFrame) parent).getDesktop().toJDesktopPane().getSelectedFrame();
			new DataDialog().saveLogic(selectedChild.getLogic(), MainFrame.DEFAULT_ENDING);
			
		}
		else if (event.getActionCommand().equals("Setze Workspace"))
		{				
			MainFrame.WORKSPACE = new DataDialog().openDirectory(MainFrame.WORKSPACE);
		}
		else if (event.getActionCommand().equals("Import..."))
		{
			String imagePath = null, imageName = null;
			imagePath = new DataDialog().openImage(MainFrame.WORKSPACE);
			if (imagePath != null)
				imageName = JOptionPane.showInputDialog(parent, "Bitte geben Sie das Land an, zu welchem die Karte zugeordnet werden soll.", "Bitte Land eingeben", JOptionPane.INFORMATION_MESSAGE);
			if (imagePath != null || imageName != null)
				((MainFrame) parent).getMapManager().addMap(imageName, imagePath);
			new ObjectSerialization().save("Maps", MainFrame.MAP_ENDING, ((MainFrame) parent).getMapManager());
		}
		else if (event.getActionCommand().equals("Beenden"))
		{
			//Programm und JavaVirtualMachine wird beendet
			System.exit(0);
		}
		else if (event.getActionCommand().equals("Optionen"))
		{
			//Neues Optionen-Fenster wird erzeugt und auf dem Bildschirm dargestellt
			new OptionsDialog(parent);
		}
		else if (event.getActionCommand().equals("Über"))
		{
			//
			new AboutDialog(parent);
		}
		else if (event.getActionCommand().equals("Dokumentation"))
		{
			//Öffnet die Dokumentation im Browser, welche Installationanweisungen und die Ausarbeitung enthält
			new OpenURL("www.google.de", OpenURL.WEB_FILE);
		}
		else if (event.getActionCommand().equals("Java Doc"))
		{
			//Öffnet die Java Doc des Programmes im Browser mit allen im Programm enthaltenen Klassen
			new OpenURL(MainFrame.WORKSPACE + "/doc/index.html", OpenURL.LOCAL_FILE);
		}
	}
}