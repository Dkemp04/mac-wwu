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
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläche
		super();
	    canvas = new DrawingCanvas();
	    
	    //Einstellung des Frames und dessen Komponenten (Zeichenfläche und Koordinaten-Anzeige)
	    this.setLayout(new BorderLayout());
	    location = new JLabel("x,y: ");
	    this.add(location, BorderLayout.SOUTH);
	    this.add(canvas, BorderLayout.CENTER);
	    setSize(500,500);
	}
	
	//Anzeige der Koordinaten
	private void displayCoordinates()
    {
	    double x = canvas.selectedEllipse.getX();
	    double y = canvas.selectedEllipse.getY();
	    String coords = "x,y: (" + Double.toString(x) + ","+ Double.toString(y) + ")";
	    location.setText(coords);
    }
	
	public int getEllipseCount ()
	{
		return canvas.ellipses.size();
	}
	
	public LinkedList<Graph.DrawingCanvas.Ellipse> getEllipses ()
	{
		return canvas.ellipses;
	}
	
	public class DrawingCanvas extends Canvas
    {
    	//Deklarierung der serialVersionUID für die serialisierbare Klasse DrawingCanvas
		private static final long serialVersionUID = -7972492610172541422L;
		
    	int x1, y1, x2, y2;
    	LinkedList<Ellipse> ellipses = new LinkedList<Ellipse>();
    	Ellipse selectedEllipse;
    
    	public class Ellipse
    	{
    		private Ellipse2D ellipse;
    		private double x, y, w, h;
    		
	    	public Ellipse(double x, double y)
	    	{
	    		this.x = x;
	    		this.y = y;
	    		this.w = 10;
	    		this.h = 10;
	    		ellipse = new Ellipse2D.Double(x,y,w,h);
	        }
	    	
	    	public void draw(Graphics2D g2D)
	    	{
	    		g2D.draw(ellipse);
	    	}
	    	
	    	public void updateData(double x, double y)
	    	{
	    		this.x = x;
	    		this.y = y;
	    		ellipse = new Ellipse2D.Double(x,y,w,h);
	        }
	    	
	    	public Ellipse select(MouseEvent e)
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
	    	setBackground(Color.LIGHT_GRAY);
	    	addMouseListener(new MyMouseListener());
	    	addMouseMotionListener(new MyMouseMotionListener());
	    }
	    
	    public void paint(Graphics g)
	    {
	    	Graphics2D g2D = (Graphics2D) g;
	    	g2D.drawImage(getToolkit().getImage("Karte_Deutschland.jpg"),100,100,this);
	    	for(Ellipse ellipse : ellipses)
	    		ellipse.draw(g2D);
	    	if (curCursor != null)
	    		setCursor(curCursor);
	    }

	    private class MyMouseListener extends MouseAdapter
	    {
	    	long timer = 0;
	    	
	    	public void mousePressed(MouseEvent e)
	    	{
	    		Iterator<Ellipse> itr = ellipses.iterator();  
	    		Ellipse ellipse = null;
		      	while(itr.hasNext() && ellipse == null)
		      		ellipse = itr.next().select(e);
		    	if (ellipse != null)
		    	{
		    		selectedEllipse = ellipse;
			        displayCoordinates();
			      	curCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		    	}
		    	else
		          location.setText("x,y: ");
		        x1 = e.getX();
		        y1 = e.getY();
		        timer = System.currentTimeMillis();
		        canvas.repaint();
		    }
	    	
		    public void mouseReleased(MouseEvent e)
		    {
		    	if(selectedEllipse == null && System.currentTimeMillis()-timer <= 500)
		    		ellipses.add(new Ellipse(e.getX(), e.getY()));
		        curCursor = Cursor.getDefaultCursor();
		        selectedEllipse = null;
		        location.setText("x,y: ");
		        canvas.repaint();
		    }
	    }
	    
	    private class MyMouseMotionListener extends MouseMotionAdapter
	    {
	    	public void mouseDragged(MouseEvent e)
	    	{
		        if (selectedEllipse != null)
		        {
			        x2 = e.getX();
			        y2 = e.getY();
			        selectedEllipse.updateData(selectedEllipse.getX() + x2 - x1,selectedEllipse.getY() + y2 - y1);
			        x1 = x2;
			        y1 = y2;
			        canvas.repaint();
		        }
		        if (selectedEllipse != null)
		        	displayCoordinates();
	    	}
	    }
    }
}