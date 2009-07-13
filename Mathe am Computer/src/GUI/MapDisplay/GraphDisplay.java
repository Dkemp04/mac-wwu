package GUI.MapDisplay;
import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

import Logic.History;

public class GraphDisplay extends JPanel
{
	private static final long serialVersionUID = -6614648723556742214L;
	
	private Image background;
	
	private History history;
	private History lastHistory;
	
	private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
	
	public GraphDisplay(String picture, Graph original, History hist)
	{
		ellipses = original.getEllipses();
		background = Toolkit.getDefaultToolkit().getImage(picture);
		setSize(background.getWidth(this), background.getHeight(this));
		
		history = hist;
		lastHistory = hist;
		while(lastHistory != null)
			lastHistory = lastHistory.getNext();
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
    	for(SingleEllipse singleEllipse : ellipses)
    		singleEllipse.draw((Graphics2D) g);
	}
	
	/*public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2D = (Graphics2D) g;
		g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
    	for(SingleEllipse singleEllipse : ellipses)
    		singleEllipse.draw((Graphics2D) g);
    	
    	History hist = history;
    	if(history != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	while(hist != lastHistory)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
    	}
	}*/
	
	public void stepBack (Graphics g)
	{
		/*if(history != null)
    	{
			if( history.getLineEnd() != null &&  history.getLineStart() != null)
				g2D.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
			history = history.getNext();
    		if(history != lastHistory)
	    	{
	    		if( history != null && history.getLineEnd() != null &&  history.getLineStart() != null)
    			g2D.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
	    	}
    	}*/
	}
	
	public void stepForward(Graphics g)
	{
		if(history != null)
    	{
			if( history.getLineEnd() != null &&  history.getLineStart() != null)
				g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
			history = history.getNext();
    		if(history != lastHistory)
	    	{
	    		if( history != null && history.getLineEnd() != null &&  history.getLineStart() != null)
    			g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
	    	}
    	}
	}
	
	public void stepToEnd (Graphics g)
	{
		History hist = history;
    	if(history != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	while(hist != lastHistory)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
    	}
	}
}