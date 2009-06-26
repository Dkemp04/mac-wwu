package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Options extends JFrame  implements ActionListener
{
	public Options ()
	{
		JPanel base = new JPanel();
		base.setBorder(BorderFactory.createEtchedBorder()); 
		JLabel header = new JLabel("Look-and-Feel");
		header.setHorizontalAlignment(JButton.LEFT);
		header.setBorder(BorderFactory.createEtchedBorder()); 
		
		JButton metal = new JButton("Metal");
		metal.addActionListener(this);
		metal.setHorizontalAlignment(JButton.LEFT);
		JButton motif = new JButton("Motif");
		motif.addActionListener(this);
		motif.setHorizontalAlignment(JButton.LEFT);
		JButton windows = new JButton("Windows");
		windows.addActionListener(this);
		windows.setHorizontalAlignment(JButton.LEFT);
		
		base.add(header, BorderLayout.NORTH);
		base.add(metal, BorderLayout.SOUTH);
		base.add(motif, BorderLayout.SOUTH);
		base.add(windows, BorderLayout.SOUTH);
		getContentPane().add(base);
		
		this.setTitle("Optionen");
		this.setLocation(100,100);
		this.setResizable(false);
		this.setSize(500,100);
		//this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		String cmd = a.getActionCommand();
		try
		{
			String plaf = "unknown";
		      if (cmd.equals("Metal")) {
		        plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
		      } else if (cmd.equals("Motif")) {
		        plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		      } else if (cmd.equals("Windows")) {
		        plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		      }
			UIManager.setLookAndFeel(plaf);
		    SwingUtilities.updateComponentTreeUI(this);
		    SwingUtilities.updateComponentTreeUI(MainFrame.obj);
		}
		catch (Exception e)
		{
			System.out.println("Fehler");
		}
	}
}