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
		
		Controlbar control = new Controlbar(this);
		TabOrganisation tab_org = new TabOrganisation(this);
	}
	
	public static void main(String[] args)
	{
		MainFrame test = new MainFrame();
		obj=test;
		test.setLocation(100,100);
		test.setSize(640,480);
	    //test.pack();
	    test.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		
	}
}