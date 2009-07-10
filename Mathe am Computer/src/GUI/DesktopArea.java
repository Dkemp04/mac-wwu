package GUI;
import java.awt.*;
import javax.swing.*;
import Logik.*;

/**
 * Klasse, die für die Darstellung des JDesktopPanes sorgt und dessen Kinder
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class DesktopArea
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse DesktopArea
	private static final long serialVersionUID = 8244608250833559146L;
	
	//Desktop-Bereich auf welchen die internen Frames angezeigt werden
	private JDesktopPane desk;
	
	/**Konstruktor, welcher den Desktopbereich erzeugt und einstellt
	 * @param parent Vater-Container, in welchem der Desktopbereich hinzugefügt werden soll
	 */
	public DesktopArea (MainFrame parent)
	{
		//Erzeugung des Desktopbereichs
		desk = new JDesktopPane();
		
		//Einstellung des Desktop-Bereichs
		desk.setBackground(Color.WHITE);
		desk.setBorder(BorderFactory.createEtchedBorder());
		desk.setSize(1280,1024);
		
		//Test-ChildFrames
		//addChildFrame(new JPanel(), "Child 1", 30, 30, 200, 150);
		//addChildFrame(new JPanel(), "Child 2", 230, 30, 200, 150);
		
		//Hinzufügen des Desktopbereichs zum Vater-Container
		parent.add(desk);
	}
	
	/**Methode, die dafür sorgt, dass ChildFrames innerhalb des Deesktop-Bereichs erzeugt und eingestellt werden
	 * @param content	Container, welcher im ChildFrames dargestellt werden soll
	 * @param title		Titel des ChildFrames
	 * @param x			x-Position des ChildFrames
	 * @param y			y-Position des ChildFrames
	 * @param height	Höhe des ChildFrames
	 * @param width		Breite des ChildFrames
	 * @return			Gibt das neu erzeugte ChildFrames zurück
	 */
	public ChildFrame addChildFrame (Container parent, Container content, Graph map, Logic newLogic, String title, int x, int y, int height, int width)
	{	
		//Erzeugung des neuen ChildFrames und Einstellung des Titels
		ChildFrame child = new ChildFrame(parent, content, map, newLogic, title, x, y, height, width);
		
		child.setOpaque(false);
		
		//Hinzufügen und Zurückgeben des ChildFrames
		desk.add(child);
		desk.setSelectedFrame(child);
		return child;
	}
	public void addChildFrame (JInternalFrame child)
	{
		child.setOpaque(false);
		desk.add(child);
		desk.setSelectedFrame(child);
	}
	
	public JDesktopPane getDesktopPane()
	{
		return desk;
	}
	
	//public void addTabToChildFrame (ChildFrame child, String tab_title, Container content)
	//{		child.addTab(tab_title, content);}
}