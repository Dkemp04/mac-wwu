package Test;

import java.awt.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class Splash extends JDialog implements Runnable
{
	private static final long serialVersionUID = -1048382339479929593L;
	
	private Image capture;
	private Image picture;
	private Timer timer;
	
	public Splash(String file, int w, int h, long millis)
	{
		setSize(w, h);
		setUndecorated(true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int frmX = ((int) d.getWidth() - w) / 2;
		int frmY = ((int) d.getHeight() - h) / 2;
		setLocation(frmX, frmY);
		
		try
		{
			Robot rob = new Robot();
			Rectangle rect = new Rectangle(frmX, frmY, w, h);
			capture = rob.createScreenCapture(rect);
		}
		catch (AWTException e)
		{
			e.printStackTrace();
		}
		
		MediaTracker mt = new MediaTracker(this);
		picture = Toolkit.getDefaultToolkit().getImage(file)
				.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		mt.addImage(picture, 0);
		
		try
		{
			mt.waitForAll();
		}
		catch (InterruptedException e2)
		{
			e2.printStackTrace();
		}
		
		setVisible(true);
		if (picture == null)
			picture = createImage(w, h);
		timer = new Timer();
		timer.schedule(new ExitTimerTask(this), millis);
	}
	
	public void run ()
	{
		
	}
	
	public static void main(String[] args)
	{
		new Splash("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/images/logo.jpg", 800, 600, 5000);
	}
	
	public void paint(Graphics g)
	{
		if (picture != null && capture != null)
		{
			capture.getGraphics().drawImage(picture, 0, 0, this);
			g.drawImage(capture, 0, 0, this);
		}
	}
	
	class ExitTimerTask extends TimerTask
	{
		private JDialog diag;
		
		public ExitTimerTask(JDialog diag)
		{
			this.diag = diag;
		}
		
		public void run()
		{
			diag.setVisible(false);
			diag.dispose();
			System.exit(0);
		}
	}
}