package GUI;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import Logik.*;

/**
 * Men�leiste, welche im Hauptfenster eingeblendet wird und zur Navigation dient
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class Controlbar implements ActionListener
{
	//Vater-Container, in welchen diese Klasse angezeigt wird
	private MainFrame parent;
	
	//Deklarierung der GUI-Elemente
	private JMenuBar menubar;			//Men�leiste
	private JMenu data;					//Men� "Datei"
	private JMenuItem newProblem;		//Men�element "Neu" zur Erstellung neuer Probleme
	private JMenuItem open;				//Men�element "�ffnen" zur Erstellung vorhandener Probleme
	private JMenuItem save;				//Men�element "Speichern" zur Speicherung ge�ffneter Probleme
	private JMenuItem save_path;		//Men�element "Speichern" zur Speicherung ge�ffneter Probleme unter bestimmten Pfad
	private JMenuItem workspace;
	private JMenuItem k_import;			//Men�element "Speichern" zur Importierung von Karten
	private JMenuItem exit;				//Men�element "Speichern" zum Schlie�en des Programmes
	private JMenu advanced;				//Men� "Erweitert"
	private JMenuItem options;			//Men�element "Optionen" zum Aufruf der Optionen
	private JMenu help;					//Men� "Erweitert"
	private JMenuItem documentation;	//Men�element "Dokumentation" zum Aufruf der Dokumentation bzw. Ausarbeitung zum Programm
	private JMenuItem javadoc;			//Men�element "Java Doc" zum Aufruf der Java Doc mit allen im Programm enthaltenen Klassen
	
	/**
	 * Konstruktor zur Einstelllung und Darstellung der Elemente des Men�s
	 * @param base Vater-Container der ControlBar
	 */
	public Controlbar (MainFrame parent)
	{
		//Vater-Container wird hier in der Klasse f�r weitere Zwecke zwischengespeichert
		this.parent = parent;
		
		//Erzeugung des Men�s, dessen Untermen�s und Men�elementen
		menubar = new JMenuBar();
		data = new JMenu("Datei");
		newProblem = new JMenuItem("Neu");
		open = new JMenuItem("�ffnen");
		save = new JMenuItem("Speichern");
		save_path = new JMenuItem("Speichern unter...");
		workspace = new JMenuItem("Setze Workspace");
		k_import = new JMenuItem("Import...");
		exit = new JMenuItem("Beenden");
		advanced = new JMenu("Erweitert");
		options = new JMenuItem("Optionen");
		help = new JMenu("Hilfe");
		documentation = new JMenuItem("Dokumentation");
		javadoc = new JMenuItem("Java Doc");
		
		
		//Hinzuf�gen von Listener, welche auf Aktionen auf das Men� reagieren
		newProblem.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		save_path.addActionListener(this);
		workspace.addActionListener(this);
		k_import.addActionListener(this);
		exit.addActionListener(this);
		options.addActionListener(this);
		documentation.addActionListener(this);
		javadoc.addActionListener(this);
		
		//Hinzuf�gen der Untermen�s und Men�elemente zu der Men�leiste
		data.add(newProblem);
		data.add(open);
		data.add(save);
		data.add(save_path);
		data.add(workspace);
		data.add(k_import);
		data.add(exit);
		advanced.add(options);
		help.add(documentation);
		help.add(javadoc);
		menubar.add(data);
		menubar.add(advanced);
		menubar.add(help);
		parent.add(menubar, BorderLayout.NORTH);
	}
	
	/**
	 * Methode, die auf Aktionen auf ihr zugeordneter GUI-Elemente reagiert und ausgef�hrt wird
	 * @param event Ereignis, welches von den Listenern zugeordneten Elementen ausgel�st wurde
	 */
	public void actionPerformed(ActionEvent event)
	{
		//�berpr�fung anhand der Beschriftung des Elementes, von welchem Element der Befehl bzw. die Aktion ausgegangen ist
		//Danach werden die elementspezifischen Aktionen aufgerufen
		if (event.getActionCommand().equals("Neu"))
		{
			//Eingabe-Dialog zur Erzeugung eines neuen Problems wird aufgerufen und eingestellt
			NewProblemDialog problem = new NewProblemDialog(parent);
			problem.setAlwaysOnTop(true);
			problem.setModal(true);
		}
		if (event.getActionCommand().equals("�ffnen"))
		{
			//Eingabe-Dialog zum �ffnen von gespeicherten Problemen wird augerufen
			OpenDialog open_dialog = new OpenDialog();
			
			//Neues Problem wird erzeugt aus dem R�ckgabewert des Dialogs
			Logic  openLogic = open_dialog.openLogic(MainFrame.WORKSPACE);
			
			//Graph zur Darstellung des Problems wird erzeugt
			Graph map = new Graph();
			
			if (openLogic != null)
			{
				for(Logik.Point add : openLogic.getProblem().getPoints()){
					map.addEllipse(add.getX(), add.getY());
				}
				StaticGraph display = new StaticGraph(parent, map);
				
				//gr��te x-und y-Koordinaten aller Punkte im Graphen werden gesucht
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
				
				//Neues JInternalFrame innerhalb der Arbeitsfl�che des Hauptfensters wird erzeugt (maxX*2 bzw. maxY*2 dient zur Zentrierung der Darstellung)
				((MainFrame) parent).getDesktop().addChildFrame(parent, display, map, openLogic, "Test", 10, 10, (maxY * 2) +10, maxX * 2);
			}
		}
		if (event.getActionCommand().equals("Speichern"))
		{					parent.getDesktop().getDesktopPane().getSelectedFrame();}
		if (event.getActionCommand().equals("Speichern unter..."))
		{					}
		if (event.getActionCommand().equals("Setze Workspace"))
		{				
			MainFrame.WORKSPACE = new WorkspaceDialog().openDirectory(MainFrame.WORKSPACE);
		}
		if (event.getActionCommand().equals("Import..."))
		{					
			Image newImage = new OpenDialog().openImage(MainFrame.WORKSPACE);
			((MainFrame) parent).addMap("Test");
		}
		if (event.getActionCommand().equals("Beenden"))
		{
			//Programm und JavaVirtualMachine wird beendet
			System.exit(0);
		}
		if (event.getActionCommand().equals("Optionen"))
		{
			//Neues Optionen-Fenster wird erzeugt und auf dem Bildschirm dargestellt
			new Options(parent);
		}
		if (event.getActionCommand().equals("Dokumentation"))
		{
			//�ffnet die Dokumentation im Browser, welche Installationanweisungen und die Ausarbeitung enth�lt
			new OpenURL("www.google.de", OpenURL.LOCAL_FILE);
		}
		if (event.getActionCommand().equals("Java Doc"))
		{
			//�ffnet die Java Doc des Programmes im Browser mit allen im Programm enthaltenen Klassen
			new OpenURL(MainFrame.WORKSPACE + "/doc/index.html", OpenURL.LOCAL_FILE);
		}
	}
}