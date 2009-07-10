package GUI;
import java.awt.*;
import javax.swing.*;

public class StaticGraph extends JComponent
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;
	
	private GraphDisplay display;
	private JLabel description;
	
	public StaticGraph(Container parent, Graph original)
	{
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläches
		
		description = new JLabel("<html>Beschreibung<br><html>");
		this.setLayout(new GridLayout(1,2));
		display = new GraphDisplay("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Polen.jpg", original);
		this.add(display);
		this.add(description);
		parent.add(this);
	}
}