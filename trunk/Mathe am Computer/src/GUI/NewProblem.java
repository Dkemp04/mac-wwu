package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class NewProblem extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1691310154690553788L;

	public NewProblem (JFrame parent)
	{
		super(parent, "Neues Problem", true);
		getContentPane().setLayout(new BorderLayout());
		JPanel head = new JPanel();	
		head.setLayout(new GridLayout(4,4));
		JPanel base_center = new JPanel();
		base_center.setLayout(new BorderLayout());
		JTextField name = new JTextField();
		JTextField anzahl_knoten = new JTextField();
		Graph paint = new Graph();
		
		JPanel buttons = new JPanel();
		
		JButton ok = new JButton("Ok");
		ok.addActionListener(this);
		JButton cancel = new JButton("Abbrechen");
		cancel.addActionListener(this);
		JButton accept = new JButton("Übernehmen");
		accept.addActionListener(this);
		
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1,2));
		JPanel heuristics = new JPanel();
		heuristics.setLayout(new BoxLayout(heuristics, BoxLayout.Y_AXIS));
		heuristics.setBorder(BorderFactory.createTitledBorder("Verfahren"));
		JTextArea description = new JTextArea();
		description.setEnabled(false);
		description.setBorder(BorderFactory.createTitledBorder("Beschreibung"));
		
		JCheckBox bab = new JCheckBox("Branch-and-Bound");
		heuristics.add(bab);
		JCheckBox mst = new JCheckBox("Minimal Spanning Tree");
		heuristics.add(mst);
		JCheckBox sa = new JCheckBox("Simulated Annealing");
		heuristics.add(sa);
		JCheckBox sam = new JCheckBox("Selbst­organisierende Karte");
		heuristics.add(sam);
		JCheckBox ch = new JCheckBox("Christofides-Heuristik");
		heuristics.add(ch);
		JCheckBox koh = new JCheckBox("K-Opt-Heuristik");
		heuristics.add(koh);
		
		
		head.add(new JLabel("Name"));
		head.add(name);
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel("Anzahl Knoten"));
		head.add(anzahl_knoten);
		head.add(new JLabel(""));
		
		center.add(heuristics);
		center.add(description);
		
		buttons.add(ok);
		buttons.add(cancel);
		buttons.add(accept);
		
		getContentPane().add(new Label(""), BorderLayout.WEST);
		getContentPane().add(new Label(""), BorderLayout.NORTH);
		
		base_center.add(head, BorderLayout.NORTH);
		base_center.add(center, BorderLayout.CENTER);
		base_center.add(buttons, BorderLayout.SOUTH);
		getContentPane().add(base_center, BorderLayout.CENTER);
		getContentPane().add(paint, BorderLayout.CENTER);
		
		getContentPane().add(new Label(""), BorderLayout.EAST);
		getContentPane().add(new Label(""), BorderLayout.SOUTH);

		this.setLocation(100,100);
		this.setSize(500,65);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		String cmd = a.getActionCommand();
		if (cmd.equals("Ok")) {
			this.setVisible(false);System.exit(0);}
		else if (cmd.equals("Abbrechen")) {
		    this.setVisible(false);System.exit(0);}
		else if (cmd.equals("Übernehmen")) {
			this.setVisible(false);System.exit(0);}
	}
}