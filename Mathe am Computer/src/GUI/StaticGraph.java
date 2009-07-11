package GUI;
import java.awt.*;
import javax.swing.*;
import Logik.History;

public class StaticGraph extends JComponent
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;
	
	private GraphDisplay display;
	private JLabel description;
	
	public StaticGraph(Container parent, Graph original)
	{
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläches
		
		description = new JLabel("<html><b>Beschreibung</b><br>1. Schritt:<br>2. Schritt:<br>3. Schritt:</html>");
		description.setHorizontalAlignment(JLabel.LEFT);
		description.setVerticalAlignment(JLabel.TOP);
		description.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new GridLayout(1,2));
		display = new GraphDisplay("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Deutschland.jpg", original);
		this.add(display);
		this.add(description);
		parent.add(this);
	}
	
	public StaticGraph(Container parent, Graph original, History history)
	{
		description = new JLabel("<html><b>Beschreibung</b><br>1. Schritt:<br>2. Schritt:<br>3. Schritt:</html>");
		description.setHorizontalAlignment(JLabel.CENTER);
		description.setVerticalAlignment(JLabel.TOP);
		this.setLayout(new GridLayout(1,2));
		display = new GraphDisplay("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Deutschland.jpg", original, history);
		this.add(display);
		this.add(description);
		parent.add(this);
	}
}