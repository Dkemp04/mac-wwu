package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Klasse, die f�r die Darstellung des JDesktopPanes sorgt und dessen Kinder
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class DesktopArea
{
	//Deklarierung der serialVersionUID f�r die serialisierbare Klasse DesktopArea
	private static final long serialVersionUID = 8244608250833559146L;
	
	//Desktop-Bereich auf welchen die internen Frames angezeigt werden
	private JDesktopPane desk;
	
	/**Konstruktor, welcher den Desktopbereich erzeugt und einstellt
	 * @param parent Vater-Container, in welchem der Desktopbereich hinzugef�gt werden soll
	 */
	public DesktopArea (Container parent)
	{
		//Erzeugung des Desktopbereichs
		desk = new JDesktopPane();
		
		//Einstellung des Desktop-Bereichs
		desk.setBackground(Color.WHITE);
		desk.setBorder(BorderFactory.createEtchedBorder());
		desk.setSize(1024,768);
		
		//Test-ChildFrames
		addChildFrame(new JPanel(), "Child 1", 30, 30, 200, 150);
		addChildFrame(new JPanel(), "Child 2", 230, 30, 200, 150);
		
		//Hinzuf�gen des Desktopbereichs zum Vater-Container
		parent.add(desk);
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
	public JInternalFrame addChildFrame (Container content, String title, int x, int y, int height, int width)
	{	
		//Erzeugung des neuen ChildFrames und Einstellung des Titels
		JInternalFrame child = new JInternalFrame(title);
		
		//Generelle Einstellung des Fensterverhaltens des ChildFrames
		child.setResizable(true);
		child.setClosable(true);
		child.setMaximizable(true);
		child.setIconifiable(true);
		
		//Setzen des contentPanes auf den �bergebenen Container
		child.setContentPane(content);
		
		//Einstellung der Position des ChildFrames innerhalb des Desktopbereichs
		child.setLocation(x, y);
		
		//Einstellung der Gr�sse des ChildFrames
		child.setSize(height, width);
		
		//Einstellung der Schlie�operation
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		//Hinzuf�gen des passenden Listeners
		child.addInternalFrameListener(new MyInternalFrameListener());
		
		//Einblenden des ChildFrames
		child.setVisible(true);
		
		//Hinzuf�gen und Zur�ckgeben des ChildFrames
		desk.add(child);
		return child;
	}
	
	/**Listener-Klasse, welcher auf Aktionen auf die ChildFrames reagiert
	 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
	 */
	private class MyInternalFrameListener extends InternalFrameAdapter
	{
		/**Methode, die beim Schlie�en des ChildFrames ausgel�st wird
		 * @param e Ereignis, welches bei Aktionen ihr zugeordneter Elemente ausgel�st wird
		 */
		public void internalFrameClosing (InternalFrameEvent e)
		{
			//�ffnet einen Dialog, ob das aktuelles Problem gespeichert werden soll und zwischenspeichert das Ergebnis des Dialogs
			int result = JOptionPane.showConfirmDialog(null, new Label("M�chten Sie das Problem vor dem Beenden speichern ?"), "Speichern", JOptionPane.YES_NO_OPTION, 3);
			
			//�berpr�fung, ob bei dem Dialog "Ja" ausgew�hlt wurde
			if (result == JOptionPane.YES_OPTION)
			{
				//�ffnen eines Speicher-Dialoges
				new SaveDialog().save();
			}
			else
			{
				//Falls nicht gespeichert werden soll, wird das ChildFrame ausgeblendet
				e.getInternalFrame().dispose();
			}
		}
	}
}