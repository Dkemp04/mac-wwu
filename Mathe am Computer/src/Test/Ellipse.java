package Test;

import GUI.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Ellipse extends JFrame {
  DrawingCanvas canvas;

  JLabel location;

  public Ellipse() {
    super();
    Container container = getContentPane();

    canvas = new DrawingCanvas();
    container.add(canvas);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1, 2));
    panel.add(new JLabel("x,y: ", JLabel.RIGHT));
    location = new JLabel("");
    panel.add(location);

    container.add(panel, BorderLayout.SOUTH);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    setSize(600,300);
    setVisible(true);
  }

    public static void main(String arg[]) {
    new Ellipse();
  }

  class DrawingCanvas extends Canvas {
	int x1, y1, x2, y2;

    LinkedList<SingleEllipse> ellipse = new LinkedList<SingleEllipse>();
    
    
    SingleEllipse selectedShape;
    
    private class SingleEllipse{
    	private Ellipse2D ellipse;
    	private double x, y, w, h;
    	public void draw(Graphics2D g2D){
    		g2D.draw(ellipse);
    	}
    	
    	public SingleEllipse(double x, double y, double w, double h){
    		this.x = x;
    		this.y = y;
    		this.w = w;
    		this.h = h;
    		ellipse = new Ellipse2D.Double(x,y,w,h);
        }
    	public void updateData(double x, double y){
    		this.x = x;
    		this.y = y;
    		ellipse = new Ellipse2D.Double(x,y,w,h);
        }
    	public SingleEllipse select(MouseEvent e){
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
    
    Cursor curCursor;
    public DrawingCanvas() {
      ellipse.add(new SingleEllipse(20, 20, 100, 75));
      ellipse.add(new SingleEllipse(80, 60, 100, 75));
      setBackground(Color.LIGHT_GRAY);
      addMouseListener(new MyMouseListener());
      addMouseMotionListener(new MyMouseMotionListener());
    }
    public void paint(Graphics g) {
      Graphics2D g2D = (Graphics2D) g;
      for(SingleEllipse singleEllipse : ellipse)
    	  singleEllipse.draw(g2D);
      if (curCursor != null)
        setCursor(curCursor);
    }

    class MyMouseListener extends MouseAdapter {
    	long timer = 0;
    	
      public void mousePressed(MouseEvent e) {
    	Iterator<SingleEllipse> itr = ellipse.iterator();  
      	SingleEllipse singleEllipse = null;
      	while(itr.hasNext() && singleEllipse == null){
    	  	singleEllipse = itr.next().select(e);
      	}
    	if (singleEllipse != null) {
          selectedShape = singleEllipse;
          displayParameters();
      	curCursor = Cursor
        .getPredefinedCursor(Cursor.HAND_CURSOR);
        } else { 
          location.setText("");
        }
        x1 = e.getX();
        y1 = e.getY();
        timer = System.currentTimeMillis();
      }
      public void mouseReleased(MouseEvent e) {

      	if(selectedShape == null && System.currentTimeMillis()-timer <= 500){
        	ellipse.add(new SingleEllipse(e.getX(), e.getY(), 20, 20));
        }

        curCursor = Cursor.getDefaultCursor();
    	selectedShape = null;
        location.setText("");
        canvas.repaint();
      }
    }
    class MyMouseMotionListener extends MouseMotionAdapter {
      public void mouseDragged(MouseEvent e) {
        if (selectedShape != null) {
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

    public void displayParameters() {
      double x = canvas.selectedShape.getX();
      double y = canvas.selectedShape.getY();
      double w = canvas.selectedShape.getWidth();
      double h = canvas.selectedShape.getHeight();
      String locString = "(" + Double.toString(x) + ","
          + Double.toString(y) + ")";
      String sizeString = "(" + Double.toString(w) + ","
          + Double.toString(h) + ")";
      location.setText(locString);
    }
  }
