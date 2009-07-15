package GUI.MapDisplay;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Logic.History;

public class StaticGraph extends JComponent implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private LinkedList<String> steps = new LinkedList<String>();
	
	private GraphDisplay display;
	private Graph original;

	private History firstHistory;
	private History cursor;
	private History lastHistory;
	private int stepNr;
	
	private JPanel buttons;
	private JPanel right;
	
	private JButton start;
	private JButton back;
	private JButton forward;
	private JButton end;
	private JTextArea description;
	private JScrollPane scrollbar;
	
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
		
		this.original = original;
		
		this.stepNr = 1;
		this.cursor = history;
		this.firstHistory = history;
		this.lastHistory = history;
		
		while(lastHistory != null)
			lastHistory = lastHistory.getNext();
		
		int j = 0;
		while (cursor != null)
		{
			this.setStep(j, cursor.toString());
			cursor = cursor.getNext();
			j++;
		}
		cursor = firstHistory;
		
		String stepDescription = "";
		for (int i = 0; i < steps.size(); i++)
			stepDescription += (i + 1) + ". Schritt: " + steps.get(i) + "\n";
		
		description = new JTextArea(/*stepDescription*/);
		description.setBorder(BorderFactory.createTitledBorder("Beschreibung"));
		description.setEnabled(false);
		description.setForeground(Color.BLACK);
		description.setSize(original.getWidth() - 20, original.getHeight());
		description.setEditable(false);
		description.setFont(new Font("Beschreibung", Font.BOLD, 10));
		description.setDisabledTextColor(Color.black);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setAutoscrolls(true);
		
		scrollbar = new JScrollPane(description, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollbar.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		
		
		this.setLayout(new GridLayout(1,2));
		
		display = new GraphDisplay(original.getImagePath());
		
		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,4));
		buttons.add(start);
		buttons.add(back);
		buttons.add(forward);
		buttons.add(end);
		
		scrollbar.setViewportView(description);
		right = new JPanel(new BorderLayout());
		right.add(buttons, BorderLayout.NORTH);
		right.add(scrollbar, BorderLayout.CENTER);
		
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
		if (e.getActionCommand().equals("Start"))
		{
			display.stepToStart(this.getGraphics());
			description.setText("");
			stepNr = 1;
		}
			
		if (e.getActionCommand().equals("Schritt zurück"))
		{
			display.stepBack(this.getGraphics());
			description.setText("");
			stepNr = 1;
			
			History hist = firstHistory;
			while (hist != cursor)
			{
				description.append(stepNr + ". Schritt: " + hist.toString() + "\n");
				stepNr++;
				hist = hist.getNext();
			}
		}
			
		if (e.getActionCommand().equals("Schritt vor"))
		{
			display.stepForward(this.getGraphics());
			if (cursor != null)
			{
				description.append(stepNr + ". Schritt: " + cursor.toString() + "\n");
				stepNr++;
			}
		}
		if (e.getActionCommand().equals("Ende"))
		{
			display.stepToEnd(this.getGraphics());
			
			History hist = cursor;
			while (hist != null)
			{
				description.append(stepNr + ". Schritt: " + hist.toString() + "\n");
				stepNr++;
				hist = hist.getNext();
			}
		}
	}
	
	/**
	 * 
	 * @author Daniel Kemper
	 *
	 */
	public class GraphDisplay extends JPanel
	{
		private static final long serialVersionUID = -6614648723556742214L;
		
		private Image background;
		private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
		
		public GraphDisplay(String picture)
		{
			ellipses = original.getEllipses();
			background = Toolkit.getDefaultToolkit().getImage(picture);
			setSize(background.getWidth(this), background.getHeight(this));
		}
		
		public void paint(Graphics g)
		{
			super.paint(g);
			g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
	    	for(SingleEllipse singleEllipse : ellipses)
	    		singleEllipse.draw((Graphics2D) g);
	    	
	    	History hist = firstHistory;
	    	if(hist != null)
	    	{
				if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
					g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
				while (hist != cursor)
		    	{
		    		hist = hist.getNext();
		    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
		    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
		    	}
				if (hist != null)
					hist = hist.getNext();
	    	}
		}
		
		public void stepBack (Graphics g)
		{
			g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
	    	for(SingleEllipse singleEllipse : ellipses)
	    		singleEllipse.draw((Graphics2D) g);
	    	
			if (cursor != null && cursor.getPrev() != null)
				cursor = cursor.getPrev();
			History hist = firstHistory;
	    	if(hist != null)
	    	{
				if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
					g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
				while (hist != cursor)
		    	{
		    		hist = hist.getNext();
		    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
		    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
		    	}
				if (hist != null)
					hist = hist.getNext();
	    	}
		}
		
		public void stepToStart (Graphics g)
		{
			g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
	    	for(SingleEllipse singleEllipse : ellipses)
	    		singleEllipse.draw((Graphics2D) g);
	    	
			if (cursor != null && cursor.getPrev() != null)
				cursor = firstHistory;
		}
		
		public void stepForward(Graphics g)
		{
			if(cursor != null)
	    	{
				if( cursor.getLineEnd() != null &&  cursor.getLineStart() != null)
					g.drawLine(Double.valueOf(cursor.getLineStart().getX()).intValue(), Double.valueOf(cursor.getLineStart().getY()).intValue(), Double.valueOf(cursor.getLineEnd().getX()).intValue(), Double.valueOf(cursor.getLineEnd().getY()).intValue());
	    		if (cursor != null)
		    	{
	    			cursor = cursor.getNext();
		    		if( cursor != null && cursor.getLineEnd() != null &&  cursor.getLineStart() != null)
		    		{
		    			g.drawLine(Double.valueOf(cursor.getLineStart().getX()).intValue(), Double.valueOf(cursor.getLineStart().getY()).intValue(), Double.valueOf(cursor.getLineEnd().getX()).intValue(), Double.valueOf(cursor.getLineEnd().getY()).intValue());	
		    		}
		    	}
	    	}
		}
		
		public void stepToEnd (Graphics g)
		{
			if(cursor != null)
	    	{
				if( cursor.getLineEnd() != null &&  cursor.getLineStart() != null)
					g.drawLine(Double.valueOf(cursor.getLineStart().getX()).intValue(), Double.valueOf(cursor.getLineStart().getY()).intValue(), Double.valueOf(cursor.getLineEnd().getX()).intValue(), Double.valueOf(cursor.getLineEnd().getY()).intValue());
				while (cursor != null)
		    	{
					cursor = cursor.getNext();
		    		if( cursor != null && cursor.getLineEnd() != null &&  cursor.getLineStart() != null)
		    		{
		    			g.drawLine(Double.valueOf(cursor.getLineStart().getX()).intValue(), Double.valueOf(cursor.getLineStart().getY()).intValue(), Double.valueOf(cursor.getLineEnd().getX()).intValue(), Double.valueOf(cursor.getLineEnd().getY()).intValue());
		    		}
		    	}
	    	}
		}
	}
}