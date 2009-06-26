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
	public static MainFrame obj = new MainFrame();
	
	public MainFrame ()
	{
		super("Travelling Salesman");
		JPanel base = new JPanel();
		base.setLayout(new BorderLayout());
		base.setBorder(BorderFactory.createEtchedBorder());
		JPanel controls = new JPanel();
		JPanel display = new JPanel();
		
		Controlbar control = new Controlbar();
		TabOrganisation tab_org = new TabOrganisation(display);
		base.add(controls, BorderLayout.NORTH);
		base.add(display, BorderLayout.CENTER);
		getContentPane().add(base);
		
	}
	
	public static void main(String[] args)
	{
		MainFrame test = new MainFrame();
		obj=test;
		test.setLocation(100,100);
		test.setSize(640,480);
	    test.pack();
	    test.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
	}
}