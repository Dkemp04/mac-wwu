package GUI;
import java.awt.*;

import javax.swing.*;

/**
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class TabOrganisation
{
	//Auf dieses Pane kann man Container adden, die dann als Tabs nacheinander dargestellt werden
	private JTabbedPane tabs;
	
	/**Konstruktor, der für dafür sorgt, dass das JTabbedPane erzeugt und eingestellt wird
	 * @param parent Vater-Container des JTabbedPanes
	 */
	public TabOrganisation(Container parent)
	{
		//Erzeugung und Einstellung eines neuen JTabbedPanes
		tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.setSize(320, 240);
		
		//Test-Tabs
		tabs.addTab("Hinweise", new JLabel("Hinweis"));
		tabs.addTab("Warnungen", new JLabel("Warnung"));		
		tabs.addTab("Fehler", new JLabel("Fehler"));
		
		//Hinzufügen des JTabbedPanes auf den übergebenen Vater-Container
		parent.add(tabs, BorderLayout.SOUTH);
	}
	
	/**Methode, die zu dem TabbedPane ein Tab hinzufügt mit übergebenen Titel und content
	 * @param title Titel der hinzuzufügenden Tabs
	 * @param content Inhalt-Container des hinzuzufügenden Tabs
	 */
	public void addTab (String title, Container content)
	{
		tabs.addTab(title, content);
	}
	
	/**Methode, die ein Tab anhand des übergebenen Titels sucht und deaktiviert
	 * @param title Titel des zu deaktivierenden Tabs
	 * @param status Status (aktiviert oder deaktiviert) auf welchen der Tab gesetzt werden soll
	 */
	public void setTabEnableStatus (String title, boolean status)
	{
		//Suche nach ersten Tabs, der dem angegebenen Titel entspricht
		for (int i = 0; i < tabs.getTabCount(); i++)
		{
			//Titel entspricht übergebenen Titel
			if (tabs.getTitleAt(i) == title)
			{
				//Setze den Status des ausgewählten Tabs
				tabs.setEnabledAt(i, status);
				
				//Ignoriere folgende Tabs mit gleichem Titel
				break;
			}
		}
	}
}