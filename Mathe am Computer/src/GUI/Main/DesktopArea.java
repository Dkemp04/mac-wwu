package GUI.Main;

import java.awt.*;
import javax.swing.*;
import GUI.MapDisplay.*;
import Logic.*;

/**
 * Klasse, die f�r die Darstellung des JDesktopPanes sorgt und dessen Kinder
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class DesktopArea extends JDesktopPane
{
	//Deklarierung der serialVersionUID f�r die serialisierbare Klasse DesktopArea
	private static final long serialVersionUID = 8244608250833559146L;
	
	//Desktop-Bereich auf welchen die internen Frames angezeigt werden
	//private JDesktopPane desk;
	
	/**Konstruktor, welcher den Desktopbereich erzeugt und einstellt
	 * @param parent Vater-Container, in welchem der Desktopbereich hinzugef�gt werden soll
	 */
	public DesktopArea (Container parent)
	{		
		//Einstellung des Desktop-Bereichs
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setSize(parent.getWidth(),parent.getHeight());
		
		//Hinzuf�gen des Desktopbereichs zum Vater-Container
		parent.add(this);
	}
	
	/**Methode, die daf�r sorgt, dass ChildFrames innerhalb des Deesktop-Bereichs erzeugt und eingestellt werden
	 * @param content	Container, welcher im ChildFrames dargestellt werden soll
	 * @param title		Titel des ChildFrames
	 * @param x			x-Position des ChildFrames
	 * @param y			y-Position des ChildFrames
	 * @param height	H�he des ChildFrames
	 * @param width		Breite des ChildFrames
	 * @return			Gibt das neu erzeugte ChildFrames zur�ck
	 */
	public ChildFrame addChildFrame (Container parent, Graph map, Logic newLogic, String title, int x, int y, int height, int width)
	{	
		//Erzeugung des neuen ChildFrames und Einstellung des Titels
		ChildFrame child = new ChildFrame(parent, map, newLogic, title, x, y, height, width);
		
		//Hinzuf�gen und Zur�ckgeben des ChildFrames
		this.add(child);
		this.setSelectedFrame(child);
		return child;
	}
	public void addChildFrame (JInternalFrame child)
	{		this.add(child);this.setSelectedFrame(child);}
	
	public JDesktopPane toJDesktopPane()
	{		return this;}
}