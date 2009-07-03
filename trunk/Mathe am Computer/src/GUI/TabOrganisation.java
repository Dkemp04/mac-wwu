package GUI;
import java.awt.*;
import java.util.*;

import javax.swing.*;

public class TabOrganisation
{
	JTabbedPane tabs;
	LinkedList<JPanel> tabList = new LinkedList<JPanel>();
	
	public TabOrganisation(JFrame parent)
	{
		tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Hinweise", new JLabel("Hinweis"));
		tabs.addTab("Warnungen", new JLabel("Warnung"));		
		tabs.addTab("Fehler", new JLabel("Fehler"));
		tabs.setSize(320, 240);
		parent.add(tabs, BorderLayout.SOUTH);
	}
}