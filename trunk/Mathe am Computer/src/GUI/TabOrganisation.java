package GUI;
import java.awt.*;

import javax.swing.*;

/**
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class TabOrganisation
{
	private JTabbedPane tabs;
	
	public TabOrganisation(JFrame parent)
	{
		tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Hinweise", new JLabel("Hinweis"));
		tabs.addTab("Warnungen", new JLabel("Warnung"));		
		tabs.addTab("Fehler", new JLabel("Fehler"));
		tabs.setSize(320, 240);
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