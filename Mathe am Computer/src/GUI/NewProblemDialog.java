package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Logik.Problem;
import Logik.Point;
import Persistenz.ObjectSerialization;

/**
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class NewProblemDialog extends JDialog implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse NewProblem
	private static final long serialVersionUID = 1691310154690553788L;
	
	//Erzeugung der Zeichenfläche
	Graph map;
	JTabbedPane steps;
	JFrame parent;
	JTextField name;
	
	//Deklarierung der Buttons und des zugehörigen Panel im ersten Schritt der Problemerstellung
	JPanel buttons1;
	JButton cancel1;
	JButton forward1;
	
	//Deklarierung der Buttons und des zugehörigen Panel im zweiten Schritt der Problemerstellung
	JPanel buttons2;
	JButton back2;
	JButton cancel2;
	JButton ready2;
	
	//Deklarierung der Heuristik-Auswahl und -Beschreibung im ersten Schritt der Problemerstellung
	JPanel heuristics;
	JPanel heuristics_selection;
	JTextArea description;
	
	JCheckBox bab;
	JCheckBox mst;
	JCheckBox sa;
	JCheckBox sam;
	JCheckBox ch;
	JCheckBox koh;
	
	//
	JPanel head;
	JPanel step1;
	JPanel step2;
	JPanel center1;

	public NewProblemDialog (JFrame parent)
	{
		//Einstellung des Fensters (Vater-Fenster, Titel, Modal-Eigenschaft)
		super(parent, "Neues Problem", true);
		this.parent = parent;
		map = new Graph(parent);
		
		//Erzeugung und Einstellung der unteren Buttons für Schritt 1
		buttons1 = new JPanel();
		buttons1.setSize(this.getHeight(), 50);
		cancel1 = new JButton("Abbrechen");
		cancel1.addActionListener(this);
		forward1 = new JButton("Weiter");
		forward1.addActionListener(this);
		
		buttons1.add(cancel1);
		buttons1.add(forward1);
		
		
		//Erzeugung und Einstellung der unteren Buttons für Schritt 2
		buttons2 = new JPanel();
		buttons2.setSize(this.getHeight(), 50);
		back2 = new JButton("Zurück");
		back2.addActionListener(this);
		cancel2 = new JButton("Abbrechen");
		cancel2.addActionListener(this);
		ready2 = new JButton("Fertig");
		ready2.addActionListener(this);
		
		buttons2.add(back2);
		buttons2.add(cancel2);
		buttons2.add(ready2);
		
		
		//Erzeugung und Einstellung der Auswahl der Heuristik (und den einzelnen Checkboxes) und dessen Beschreibung
		heuristics = new JPanel();
		heuristics.setLayout(new GridLayout(1,2));
		heuristics_selection = new JPanel();
		heuristics_selection.setLayout(new BoxLayout(heuristics_selection, BoxLayout.Y_AXIS));
		heuristics_selection.setBorder(BorderFactory.createTitledBorder("Verfahren"));
		description = new JTextArea();
		description.setEnabled(false);
		description.setBorder(BorderFactory.createTitledBorder("Beschreibung"));
		description.setFont(new Font("Beschreibung", Font.BOLD, 10));
		description.setDisabledTextColor(Color.black);
		description.setLineWrap(true);
		description.setWrapStyleWord(true);

		
		bab = new JCheckBox("Branch-and-Bound");
		heuristics_selection.add(bab);
		bab.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Branch-and-Bound");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		mst = new JCheckBox("Minimal Spanning Tree");
		heuristics_selection.add(mst);
		mst.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Minimal Spanning Tree");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		sa = new JCheckBox("Simulated Annealing");
		heuristics_selection.add(sa);
		sa.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Simulated Annealing");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		sam = new JCheckBox("Selbst­organisierende Karte");
		heuristics_selection.add(sam);
		sam.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Selbst­organisierende Karte");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		ch = new JCheckBox("Christofides-Heuristik");
		heuristics_selection.add(ch);
		ch.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Christofides-Heuristik");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		koh = new JCheckBox("K-Opt-Heuristik");
		heuristics_selection.add(koh);
		koh.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("K-Opt-Heuristik");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		
		heuristics.add(heuristics_selection);
		heuristics.add(description);
		
		
		//Erzeugung des oberen Bereichs zur Eingabe des Namens und der Anzahl an Knoten
		head = new JPanel();	
		head.setLayout(new GridLayout(4,4));
		name = new JTextField();
		
		head.add(new JLabel("Name"));
		head.add(name);
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		
		
		//Einstellung des Rahmens und der inneren Komponenten
		steps = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
		
		step1 = new JPanel();
		
		center1 = new JPanel();
		center1.setLayout(new BorderLayout());
		
		center1.add(head, BorderLayout.NORTH);
		center1.add(heuristics, BorderLayout.CENTER);
		center1.add(buttons1, BorderLayout.SOUTH);
		
		step2 = new JPanel();
		JPanel center2 = new JPanel();
		map.setBorder(BorderFactory.createEtchedBorder());
		center2.setLayout(new BorderLayout());
		center2.add(new JLabel("<html>Durch Klicken auf die Zeichenfläche können neue Punkte erzeugt werden.<br>Nach der Erzeugung ist es außerdem möglich die Punkte per Drag&Drop zu verschieben.<br>Wenn Sie Punkte wieder löschen möchten, müssen Sie auf den zu löschenden Punkt mit der rechten Maustaste klicken.<html>"), BorderLayout.NORTH);
		center2.add(map, BorderLayout.CENTER);
		center2.add(buttons2, BorderLayout.SOUTH);
		
		steps.addTab("Heuristik", new WhitespaceFrame().decorate(step1, center1));
		steps.addTab("Koordinaten", new WhitespaceFrame().decorate(step2, center2));
		steps.setEnabledAt(1, false);
		setContentPane(steps);
		
		
		//Allgemeine Einstellung des Frames
		this.setLocation(parent.getX() + 100, parent.getY() + 100);
		this.setSize(400,400);
		//this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		//Auswertung des Objektes, von welchem der Befehl ausgegangen ist
		String cmd = a.getActionCommand();
		if (cmd.equals("Zurück"))
		{
			if (steps.getSelectedIndex() > 0)
				steps.setSelectedIndex(steps.getSelectedIndex() - 1);
			if (steps.getTitleAt(steps.getSelectedIndex()) != "Koordinaten")
				this.setSize(400,400);
			
		}
		else if (cmd.equals("Abbrechen"))
		{
		    this.dispose();
		}
		else if (cmd.equals("Weiter"))
		{
			this.setSize(map.getHeight() + 100, map.getWidth());
			if (steps.getSelectedIndex() < steps.getTabCount() - 1)
			{
				steps.setSelectedIndex(steps.getSelectedIndex() + 1);
				if (steps.getTitleAt(steps.getSelectedIndex()) == "Koordinaten")
				{
					steps.setEnabledAt(1, true);
					this.setSize(map.getHeight() + 100, map.getWidth());
				}
				this.repaint();
			}
		}
		else if (cmd.equals("Fertig"))
		{
			int choice = JOptionPane.showConfirmDialog(this, "Sind sie sicher ?", "Daten korrekt", 0);
			if (choice == JOptionPane.YES_OPTION)
			{
				Problem newProblem = new Problem();
				for (int i = 0; i < map.getEllipseCount(); i++)
				{
					try {
						newProblem.AddPoint(new Point(map.getEllipses().get(i).getX(),map.getEllipses().get(i).getY()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				new ObjectSerialization().save(name.getText(), newProblem);
				new ObjectSerialization().save(name.getText()+"GUI", map);
				//StaticGraph representation = new StaticGraph(parent);
				((MainFrame) parent).getDesktop().addChildFrame(map, "Test", map.getX() + 10, map.getY() + 10, 300, 300);
				this.dispose();
			}
		}
	}
}