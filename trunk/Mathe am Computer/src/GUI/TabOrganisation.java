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
	
	public void addTab (String title, Container content)
	{
		tabs.addTab(title, content);
	}
	
	public void setTabEnableStatus (String title, boolean status)
	{
		for (int i = 0; i < tabs.getTabCount(); i++)
		{
			if (tabs.getTitleAt(i) == title)
				tabs.setEnabledAt(i, status);
		}
	}
}