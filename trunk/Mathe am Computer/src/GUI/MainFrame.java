package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * @author Daniel Kemper
 *
 */
public class MainFrame extends JFrame implements ActionListener
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
		addChildFrame(new JPanel(), "Child 1", 30, 30, 200, 150);
		addChildFrame(new JPanel(), "Child 2", 230, 30, 200, 150);
		getContentPane().add(desk, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e)
		      {		System.exit(0);}});
	}
	
	public void addChildFrame (JPanel content, String title, int x, int y, int h, int w)
	{
		ChildFrame child = new ChildFrame(title);
		child.setContentPane(content);
		child.setLocation(x,y);
		child.setSize(h,w);
		child.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		child.setVisible(true);
		desk.add(child);
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
	
	public void actionPerformed(ActionEvent arg0)
	{
		
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