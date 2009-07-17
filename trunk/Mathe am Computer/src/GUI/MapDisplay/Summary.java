package GUI.MapDisplay;

import java.awt.*;
import javax.swing.*;

public class Summary extends JComponent
{
	private static final long serialVersionUID = -5804582430610857571L;
	
	private JTextArea names;
	private JTextArea figures;
	private JTextArea[] methods;
	
	public Summary (Container parent)
	{
		names = new JTextArea();
		names.setEditable(false);
		names.setEnabled(false);
		names.setDisabledTextColor(Color.BLACK);
		names.setFont(new Font("Namen", 10, Font.BOLD));
		
		figures = new JTextArea();
		figures.setEditable(false);
		figures.setEnabled(false);
		figures.setDisabledTextColor(Color.BLACK);
		figures.setFont(new Font("Werte", 10, Font.BOLD));
		
		this.setLayout(new GridLayout(1,2));
		this.add(names);
		this.add(figures);
		this.setSize(400,400);
		this.setVisible(true);
		parent.add(this);
	}
	
	public void addFigure (String name, String value, String method)
	{
		for (int i = 0; i< this.methods.length; i++)
		{
			
		}
		names.append(name);
		figures.append(value);
	}
	
	public void setMethods (String[] methods)
	{
		for (int i = 0; i < methods.length; i++)
			this.methods[i] = new JTextArea(methods[i]);
	}
}