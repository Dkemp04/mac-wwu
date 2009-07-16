package GUI.Dialogs;

import java.awt.*;
import javax.swing.*;

/**
 * 
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class SplashDialog extends Thread
{
	private static final long serialVersionUID = -1048382339479929593L;
	
	private SplashDiag diag;
	
	private String file;
	private int w, h;
	private long millis;
	private boolean running;
	
	public SplashDialog (String file, int w, int h, long millis)
	{
		this.file = file;
		this.w = w;
		this.h = h;
		this.millis = millis;
		this.running = true;
	}
	
	public void run ()
	{
		this.running = true;
	    diag = new SplashDiag ();
	    try
	    {
			sleep(millis);
		}
	    catch (InterruptedException e)
	    {
			e.printStackTrace();
		}
	    diag.setVisible(false);
	    diag.dispose();
	    this.running = false;
	}
	
	public boolean isRunning()
	{		return running;}
	
	public class SplashDiag extends JDialog
	{
		private static final long serialVersionUID = -3164882275585376898L;
		
		public SplashDiag()
		{
			this.setSize(w, h);
			this.setUndecorated(true);
			this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - w) / 2,
							((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - h) / 2));
			this.setVisible(true);
		}
		
		public void paint(Graphics g)
		{
			g.drawImage(Toolkit.getDefaultToolkit().getImage(file), 0, 0, diag);
		}
	}
}