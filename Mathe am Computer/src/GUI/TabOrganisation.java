package GUI;
import java.awt.*;
import javax.swing.*;

/**
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class TabOrganisation extends JTabbedPane
{
	private static final long serialVersionUID = -2568355763034745400L;
	
	/**Konstruktor, der für dafür sorgt, dass das JTabbedPane erzeugt und eingestellt wird
	 * @param parent Vater-Container des JTabbedPanes
	 */
	public TabOrganisation(Container parent)
	{
		//Erzeugung und Einstellung eines neuen JTabbedPanes
		super(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setSize(320, 240);
		
		//Hinzufügen des JTabbedPanes auf den übergebenen Vater-Container
		parent.add(this, BorderLayout.SOUTH);
	}
	
	/**Methode, die ein Tab anhand des übergebenen Titels sucht und deaktiviert
	 * @param title Titel des zu deaktivierenden Tabs
	 * @param status Status (aktiviert oder deaktiviert) auf welchen der Tab gesetzt werden soll
	 */
	public void setTabEnableStatus (String title, boolean status)
	{
		//Suche nach ersten Tabs, der dem angegebenen Titel entspricht
		for (int i = 0; i < this.getTabCount(); i++)
		{
			//Titel entspricht übergebenen Titel
			if (this.getTitleAt(i) == title)
			{
				//Setze den Status des ausgewählten Tabs
				this.setEnabledAt(i, status);
				
				//Ignoriere folgende Tabs mit gleichem Titel
				break;
			}
		}
	}
}