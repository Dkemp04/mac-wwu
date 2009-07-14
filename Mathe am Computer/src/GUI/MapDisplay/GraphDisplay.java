package GUI.MapDisplay;
import java.awt.*;
import java.util.*;

import javax.swing.JPanel;

import Logic.History;

public class GraphDisplay extends JPanel
{
	private static final long serialVersionUID = -6614648723556742214L;
	
	private Image background;
	
	private History firstHistory;
	private History history;
	private History lastHistory;
	
	private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
	
	public GraphDisplay(String picture, Graph original, History hist)
	{
		ellipses = original.getEllipses();
		background = Toolkit.getDefaultToolkit().getImage(picture);
		setSize(background.getWidth(this), background.getHeight(this));
		
		firstHistory = hist;
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
    	
    	History hist = firstHistory;
    	if(hist != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
			while (hist != history)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
			if (hist != null)
				hist = hist.getNext();
    	}
	}
	
	public void stepForward(Graphics g)
	{
		if(history != null)
    	{
			if( history.getLineEnd() != null &&  history.getLineStart() != null)
				g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
    		if (history != null)
	    	{
	    		history = history.getNext();
	    		if( history != null && history.getLineEnd() != null &&  history.getLineStart() != null)
	    		{
	    			g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());	
	    		}
	    	}
    	}
	}
	
	public void stepToEnd (Graphics g)
	{
		if(history != null)
    	{
			if( history.getLineEnd() != null &&  history.getLineStart() != null)
				g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
			while (history != null)
	    	{
	    		history = history.getNext();
	    		if( history != null && history.getLineEnd() != null &&  history.getLineStart() != null)
	    		{
	    			g.drawLine(Double.valueOf(history.getLineStart().getX()).intValue(), Double.valueOf(history.getLineStart().getY()).intValue(), Double.valueOf(history.getLineEnd().getX()).intValue(), Double.valueOf(history.getLineEnd().getY()).intValue());
	    		}
	    	}
    	}
	}
	
	public void stepBack (Graphics g)
	{
		//g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		if (history != null || history.getPrev() != null)
			history = history.getPrev();
		History hist = history;
    	if(hist != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
			while (hist != history)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
			if (hist != null)
				hist = hist.getNext();
    	}
	}
	
	/*public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g2D = (Graphics2D) g;
		g.drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
    	for(SingleEllipse singleEllipse : ellipses)
    		singleEllipse.draw((Graphics2D) g);
    	
    	History hist = firstHistory;
    	if(history != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	while(hist != history)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			g.drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
    	}
	}*/
}