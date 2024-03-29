package GUI.Main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import GUI.Dialogs.*;
import GUI.MapDisplay.*;
import GUI.Tools.*;
import Logic.*;
import Logic.Point;

/**
 * Men�leiste, welche im Hauptfenster eingeblendet wird und zur Navigation dient
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class Controlbar implements ActionListener
{
	//Vater-Container, in welchen diese Klasse angezeigt wird
	private Container parent;
	
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
	private JMenuItem about;	
	private JMenuItem documentation;	//Men�element "Dokumentation" zum Aufruf der Dokumentation bzw. Ausarbeitung zum Programm
	private JMenuItem javadoc;			//Men�element "Java Doc" zum Aufruf der Java Doc mit allen im Programm enthaltenen Klassen
	
	/**
	 * Konstruktor zur Einstelllung und Darstellung der Elemente des Men�s
	 * @param base Vater-Container der ControlBar
	 */
	public Controlbar (Container parent)
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
		about = new JMenuItem("�ber");
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
		about.addActionListener(this);
		documentation.addActionListener(this);
		javadoc.addActionListener(this);
		
		//
		newProblem.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
		javadoc.setAccelerator(KeyStroke.getKeyStroke('J', InputEvent.CTRL_DOWN_MASK));
		
		//
		newProblem.setMnemonic('N');
		open.setMnemonic('�');
		save.setMnemonic('S');
		save_path.setMnemonic('U');
		workspace.setMnemonic('W');
		exit.setMnemonic('B');
		options.setMnemonic('O');
		about.setMnemonic('�');
		documentation.setMnemonic('D');
		javadoc.setMnemonic('J');
		
		//Hinzuf�gen der Untermen�s und Men�elemente zu der Men�leiste
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
			new NewProblemDialog(parent);
		}
		else if (event.getActionCommand().equals("�ffnen"))
		{			
			//Neues Problem wird erzeugt aus dem R�ckgabewert des Dialogs
			Logic  openLogic = new DataDialog().openLogic(MainFrame.DEFAULT_ENDING);
			
			//Graph zur Darstellung des Problems wird erzeugt
			Graph map = new Graph(MainFrame.WORKSPACE + "/images");
			
			if (openLogic != null)
			{
				for(Point add : openLogic.getProblem().getPoints())
				{
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
				ChildFrame openChild = ((MainFrame) parent).getDesktop().addChildFrame(parent, map, openLogic, openLogic.getName(), 10, 10, (maxY * 2) +10, maxX * 2);
				openChild.addTabToChildFrame("Ausgangssituation", display);
			}
		}
		else if (event.getActionCommand().equals("Speichern"))
		{
			ChildFrame selectedChild = (ChildFrame) ((MainFrame) parent).getDesktop().toJDesktopPane().getSelectedFrame();
			if (selectedChild != null)
				StorageOrganisation.save(selectedChild.getTitle(), MainFrame.DEFAULT_ENDING, selectedChild.getLogic());
		}
		else if (event.getActionCommand().equals("Speichern unter..."))
		{
			ChildFrame selectedChild = (ChildFrame) ((MainFrame) parent).getDesktop().toJDesktopPane().getSelectedFrame();
			if (selectedChild != null)
				new DataDialog().saveLogic(selectedChild.getLogic(), MainFrame.DEFAULT_ENDING);
			
		}
		else if (event.getActionCommand().equals("Setze Workspace"))
		{
			String workspace = new DataDialog().openDirectory(MainFrame.WORKSPACE);
			if (workspace != null)
				MainFrame.WORKSPACE = workspace;
		}
		else if (event.getActionCommand().equals("Import..."))
		{
			String imagePath = null, imageName = null;
			imagePath = new DataDialog().openImage(MainFrame.WORKSPACE);
			if (imagePath != null)
				imageName = JOptionPane.showInputDialog(parent, "Bitte geben Sie das Land an, zu welchem die Karte zugeordnet werden soll.", "Bitte Land eingeben", JOptionPane.INFORMATION_MESSAGE);
			if (imagePath != null || imageName != null)
				((MainFrame) parent).getMapManager().addMap(imageName, imagePath);
			StorageOrganisation.save("Maps", MainFrame.MAP_ENDING, ((MainFrame) parent).getMapManager());
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
		else if (event.getActionCommand().equals("�ber"))
		{
			//
			new AboutDialog(parent);
		}
		else if (event.getActionCommand().equals("Dokumentation"))
		{
			//�ffnet die Dokumentation im Browser, welche Installationanweisungen und die Ausarbeitung enth�lt
			new OpenURL("www.google.de", OpenURL.WEB_FILE);
		}
		else if (event.getActionCommand().equals("Java Doc"))
		{
			//�ffnet die Java Doc des Programmes im Browser mit allen im Programm enthaltenen Klassen
			new OpenURL(MainFrame.WORKSPACE + "/doc/index.html", OpenURL.LOCAL_FILE);
		}
	}
}