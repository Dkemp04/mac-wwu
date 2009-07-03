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
	public static MainFrame obj;
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
		JDesktopPane desk = new JDesktopPane();
		desk.setBackground(Color.WHITE);
		desk.setBorder(BorderFactory.createEtchedBorder());
		ChildFrame child1 = new ChildFrame("Child 1");
		child1.setLocation(30,30);
		child1.setSize(200,150);
		child1.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		child1.setVisible(true);
		desk.add(child1);
		ChildFrame child2 = new ChildFrame("Child 2");
		child2.setLocation(230,30);
		child2.setSize(200,150);
		child2.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		child2.setVisible(true);
		desk.add(child2);
		getContentPane().add(desk, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        System.exit(0);
		      }
		    });
	}
	
	public static void main(String[] args)
	{
		MainFrame test = new MainFrame();
		obj = test;
		test.setLocation(100,100);
		test.setSize(640,480);
		test.setResizable(false);
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