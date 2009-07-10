package GUI;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.*;

import javax.swing.JPanel;

public class GraphDisplay extends JPanel
{
	private static final long serialVersionUID = -6614648723556742214L;
	private Image background;
	private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
	private LinkedList<Line2D> edges = new LinkedList<Line2D>();
	
	public GraphDisplay(String picture, Graph original)
	{
		ellipses = original.getEllipses();
		background = Toolkit.getDefaultToolkit().getImage(picture);
		setSize(background.getWidth(this), background.getHeight(this));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		((Graphics2D)g).drawImage(background, 0, 0, background.getWidth(this), background.getHeight(this), this);
    	for(SingleEllipse singleEllipse : ellipses)
    		singleEllipse.draw((Graphics2D) g);
    	for (Line2D line: edges)
    		((Graphics2D) g).draw(line);
	}
}