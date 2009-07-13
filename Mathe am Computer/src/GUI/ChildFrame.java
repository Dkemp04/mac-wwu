package GUI;

import java.awt.Container;
import java.awt.Label;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import Logic.Logic;
import Methods.*;

public class ChildFrame extends JInternalFrame
{
	private static final long serialVersionUID = -8268041752214054122L;
	
	private Logic logic;
	private Graph map;
	private TabOrganisation tabs = new TabOrganisation(this);
	
	/**Konstruktor, die dafür sorgt, dass ChildFrames innerhalb des Desktop-Bereichs erzeugt und eingestellt werden
	 * @param content	Container, welcher im ChildFrames dargestellt werden soll
	 * @param title		Titel des ChildFrames
	 * @param x			x-Position des ChildFrames
	 * @param y			y-Position des ChildFrames
	 * @param height	Höhe des ChildFrames
	 * @param width		Breite des ChildFrames
	 */
	public ChildFrame (Container parent, Container content, Logic logic, String title, int x, int y,int width, int height)
	{
		//Allgemeine Einstellung des neuen ChildFrames
		super(title, true, true, false, true);
		this.setResizable(false);
		
		this.map = (Graph) content;
		
		this.logic = logic;
		this.logic.setCallback(this);
		this.logic.start();
		
		this.add(tabs);
		
		//Einstellung der Schließoperation
		this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		
		//Hinzufügen des passenden Listeners
		this.addInternalFrameListener(new MyInternalFrameListener());
		
		//Einstellung der Position des ChildFrames innerhalb des Desktopbereichs
		this.setLocation(x, y);
		
		//Einstellung der Grösse des ChildFrames
		this.setSize(width, height);
		
		//Einblenden des ChildFrames
		this.setVisible(true);
	}
	
	public void addTabToChildFrame (String tab_title, Container content)
	{	tabs.addTab(tab_title, content);}
	
	public void logicCallback (Method m)
	{
		tabs.addTab(m.getMethodName(), new StaticGraph(this.getParent(), map, m.getHistory()));
	}
	
	/**Listener-Klasse, welcher auf Aktionen auf die ChildFrames reagiert
	 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
	 */
	private class MyInternalFrameListener extends InternalFrameAdapter
	{
		/**Methode, die beim Schließen des ChildFrames ausgelöst wird
		 * @param e Ereignis, welches bei Aktionen ihr zugeordneter Elemente ausgelöst wird
		 */
		public void internalFrameClosing (InternalFrameEvent e)
		{
			//Öffnet einen Dialog, ob das aktuelles Problem gespeichert werden soll und zwischenspeichert das Ergebnis des Dialogs
			int result = JOptionPane.showConfirmDialog(null, new Label("Möchten Sie das Problem vor dem Beenden speichern ?"), "Speichern", JOptionPane.YES_NO_OPTION, 3);
			
			//Überprüfung, ob bei dem Dialog "Ja" ausgewählt wurde
			if (result == JOptionPane.YES_OPTION)
			{
				//Öffnen eines Speicher-Dialoges
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