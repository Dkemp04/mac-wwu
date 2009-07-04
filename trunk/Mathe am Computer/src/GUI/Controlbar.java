package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Logik.Problem;

public class Controlbar implements ActionListener
{
	public JFrame parent;
	public Controlbar (JFrame base)
	{
		parent = base;
		JMenuBar menubar = new JMenuBar();
		JMenu data = new JMenu("Datei");
		JMenuItem newProblem = new JMenuItem("Neu");
		JMenuItem open = new JMenuItem("Öffnen");
		JMenuItem save = new JMenuItem("Speichern");
		JMenuItem k_import = new JMenuItem("Import...");
		JMenuItem exit = new JMenuItem("Beenden");
		JMenu advanced = new JMenu("Erweitert");
		JMenuItem options = new JMenuItem("Optionen");
		newProblem.addActionListener(this);
		open.addActionListener(this);
		exit.addActionListener(this);
		options.addActionListener(this);
		
		data.add(newProblem);
		data.add(open);
		data.add(save);
		data.add(k_import);
		data.add(exit);
		advanced.add(options);
		menubar.add(data);
		menubar.add(advanced);
		base.add(menubar, BorderLayout.NORTH);
	}
	
	public void actionPerformed(ActionEvent event)
	{
		String cmd = event.getActionCommand();
		if (cmd.equals("Neu"))
		{			NewProblemDialog problem = new NewProblemDialog(parent);
					problem.setAlwaysOnTop(true);
					problem.setModal(true);}
		if (cmd.equals("Öffnen"))
		{
			OpenDialog open_dialog = new OpenDialog();
			Problem  openProblem = (Problem) open_dialog.open ("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/");
			Graph map = new Graph(parent);
			double maxX = 0, maxY = 0;
			for (int i = 0; i < openProblem.getPoints().size(); i++)
			{
				double curX = openProblem.getPoints().get(i).getX();
				double curY = openProblem.getPoints().get(i).getY();
				map.addEllipse(curX, curY);
				if (curX > maxX)
				{	maxX = curX;}
				if (curY > maxY)
				{	maxY = curY;}
			}
			((MainFrame) parent).addChildFrame(map, "Test", 10, 10, maxX*2, maxY*2);
		}
		if (cmd.equals("Speichern"))
		{					}
		if (cmd.equals("Import..."))
		{					}
		if (cmd.equals("Optionen"))
		{			new Options(parent);}
		if (cmd.equals("Beenden"))
		{			System.exit(0);}
	}
}