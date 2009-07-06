package GUI;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class SingleEllipse
{
	private Ellipse2D singleEllipse;
	private double x, y, w, h;
	
	public SingleEllipse(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.w = 10;
		this.h = 10;
		singleEllipse = new Ellipse2D.Double(x,y,w,h);
    }
	
	public void draw(Graphics2D g2D)
	{
		g2D.draw(singleEllipse);
	}
	
	public void updateData(double x, double y)
	{
		this.x = x;
		this.y = y;
		singleEllipse = new Ellipse2D.Double(x,y,w,h);
    }
	
	public SingleEllipse select(MouseEvent e)
	{
		if (singleEllipse.contains(e.getX(), e.getY()))
			return this;
		return null;
	}
	
	public Ellipse2D getEllipse(){return singleEllipse;}
	public double getX(){return x;}
	public double getY(){return y;}
	public double getHeight(){return h;}
	public double getWidth(){return w;}
}