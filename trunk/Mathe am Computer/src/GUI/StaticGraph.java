package GUI;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import Logik.History;

public class StaticGraph extends JComponent
{
	//Deklarierung der serialVersionUID f�r die serialisierbare Klasse Graph
	private static final long serialVersionUID = -527399993614992557L;

	private LinkedList<String> steps = new LinkedList<String>();
	
	private GraphDisplay display;
	
	private JPanel right;
	private JButton back;
	private JButton forward;
	private JLabel description;
	
	public StaticGraph(Container parent, Graph original, History history)
	{
		back = new JButton("Schritt zur�ck");
		forward = new JButton("Schritt vor");
		String stepDescription = "";
		for (int i = 0; i < steps.size(); i++)
			stepDescription += (i + 1) + ". Schritt: " + steps.get(i) + "<br>";
		description = new JLabel("<html><b>Beschreibung</b><br>" + stepDescription + "</html>");
		description.setHorizontalAlignment(JLabel.LEFT);
		description.setVerticalAlignment(JLabel.TOP);
		description.setBorder(BorderFactory.createEtchedBorder());
		this.setLayout(new GridLayout(1,2));
		
		display = new GraphDisplay("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/src/GUI/Deutschland.jpg", original, history);

		description.setSize(display.getHeight()-back.getHeight()-forward.getHeight(), display.getWidth() * 2);
		right = new JPanel();
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		right.add(back);
		right.add(forward);
		right.add(description);
		
		this.add(display);
		this.add(right);
		parent.add(this);
	}
	
	public void setStep (int stepnr, String description)
	{
		steps.add(stepnr, description);
	}
}