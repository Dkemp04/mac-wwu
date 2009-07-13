package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import Logic.History;

public class StaticGraph extends JComponent implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private LinkedList<String> steps = new LinkedList<String>();
	
	private GraphDisplay display;
	
	private JPanel right;
	private JButton back;
	private JButton forward;
	private JButton forwardToEnd;
	private JLabel description;
	
	public StaticGraph(Container parent, Graph original, History history)
	{
		back = new JButton("Schritt zurück");
		back.addActionListener(this);
		forward = new JButton("Schritt vor");
		forward.addActionListener(this);
		forwardToEnd = new JButton("Ende");
		forwardToEnd.addActionListener(this);
		
		String stepDescription = "";
		for (int i = 0; i < steps.size(); i++)
			stepDescription += (i + 1) + ". Schritt: " + steps.get(i) + "<br>";
		description = new JLabel("<html><b>Beschreibung</b><br>" + stepDescription + "</html>");
		description.setHorizontalAlignment(JLabel.LEFT);
		description.setVerticalAlignment(JLabel.TOP);
		description.setBorder(BorderFactory.createEtchedBorder());
		
		this.setLayout(new GridLayout(1,2));
		
		display = new GraphDisplay(original.getImagePath(), original, history);

		description.setSize(display.getHeight()-back.getHeight()-forward.getHeight(), display.getWidth() * 2);
		right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(back);
		right.add(forward);
		right.add(forwardToEnd);
		right.add(description);
		
		this.add(display);
		this.add(right);
		parent.add(this);
	}
	
	public void setStep (int stepnr, String description)
	{
		steps.add(stepnr, description);
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getActionCommand().equals("Schritt zurück"))
			display.stepBack(this.getGraphics());
		if (e.getActionCommand().equals("Schritt vor"))
			display.stepForward(this.getGraphics());
		if (e.getActionCommand().equals("Ende"))
			display.stepToEnd(this.getGraphics());
	}
}