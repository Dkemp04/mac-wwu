package GUI.MapDisplay;
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
	private History cursor;
	private History lastHistory;
	
	private JPanel buttons;
	private JPanel right;
	private JButton start;
	private JButton back;
	private JButton forward;
	private JButton end;
	private JLabel description;
	
	public StaticGraph(Container parent, Graph original, History history)
	{
		start = new JButton("Start");
		start.addActionListener(this);
		back = new JButton("Schritt zurück");
		back.addActionListener(this);
		forward = new JButton("Schritt vor");
		forward.addActionListener(this);
		end = new JButton("Ende");
		end.addActionListener(this);
		
		lastHistory = history;
		while(lastHistory != null)
			lastHistory = lastHistory.getNext();
		
		this.cursor = history;
		int j = 0;
		while (cursor != null)
		{
			this.setStep(j, cursor.toString());
			cursor = cursor.getNext();
			j++;
		}
		
		String stepDescription = "";
		for (int i = 0; i < steps.size(); i++)
			stepDescription += (i + 1) + ". Schritt: " + steps.get(i) + "<br>";
		description = new JLabel("<html><b>Beschreibung</b><br>" + stepDescription + "</html>");
		description.setHorizontalAlignment(JLabel.LEFT);
		description.setVerticalAlignment(JLabel.TOP);
		description.setBorder(BorderFactory.createEtchedBorder());
		
		this.setLayout(new GridLayout(1,2));
		
		display = new GraphDisplay(original.getImagePath(), original, history);
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,4));
		buttons.add(start);
		buttons.add(back);
		buttons.add(forward);
		buttons.add(end);
		
		//description.setSize(display.getHeight()-back.getHeight()-forward.getHeight(), display.getWidth() * 2);
		right = new JPanel();
		//right.setLayout(new BoxLayout(right, BoxLayout.LINE_AXIS));
		right.add(buttons);
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