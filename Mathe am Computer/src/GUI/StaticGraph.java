package GUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class StaticGraph extends JPanel
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private Cursor curCursor;
	private LinkedList<SingleEllipse> ellipses = new LinkedList<SingleEllipse>();
	StaticCanvas canvas;
	
	public StaticGraph(Container parent, Graph original)
	{
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläche
		super();
		
		ellipses = original.getEllipses();
		
		canvas = new StaticCanvas();
		this.add(canvas);
		parent.add(this);
		
	    //Einstellung des Frames und dessen Komponenten (Zeichenfläche und Koordinaten-Anzeige)
	    this.setSize(400,400);
    	this.addComponentListener(new MyComponentAdapter());
	    this.setVisible(true);
	}
	
	public Canvas getCanvas ()
	{		return this.canvas;}
	
	public class StaticCanvas extends Canvas
	{
		private BufferedImage background;
		
		public void paint(Graphics g)
	    {
	    	Graphics2D g2D = (Graphics2D) background.createGraphics();
	    	g2D.setColor(Color.black);
	    	for(SingleEllipse singleEllipse : ellipses)
	    		singleEllipse.draw((Graphics2D) g2D);
	    	if (curCursor != null)
	    		setCursor(curCursor);
	    	if (background != null)
				((Graphics) this.getGraphics()).drawImage(background, 0, 0, this);
	    }
	    
	    public void setImage(BufferedImage image) 
		{ 
		    this.background = image;
		    this.setPreferredSize(new Dimension(background.getWidth(), background.getHeight())); 
		    repaint(); 
		    invalidate();
		}
	}
	public class MyComponentAdapter extends ComponentAdapter
	{
		public void componentShown(ComponentEvent e)
		{
			final File f = new File ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Deutschland.jpg");
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