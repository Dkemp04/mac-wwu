package GUI;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Graph extends JPanel
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private DrawingCanvas canvas;
	private JLabel location;
	private Cursor curCursor;

	public Graph(JFrame parent)
	{
		super();
	    canvas = new DrawingCanvas();
	    
	    this.setLayout(new GridLayout(1, 2));
	    location = new JLabel("");
	    this.add(new Label("x,y: "+location.getText(), JLabel.LEFT), BorderLayout.SOUTH);
	    this.add(canvas, BorderLayout.CENTER);
	    setSize(600,300);
	    setVisible(true);
	}
	
	private void displayParameters()
    {
	    double x = canvas.selectedShape.getX();
	    double y = canvas.selectedShape.getY();
	    String locString = "(" + Double.toString(x) + ","+ Double.toString(y) + ")";
	    location.setText(locString);
    }
	
	private class DrawingCanvas extends Canvas
    {
    	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
		private static final long serialVersionUID = -7972492610172541422L;
		
    	int x1, y1, x2, y2;
    	LinkedList<SingleEllipse> ellipse = new LinkedList<SingleEllipse>();
    	SingleEllipse selectedShape;
    
    	private class SingleEllipse
    	{
    		private Ellipse2D ellipse;
    		private double x, y, w, h;
	    	public void draw(Graphics2D g2D)
	    	{
	    		g2D.draw(ellipse);
	    	}
	    	
	    	public SingleEllipse(double x, double y, double w, double h)
	    	{
	    		this.x = x;
	    		this.y = y;
	    		this.w = w;
	    		this.h = h;
	    		ellipse = new Ellipse2D.Double(x,y,w,h);
	        }
	    	public void updateData(double x, double y)
	    	{
	    		this.x = x;
	    		this.y = y;
	    		ellipse = new Ellipse2D.Double(x,y,w,h);
	        }
	    	
	    	public SingleEllipse select(MouseEvent e)
	    	{
	    		if (ellipse.contains(e.getX(), e.getY()))
	    			return this;
	    		return null;
	    	}
	    	
	    	public Ellipse2D getEllipse(){return ellipse;}
	    	public double getX(){return x;}
	    	public double getY(){return y;}
	    	public double getHeight(){return h;}
	    	public double getWidth(){return w;}
    	}
    
	    
	    public DrawingCanvas()
	    {
	    	ellipse.add(new SingleEllipse(20, 20, 100, 75));
	    	ellipse.add(new SingleEllipse(80, 60, 100, 75));
	    	setBackground(Color.LIGHT_GRAY);
	    	addMouseListener(new MyMouseListener());
	    	addMouseMotionListener(new MyMouseMotionListener());
	    }
	    
	    public void paint(Graphics g)
	    {
	    	Graphics2D g2D = (Graphics2D) g;
	    	for(SingleEllipse singleEllipse : ellipse)
	    		singleEllipse.draw(g2D);
	    	if (curCursor != null)
	    		setCursor(curCursor);
	    }

	    private class MyMouseListener extends MouseAdapter
	    {
	    	long timer = 0;
	    	
	    	public void mousePressed(MouseEvent e)
	    	{
	    		Iterator<SingleEllipse> itr = ellipse.iterator();  
		      	SingleEllipse singleEllipse = null;
		      	while(itr.hasNext() && singleEllipse == null)
		      	{
		    	  	singleEllipse = itr.next().select(e);
		      	}
		    	if (singleEllipse != null)
		    	{
			        selectedShape = singleEllipse;
			        displayParameters();
			      	curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		    	}
		    	else
		    	{ 
		          location.setText("");
		        }
		        x1 = e.getX();
		        y1 = e.getY();
		        timer = System.currentTimeMillis();
		    }
	    	
		    public void mouseReleased(MouseEvent e)
		    {
		
		    	if(selectedShape == null && System.currentTimeMillis()-timer <= 500)
		      	{
		        	ellipse.add(new SingleEllipse(e.getX(), e.getY(), 20, 20));
		        }
		        curCursor = Cursor.getDefaultCursor();
		    	selectedShape = null;
		        location.setText("");
		        canvas.repaint();
		    }
	    }
	    
	    private class MyMouseMotionListener extends MouseMotionAdapter
	    {
	    	public void mouseDragged(MouseEvent e)
	    	{
		        if (selectedShape != null)
		        {
			        x2 = e.getX();
			        y2 = e.getY();
			        selectedShape.updateData(selectedShape.getX() + x2 - x1,selectedShape.getY() + y2 - y1);
			        x1 = x2;
			        y1 = y2;
			        canvas.repaint();
		        }
		        if (selectedShape != null)
		          displayParameters();
	    	}
	    }
    } 
}