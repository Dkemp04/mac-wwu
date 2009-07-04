package Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PictureCanvas extends JFrame
{
	private static final long serialVersionUID = 22319080529835277L;
	Pictures canvas = new Pictures ();
	
	public PictureCanvas ()
	{
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(canvas, BorderLayout.CENTER);
		
		this.setSize(445,620);
		this.setResizable(false);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setTitle("Deutschland");
		//addWindowListener(new MyWindowListener());
	}
	
	public class MyWindowListener extends WindowAdapter
	{
		public void windowClosing (WindowEvent e)
		{
			System.exit(0);
		}
		public void windowActivated (WindowEvent e)
		{
			final File f = new File ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Karte_Deutschland.jpg");
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
	
	public class MyComponentAdapter extends ComponentAdapter
	{
		public void componentResized(ComponentEvent e)
		{
			final File f = new File ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Karte_Deutschland.jpg");
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
	
	public class Pictures extends Canvas
	{
		private static final long serialVersionUID = 6327575382472292234L;
		private BufferedImage image;
		
		public Pictures ()
		{
			addComponentListener(new MyComponentAdapter());
			this.setVisible(true);
		}
		
		public void setImage(BufferedImage image) 
		{ 
		    this.image = image; 
		    setPreferredSize(new Dimension(image.getWidth(), image.getHeight())); 
		    repaint(); 
		    invalidate();
		}
		  
		public void paint (Graphics g)
		{
			this.setBackground(Color.blue);
		    if ( image != null )
		    	g.drawImage( image, 0, 0, this ); 
		}
	}
	
	public static void main (String[] args)
	{
		new PictureCanvas();
	}
}