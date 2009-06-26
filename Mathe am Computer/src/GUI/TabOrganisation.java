package GUI;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class TabOrganisation
{
	public TabOrganisation(Container base)
	{
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.insertTab("Test1",null, new Tab(), "Test", 0);
		tabs.insertTab("Test2",null, new Tab(), "Test", 1);
		tabs.setSize(100, 100);
		base.add(tabs, BorderLayout.SOUTH);
	}
}