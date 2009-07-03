package GUI;
import java.awt.*;
import javax.swing.*;

public class WhitespaceFrame extends JPanel
{
	private static final long serialVersionUID = -1436071032096961816L;

	public WhitespaceFrame (JPanel parent, JPanel child)
	{
		JPanel comp = new JPanel();
		comp.setLayout(new BorderLayout());
		comp.add(new Label(""), BorderLayout.WEST);
		comp.add(new Label(""), BorderLayout.NORTH);
		comp.add(new Label(""), BorderLayout.EAST);
		comp.add(new Label(""), BorderLayout.SOUTH);
		comp.add(child, BorderLayout.CENTER);
		parent.add(comp);
	}
}