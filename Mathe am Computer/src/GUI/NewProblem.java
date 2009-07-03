package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class NewProblem extends JDialog implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse NewProblem
	private static final long serialVersionUID = 1691310154690553788L;

	public NewProblem (JFrame parent)
	{
		//Einstellung des Frames
		super(parent, "Neues Problem", true);
		getContentPane().setLayout(new BorderLayout());
		
		//Erzeugung der Zeichenfläche
		Graph paint = new Graph(parent);
		
		
		//Erzeugung und Einstellung der unteren Buttons
		JPanel buttons = new JPanel();
		JButton ok = new JButton("Ok");
		ok.addActionListener(this);
		JButton cancel = new JButton("Abbrechen");
		cancel.addActionListener(this);
		JButton accept = new JButton("Übernehmen");
		accept.addActionListener(this);
		
		buttons.add(ok);
		buttons.add(cancel);
		buttons.add(accept);
		
		
		//Erzeugung und Einstellung der mittleren Auswahl der Heuristik (und den einzelnen Checkboxes) und dessen Beschreibung
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
		
		center.add(heuristics);
		center.add(description);
		
		
		//Erzeugung des oberen Bereichs zur Eingabe des Namens und der Anzahl an Knoten
		JPanel head = new JPanel();	
		head.setLayout(new GridLayout(4,4));
		JTextField name = new JTextField();
		JTextField anzahl_knoten = new JTextField();
		
		head.add(new JLabel("Name"));
		head.add(name);
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel("Anzahl Knoten"));
		head.add(anzahl_knoten);
		head.add(new JLabel(""));
		
		
		//Einstellung des Rahmens und der inneren Komponenten
		getContentPane().add(new Label(""), BorderLayout.WEST);
		getContentPane().add(new Label(""), BorderLayout.NORTH);
		
		JPanel base_center = new JPanel();
		base_center.setLayout(new BorderLayout());
		
		base_center.add(head, BorderLayout.NORTH);
		base_center.add(center, BorderLayout.CENTER);
		base_center.add(buttons, BorderLayout.SOUTH);
		getContentPane().add(base_center, BorderLayout.CENTER);
		getContentPane().add(paint, BorderLayout.CENTER);
		
		getContentPane().add(new Label(""), BorderLayout.EAST);
		getContentPane().add(new Label(""), BorderLayout.SOUTH);
		
		//Allgemeine Einstellung des Frames
		this.setLocation(100,100);
		this.setSize(500,65);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		//Auswertung des Objektes, von welchem der Befehl ausgegangen ist
		String cmd = a.getActionCommand();
		if (cmd.equals("Ok")) {
			this.setVisible(false);System.exit(0);}
		else if (cmd.equals("Abbrechen")) {
		    this.setVisible(false);System.exit(0);}
		else if (cmd.equals("Übernehmen")) {
			this.setVisible(false);System.exit(0);}
	}
}