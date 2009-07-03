package GUI;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

class Graph extends JPanel
{
	private static final long serialVersionUID = -837672945706307976L;
	
	PaintingArea p_area = new PaintingArea();
	Ellipse2D ellipse;
	int x, y;
	
	public Graph ()
	{
		setBackground (Color.WHITE);
	    setSize (300,300);
	    add(p_area);
	}
	
	class PaintingArea extends Canvas
	{
		private static final long serialVersionUID = 9080164932164761547L;

		public void paint (Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
		    int Height;
		    ellipse = new Ellipse2D.Double(75,75,20,20);
		    g2.draw(ellipse);

		    g2 = (Graphics2D) g;
		    Height = getHeight();
		    g2.drawString ("The CustomCanvas is in the CENTER area", 10, Height/2);
		    g2.drawRoundRect(10, 10, 10, 10, 5, 5);
		    g2.draw3DRect(30, 30, 10, 10, false);
		    g2.drawOval(100, 100, 20, 20);
		    g2.drawOval(200, 200, 20, 20);
		    g2.drawLine(117, 117, 203, 203);
		}
	}
	
	class MyMouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent m)
		{
			new Ellipse2D.Double(m.getX(), m.getY(), 1, 1);
		}
		
		public void mousePressed(MouseEvent m)
		{
			if(ellipse.contains(m.getX(), m.getY(), ellipse.getWidth(), ellipse.getWidth()))
			{
				int x = m.getX();
				int y = m.getY();
				ellipse = new Ellipse2D.Double(x, y, ellipse.getWidth(), ellipse.getHeight());
			}
		}
	}
		
	class MyMouseMotionListener extends MouseMotionAdapter
	{
			
	}
}