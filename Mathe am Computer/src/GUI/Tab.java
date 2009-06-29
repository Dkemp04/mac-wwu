package GUI;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Tab extends JPanel
{
	public Tab (String content)
	{
		JLabel l_content = new JLabel(content);
		l_content.setHorizontalAlignment(JLabel.LEFT);
		this.add(l_content);
	}
}