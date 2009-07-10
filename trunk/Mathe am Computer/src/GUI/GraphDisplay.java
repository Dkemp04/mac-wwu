package GUI;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

import javax.swing.JPanel;

import Logik.History;

public class GraphDisplay extends JPanel
{
	private static final long serialVersionUID = -6614648723556742214L;
	private Image background;
	History history;
	History lastHistory;
	private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
	private LinkedList<Line2D> edges = new LinkedList<Line2D>();
	
	public GraphDisplay(String picture, Graph original)
	{
		ellipses = original.getEllipses();
		background = Toolkit.getDefaultToolkit().getImage(picture);
		setSize(background.getWidth(this), background.getHeight(this));
	}
	
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
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		((Graphics2D)g).drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
    	for(SingleEllipse singleEllipse : ellipses)
    		singleEllipse.draw((Graphics2D) g);
    	History hist = history;
    	if(history != null)
    	{
			if( hist.getLineEnd() != null &&  hist.getLineStart() != null)
				((Graphics2D) g).drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	while(hist != lastHistory)
	    	{
	    		hist = hist.getNext();
	    		if( hist != null && hist.getLineEnd() != null &&  hist.getLineStart() != null)
	    			((Graphics2D) g).drawLine(Double.valueOf(hist.getLineStart().getX()).intValue(), Double.valueOf(hist.getLineStart().getY()).intValue(), Double.valueOf(hist.getLineEnd().getX()).intValue(), Double.valueOf(hist.getLineEnd().getY()).intValue());
	    	}
    	}
	}
}