package GUI.MapDisplay;

import java.awt.*;
import javax.swing.*;

public class Summary extends JComponent
{
	private static final long serialVersionUID = -5804582430610857571L;
	
	private JTable table;
	
	String[] header = {"Spalte 1", "Spalte 2", "Spalte 3", "Spalte 4", "Spalte 5"};
	Object[][] columns = {{"Test", "Test", "Test", "Test", "Test"}, {"Test", "Test", "Test", "Test", "Test"}};
	
	public Summary (Container parent)
	{
		table = new JTable(columns, header);
		table.setEnabled(false);
		table.setBorder(BorderFactory.createEtchedBorder());
		
		this.setLayout(new BorderLayout());
		this.add(table, BorderLayout.CENTER);
		this.setSize(400,400);
		this.setVisible(true);
		parent.add(this);
	}
	
	public void addFigure (String name, String value)
	{
		
	}
}