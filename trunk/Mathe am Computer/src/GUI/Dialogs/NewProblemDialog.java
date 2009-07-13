package GUI.Dialogs;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import GUI.Main.ChildFrame;
import GUI.Main.MainFrame;
import GUI.MapDisplay.Graph;
import GUI.MapDisplay.StaticGraph;
import GUI.Tools.WhitespaceFrame;
import Logic.*;
import Logic.Point;
import Methods.BranchAndBound;
import Methods.MST;
import Methods.NearestNeighbor;

/**
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class NewProblemDialog extends JDialog implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse NewProblem
	private static final long serialVersionUID = 1691310154690553788L;
	
	private static final int BORDER_WIDTH = 40;
	private static final int BORDER_HEIGHT = 210;
	
	//Erzeugung der Zeichenfläche
	private Problem newProblem;
	private Graph map;
	private Container parent;
	private JTextField name;
	private JComboBox map_selection;
	
	//Deklarierung der Buttons und des zugehörigen Panel im ersten Schritt der Problemerstellung
	private JPanel buttons1;
	private JButton cancel1;
	private JButton forward1;
	
	//Deklarierung der Buttons und des zugehörigen Panel im zweiten Schritt der Problemerstellung
	JPanel buttons2;
	private JButton back2;
	private JButton cancel2;
	private JButton ready2;
	
	//Deklarierung der Heuristik-Auswahl und -Beschreibung im ersten Schritt der Problemerstellung
	private JPanel heuristics;
	private JPanel heuristics_selection;
	private JTextArea description;
	
	private JCheckBox bab;
	private JCheckBox nn;
	private JCheckBox mst;
	private JCheckBox sa;
	private JCheckBox sam;
	private JCheckBox ch;
	private JCheckBox koh;
	
	//
	private JPanel head;
	private JPanel step1;
	private JPanel step2;
	private JPanel center1;

	public NewProblemDialog (Container parent)
	{
		//Einstellung des Fensters (Vater-Fenster, Titel, Modal-Eigenschaft)
		super((JFrame) parent, "Neues Problem", true);
		this.parent = parent;
		newProblem = new Problem();
		//this.add(map);
		map_selection = new JComboBox();
		for (int i = 0; i < ((MainFrame) parent).getMaps().size() ; i++)
			map_selection.addItem(((MainFrame) parent).getMaps().get(i).getName());
		map_selection.addActionListener(this);
		
		map = new Graph(((MainFrame) parent).getMapManager().getMapPath((String) map_selection.getSelectedItem()));
		
		//Erzeugung und Einstellung der unteren Buttons für Schritt 1
		buttons1 = new JPanel();
		cancel1 = new JButton("Abbrechen");
		cancel1.addActionListener(this);
		forward1 = new JButton("Weiter");
		forward1.addActionListener(this);
		
		buttons1.add(cancel1);
		buttons1.add(forward1);
		
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
		bab.addActionListener(this);
		
		mst = new JCheckBox("Minimal Spanning Tree");
		heuristics_selection.add(mst);
		mst.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Minimal Spanning Tree");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		mst.addActionListener(this);
		
		nn = new JCheckBox("Nearest Neighbor");
		heuristics_selection.add(nn);
		nn.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (nn.contains(e.getX(), e.getY())){ 
					description.setText("Nearest Neighbor");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		nn.addActionListener(this);
		
		sa = new JCheckBox("Simulated Annealing");
		heuristics_selection.add(sa);
		sa.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Simulated Annealing");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		sa.addActionListener(this);
		
		sam = new JCheckBox("Selbst­organisierende Karte");
		heuristics_selection.add(sam);
		sam.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Selbst­organisierende Karte");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		sam.addActionListener(this);
		
		ch = new JCheckBox("Christofides-Heuristik");
		heuristics_selection.add(ch);
		ch.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("Christofides-Heuristik");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		ch.addActionListener(this);
		
		koh = new JCheckBox("K-Opt-Heuristik");
		heuristics_selection.add(koh);
		koh.addMouseListener(new MouseAdapter(){
			public void mouseEntered (MouseEvent e){
				if (bab.contains(e.getX(), e.getY())){ 
					description.setText("K-Opt-Heuristik");}}
			public void mouseExited (MouseEvent e){
				description.setText("");}});
		koh.addActionListener(this);
		
		heuristics.add(heuristics_selection);
		heuristics.add(description);
		
		
		//Erzeugung des oberen Bereichs zur Eingabe des Namens und der Anzahl an Knoten
		head = new JPanel();	
		head.setLayout(new GridLayout(4,4));
		name = new JTextField();
		name.setText("");
		
		head.add(new JLabel("Name"));
		head.add(name);
		head.add(new JLabel("Karte"));
		head.add(map_selection);
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		head.add(new JLabel(""));
		
		//Einstellung des Rahmens und der inneren Komponenten
		
		step1 = new JPanel();
		
		center1 = new JPanel();
		center1.setLayout(new BorderLayout());
		
		center1.add(head, BorderLayout.NORTH);
		center1.add(heuristics, BorderLayout.CENTER);
		center1.add(buttons1, BorderLayout.SOUTH);
		
		step1 = (JPanel) new WhitespaceFrame().decorate(center1);
		this.setContentPane(step1);
		
		//Allgemeine Einstellung des Frames
		this.setLocation(parent.getX(), parent.getY());
		this.setSize(400,400);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a)
	{
		if (a.getSource().equals(map_selection))
		{
			map = new Graph(((MainFrame) parent).getMapManager().getMapPath((String) map_selection.getSelectedItem()));
		}
		if (a.getActionCommand().equals("Zurück"))
		{
			this.setContentPane(step1);
			this.setSize(400,400);
		}
		else if (a.getActionCommand().equals("Abbrechen"))
		{
		    this.dispose();
		}
		else if (a.getActionCommand().equals("Weiter"))
		{			
			//Erzeugung und Einstellung der unteren Buttons für Schritt 2
			buttons2 = new JPanel();
			back2 = new JButton("Zurück");
			back2.addActionListener(this);
			cancel2 = new JButton("Abbrechen");
			cancel2.addActionListener(this);
			ready2 = new JButton("Fertig");
			ready2.addActionListener(this);
			
			buttons2.add(back2);
			buttons2.add(cancel2);
			buttons2.add(ready2);
			
			step2 = new JPanel();
			JPanel center2 = new JPanel();
			map.setBorder(BorderFactory.createEtchedBorder());
			center2.setLayout(new BorderLayout());
			center2.add(new JLabel("<html>Durch Klicken auf die Zeichenfläche können neue Punkte erzeugt werden.<br>Nach der Erzeugung ist es außerdem möglich die Punkte per Drag&Drop zu verschieben.<br>Wenn Sie Punkte wieder löschen möchten, müssen Sie auf den zu löschenden Punkt mit der rechten Maustaste klicken.<br><b>Achtung:</b><br>Die Tabs der verschiedenen Verfahren werden erst bei Fertigstellung erscheinen.<html>"), BorderLayout.NORTH);
			center2.add(map, BorderLayout.CENTER);
			center2.add(buttons2, BorderLayout.SOUTH);
			step2 = (JPanel) new WhitespaceFrame().decorate(center2);
			
			this.setContentPane(step2);
			this.setSize(map.getWidth(), map.getHeight());
			this.setSize(map.getCanvas().getBackgroundImage().getWidth() + BORDER_WIDTH, map.getCanvas().getBackgroundImage().getHeight() + BORDER_HEIGHT);
			this.repaint();
		}
		else if (a.getActionCommand().equals("Fertig"))
		{
			if (map.getEllipseCount() < 3)
			{
				JOptionPane.showMessageDialog(this, "Bitte erstellen Sie mindestens 3 Punkte.", "Zu wenig Punkte", JOptionPane.WARNING_MESSAGE);
			}
			else if (name.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Bitte geben Sie einen Namen für das Problem ein", "Name fehlt", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				int choice = JOptionPane.showConfirmDialog(this, "Sind sie sicher ?", "Daten korrekt", 0);
				if (choice == JOptionPane.YES_OPTION)
				{
					try
					{
						for (int i = 0; i < map.getEllipseCount(); i++)
						{
							if (i == 0)
							{
								newProblem.AddStartingPoint(new Point(map.getEllipses().get(i).getX(), map.getEllipses().get(i).getY()));
							}
							else
							{
								newProblem.AddPoint(new Point(map.getEllipses().get(i).getX(), map.getEllipses().get(i).getY()));
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					StaticGraph representation = new StaticGraph(parent, map, null);
					Logic logic = new Logic();
					logic.setProblem(newProblem);
					if(bab.isSelected())
						logic.addMethode(new BranchAndBound(newProblem));
					if(mst.isSelected())						
						logic.addMethode(new MST(newProblem));
					if(nn.isSelected())
						logic.addMethode(new NearestNeighbor(newProblem));
					
					ChildFrame newChild = ((MainFrame) parent).getDesktop().addChildFrame(parent, map, logic, name.getText(), representation.getX() + 10, representation.getY() + 10, (map.getWidth()*2) + 10, map.getHeight() + 20);
					newChild.addTabToChildFrame("Ausgangssituation", representation);
					
					this.dispose();
				}
			}
		}
	}
}