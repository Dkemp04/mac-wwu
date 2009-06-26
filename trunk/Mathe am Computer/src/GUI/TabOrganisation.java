package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TabOrganisation
{
	public TabOrganisation(Container base)
	{
		JTabbedPane tabs = new JTabbedPane(/*JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT*/);
		tabs.addTab("Test1", new Tab());
		tabs.addTab("Test2", new Tab());
		tabs.setSize(320, 240);
		base.add(tabs, BorderLayout.CENTER);
	}
}