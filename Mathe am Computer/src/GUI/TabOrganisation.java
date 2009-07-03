package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TabOrganisation
{
	public TabOrganisation(Container base)
	{
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.addTab("Hinweise", new Tab("Hinweis"));
		tabs.addTab("Warnungen", new Tab("Warnung"));		
		tabs.addTab("Fehler", new Tab("Fehler"));
		tabs.setSize(320, 240);
		base.add(tabs, BorderLayout.SOUTH);
	}
}