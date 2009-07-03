package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controlbar implements ActionListener
{
	private JFrame parent;
	public Controlbar (JFrame base)
	{
		parent = base;
		JMenuBar menubar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenuItem newProblem = new JMenuItem("Neu");
		JMenuItem save = new JMenuItem("Speichern");
		JMenu advanced = new JMenu("Erweitert");
		JMenuItem options = new JMenuItem("Optionen");
		newProblem.addActionListener(this);
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
		if (cmd.equals("Neu"))
		{			NewProblem problem = new NewProblem(parent);
					problem.setAlwaysOnTop(true);
					problem.setModal(true);}
		if (cmd.equals("Optionen"))
		{			new Options(parent);}
	}
}