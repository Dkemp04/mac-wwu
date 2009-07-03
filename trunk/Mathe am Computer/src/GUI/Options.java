package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Options extends JDialog  implements ActionListener
{
	private static final long serialVersionUID = 473842935713197692L;

	public Options (JFrame parent)
	{
		super(parent, "Optionen", true);
		JPanel base = new JPanel();
		base.setBorder(BorderFactory.createEtchedBorder()); 
		JLabel header = new JLabel("Look-and-Feel");
		header.setHorizontalAlignment(JButton.LEFT);
		
		JButton metal = new JButton("Metal");
		metal.addActionListener(this);
		metal.setHorizontalAlignment(JButton.LEFT);
		JButton motif = new JButton("Motif");
		motif.addActionListener(this);
		motif.setHorizontalAlignment(JButton.LEFT);
		JButton windows = new JButton("Windows");
		windows.addActionListener(this);
		windows.setHorizontalAlignment(JButton.LEFT);
		
		base.add(header, BorderLayout.WEST);
		base.add(metal, BorderLayout.CENTER);
		base.add(motif, BorderLayout.CENTER);
		base.add(windows, BorderLayout.CENTER);
		getContentPane().add(base);
		
		this.setLocation((int) parent.getLocation().getX()+100,(int) parent.getLocation().getY()+100);
		this.setResizable(false);
		this.setSize(500,65);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		String cmd = a.getActionCommand();
		try
		{
			String plaf = "unknown";
		    if (cmd.equals("Metal")) {
		    	plaf = "javax.swing.plaf.metal.MetalLookAndFeel";}
		    else if (cmd.equals("Motif")) {
		    	plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";}
		    else if (cmd.equals("Windows")) {
		        plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";}
		    
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
		    for (int i=0; i < JFrame.getFrames().length; i++)
		    {
		    	SwingUtilities.updateComponentTreeUI(JFrame.getFrames()[i]);
		    }
		}
		catch (UnsupportedLookAndFeelException e) {
			System.err.println(e.toString());}
		catch (ClassNotFoundException e) {
		    System.err.println(e.toString());}
		catch (InstantiationException e) {
		      System.err.println(e.toString());}
		catch (IllegalAccessException e) {
		      System.err.println(e.toString());}
	}
}