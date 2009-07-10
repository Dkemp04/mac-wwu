package GUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Graph extends JPanel
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private DrawingCanvas canvas;
	private JLabel location;
	private Cursor curCursor;
	
	public Graph()
	{
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläche
		super();
	    canvas = new DrawingCanvas();
	    
	    final File f = new File ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Polen.jpg");
		if (f != null)
		{
			new SwingWorker<BufferedImage, Void>()
			{
				protected BufferedImage doInBackground() throws IOException
				{
					return ImageIO.read(f);
				}
				protected void done()
				{
					try
					{
						canvas.setImage(get());
					}
					catch (Exception e)
					{
						System.err.println();
					}
				}
			}.execute();
		}
	    
	    //Einstellung des Frames und dessen Komponenten (Zeichenfläche und Koordinaten-Anzeige)
	    this.setLayout(new BorderLayout());
	    location = new JLabel("x,y: ");
	    this.add(location, BorderLayout.SOUTH);
	    this.add(canvas, BorderLayout.CENTER);
	    this.setSize(canvas.getWidth(),canvas.getHeight());
	    this.setVisible(true);
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
	
	public LinkedList<SingleEllipse> getEllipses ()
	{
		return canvas.ellipses;
	}
	
	public void addEllipse (double x, double y)
	{
		this.canvas.ellipses.add(new SingleEllipse(x,y));
	}
	
	public DrawingCanvas getCanvas ()
	{
		return this.canvas;
	}
	
	public class DrawingCanvas extends Canvas
    {
    	//Deklarierung der serialVersionUID für die serialisierbare Klasse DrawingCanvas
		private static final long serialVersionUID = -7972492610172541422L;
		
    	double x1, y1, x2, y2;
    	LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
    	SingleEllipse selectedEllipse;
    	transient BufferedImage background;
    	//Graphics g2D;
    
	    public DrawingCanvas()
	    {
	    	addComponentListener(new MyComponentAdapter());
	    	this.setVisible(true);
	    	addMouseListener(new MyMouseListener());
	    	addMouseMotionListener(new MyMouseMotionListener());
	    }
	    
	    public void paint(Graphics g)
	    {
	    	Graphics g2D = (Graphics2D) background.createGraphics();
	    	g2D.setColor(Color.black);
	    	for(SingleEllipse singleEllipse : ellipses)
	    		singleEllipse.draw((Graphics2D) g2D);
	    	if (curCursor != null)
	    		setCursor(curCursor);
	    	if (this != null)
				((Graphics) this.getGraphics()).drawImage(background, 0, 0, this);
	    	this.setSize(background.getWidth(), background.getHeight());
	    }
	    
	    public void setImage(BufferedImage image) 
		{ 
		    this.background = image;
		    setPreferredSize(new Dimension(background.getWidth(), background.getHeight())); 
		    repaint(); 
		    invalidate();
		}
	    
	    public BufferedImage getBackgroundImage ()
	    {
	    	return this.background;
	    }

	    private class MyMouseListener extends MouseAdapter
	    {
	    	long timer = 0;
	    	int delPos = -1;
			SingleEllipse delEllipse = null;
			
	    	public void mousePressed(MouseEvent e)
	    	{
	    		if(e.getButton() == 3)
	    		{
	    			for (int i = 0; i < ellipses.size(); i++)
	    			{
	    				delEllipse = ellipses.get(i);
	    				if (delEllipse.select(e) != null)
	    				{
	    					delPos = i;
	    					break;
	    				}
	    			}
	    			if (delPos >= 0 && delEllipse != null && delPos <= ellipses.size()-1)
	    			{
	    				canvas.ellipses.remove(delPos);
						canvas.setSize(canvas.getWidth() + 1, canvas.getHeight() + 1);
						canvas.repaint();
						delPos = -1;
						delEllipse = null;
	    			}
	    		}
	    		else if (e.getButton() == 1)
	    		{
	    			Iterator<SingleEllipse> itr = ellipses.iterator();
		    		SingleEllipse ellipse = null;
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
			    	canvas.setSize(canvas.getWidth() - 1, canvas.getHeight() - 1);
			        x1 = e.getX();
			        y1 = e.getY();
			        timer = System.currentTimeMillis();
			        canvas.repaint();
	    		}
		    }
	    	
	    	public void mouseReleased(MouseEvent e)
		    {
	    		if(e.getButton() == 3)
	    		{
	    			if (delPos >=0 && delEllipse != null && delPos <= ellipses.size()-1)
	    			{
						canvas.setSize(canvas.getWidth() - 1, canvas.getHeight() - 1);
						canvas.repaint();
						delPos = -1;
						delEllipse = null;
	    			}
	    		}
	    		else if(e.getButton() == 1 && selectedEllipse == null && System.currentTimeMillis()-timer <= 500)
		    	{
		    		SingleEllipse newEllipse = new SingleEllipse(e.getX(), e.getY());
		    		ellipses.add(newEllipse);
		    	}
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
		        canvas.setSize(canvas.getWidth() + 1, canvas.getHeight() + 1);
	    	}
	    }
	    
	    
    }
	
	public class MyComponentAdapter extends ComponentAdapter
	{
		public void componentResized(ComponentEvent e)
		{
			final File f = new File ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Polen.jpg");
			if (f != null)
			{
				new SwingWorker<BufferedImage, Void>()
				{
					protected BufferedImage doInBackground() throws IOException
					{
						return ImageIO.read(f);
					}
					protected void done()
					{
						try
						{
							canvas.setImage(get());
						}
						catch (Exception e)
						{
							System.err.println();
						}
					}
				}.execute();
			}
		}
	}
}