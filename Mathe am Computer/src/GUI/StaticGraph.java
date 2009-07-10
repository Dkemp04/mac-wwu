package GUI;
import java.awt.*;
import javax.swing.*;

public class StaticGraph extends JComponent
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;
	
	private GraphDisplay display;
	
	public StaticGraph(Container parent, Graph original)
	{
		//Aufruf des Superklassen-Konstrukters und Erzeugung der Zeichenfläches
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		display = new GraphDisplay("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Polen.jpg", original);
		this.add(display);
		parent.add(this);
		
	    //Einstellung des Frames und dessen Komponenten (Zeichenfläche und Koordinaten-Anzeige)
	    this.setVisible(true);
	}
}