package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controlbar implements ActionListener
{
	public Controlbar (Container base)
	{
		JMenuBar menubar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenu newProblem = new JMenu("Neu");
		JMenuItem save = new JMenuItem("Speichern");
		JMenu advanced = new JMenu("Erweitert");
		JMenuItem options = new JMenuItem("Optionen");
		options.addActionListener(this);
		
		data.add(newProblem);
		data.add(save);
		advanced.add(options);
		menubar.add(data);
		menubar.add(advanced);
		base.add(menubar, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String cmd = event.getActionCommand();
		if (cmd.equals("Optionen"))
		{			new Options();}
		if (cmd.equals("Neu"))
		{			new NewProblem();}
	}
}