package GUI.Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import GUI.Dialogs.*;
import GUI.MapDisplay.*;
import Logic.Logic;
import Methods.*;

public class ChildFrame extends JInternalFrame implements InternalFrameListener
{
	private static final long serialVersionUID = -8268041752214054122L;
	
	private Logic logic;
	private Graph map;
	private TabOrganisation tabs = new TabOrganisation(this);
	
	/**Konstruktor, die daf�r sorgt, dass ChildFrames innerhalb des Desktop-Bereichs erzeugt und eingestellt werden
	 * @param content	Container, welcher im ChildFrames dargestellt werden soll
	 * @param title		Titel des ChildFrames
	 * @param x			x-Position des ChildFrames
	 * @param y			y-Position des ChildFrames
	 * @param height	H�he des ChildFrames
	 * @param width		Breite des ChildFrames
	 */
	public ChildFrame (Container parent, Container content, Logic logic, String title, int x, int y,int width, int height)
	{
		//Allgemeine Einstellung des neuen ChildFrames
		super(title, true, true, false, true);
		
		this.map = (Graph) content;
		
		this.logic = logic;
		this.logic.setCallback(this);
		this.logic.start();
		
		this.add(tabs);
		
		//Einstellung der Schlie�operation
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		//Hinzuf�gen des passenden Listeners
		this.addInternalFrameListener(this);
		
		//Einstellung der Position des ChildFrames innerhalb des Desktopbereichs
		this.setLocation(x, y);
		
		//Einstellung der Gr�sse des ChildFrames
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
	
	public Logic getLogic ()
	{		return this.logic;}
	
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
			new SaveDialog().save(logic);
		}
		else if (result == JOptionPane.NO_OPTION)
		{
			this.setVisible(true);
			return;
		}
		else
		{
			//Falls nicht gespeichert werden soll, wird das ChildFrame ausgeblendet
			e.getInternalFrame().dispose();
		}
	}
	public void internalFrameOpened (InternalFrameEvent e){}
	public void internalFrameClosed(InternalFrameEvent e){}
	public void internalFrameActivated (InternalFrameEvent e){}
	public void internalFrameIconified (InternalFrameEvent e){}
	public void internalFrameDeactivated (InternalFrameEvent e){}
	public void internalFrameDeiconified (InternalFrameEvent e){}
}