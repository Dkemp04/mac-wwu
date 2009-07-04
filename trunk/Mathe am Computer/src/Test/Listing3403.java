package Test;

/* Listing3403.java */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import GUI.*;

public class Listing3403
extends Frame
{
  private Image img;

  public static void main(String[] args)
  {
    Listing3403 wnd = new Listing3403();
  }

  public Listing3403()
  {
    super("Bitmap");
    setBackground(Color.lightGray);
    setSize(250,150);
    setVisible(true);
    //WindowListener
    addWindowListener(new WindowClosingAdapter(true));
    //Bild laden
    img = getToolkit().getImage("duke.gif");
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(img, 0);
    try {
      //Warten, bis das Image vollständig geladen ist,
      mt.waitForAll();
    } catch (InterruptedException e) {
      //nothing
    }
    new SwingWorker<BufferedImage, Void>() { 
        @Override protected BufferedImage doInBackground() throws IOException { 
          return ImageIO.read( "duke.gif" ); 
        } 
        @Override protected void done() { 
          try { this.setImage( get() ); } catch ( Exception e ) { } 
        } 
      }.execute();
    repaint();
  }

  public void paint(Graphics g)
  {
    if (img != null) {
      g.drawImage(img,40,40,this);
    }
  }
}