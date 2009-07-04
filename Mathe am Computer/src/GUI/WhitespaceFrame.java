package GUI;
import java.awt.*;
import javax.swing.*;

public class WhitespaceFrame extends JPanel
{
	private static final long serialVersionUID = -1436071032096961816L;
	
	public JPanel decorate (JPanel parent, JPanel center_child)
	{
		parent.setLayout(new BorderLayout());
		parent.add(new Label(""), BorderLayout.WEST);
		parent.add(new Label(""), BorderLayout.NORTH);
		parent.add(new Label(""), BorderLayout.EAST);
		parent.add(new Label(""), BorderLayout.SOUTH);
		parent.add(center_child, BorderLayout.CENTER);
		return parent;
	}
}