package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * @author Daniel Kemper
 *
 */
public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 995601029595640937L;
	
	JDesktopPane desk = new JDesktopPane();
	static MainFrame obj;
	
	public MainFrame ()
	{
		super("Travelling Salesman");
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch (UnsupportedLookAndFeelException e) {
			System.err.println(e.toString());}
		catch (ClassNotFoundException e) {
		    System.err.println(e.toString());}
		catch (InstantiationException e) {
		      System.err.println(e.toString());}
		catch (IllegalAccessException e) {
		      System.err.println(e.toString());}
		
		
		new Controlbar(this);
		new TabOrganisation(this);
		
		desk.setBackground(Color.WHITE);
		desk.setBorder(BorderFactory.createEtchedBorder());
		desk.setSize(1024,768);
		addChildFrame(new JPanel(), "Child 1", 30, 30, 200, 150);
		addChildFrame(new JPanel(), "Child 2", 230, 30, 200, 150);
		getContentPane().add(desk, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public ChildFrame addChildFrame (JPanel content, String title, double x, double y, double h, double w)
	{
		ChildFrame child = new ChildFrame(title);
		child.setContentPane(content);
		child.setLocation((int) x, (int) y);
		child.setSize((int) h, (int) w);
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		child.addInternalFrameListener(new MyInternalFrameListener());
		child.setVisible(true);
		desk.add(child);
		return child;
	}
	
	public static void main(String[] args)
	{
		MainFrame test = new MainFrame();
		obj = test;
		test.setLocation(100,100);
		test.setSize(640,480);
	    //test.pack();
	    test.setVisible(true);
	}
	
	private class MyInternalFrameListener extends InternalFrameAdapter
	{
		public void internalFrameClosing (InternalFrameEvent e)
		{
			int result = JOptionPane.showConfirmDialog(obj, new Label("Möchten Sie das Problem vor dem Beenden speichern ?"), "Speichern", JOptionPane.YES_NO_OPTION, 3);
			if (result == JOptionPane.YES_OPTION)
			{
				new SaveDialog().save();
			}
			else
			{
				e.getInternalFrame().dispose();
			}
		}
	}
	
	class ChildFrame extends JInternalFrame
	{
		private static final long serialVersionUID = -3194192516658608023L;

		public ChildFrame(String title)
		{
			super(title, true, true);
			setIconifiable(true);
			setMaximizable(true);
		}
	}
}