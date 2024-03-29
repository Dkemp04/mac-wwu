package GUI.Dialogs;

import java.awt.*;
import javax.swing.*;
import GUI.Main.*;

/**
 * 
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 9116014615409086016L;
	
	private Image img;
	private JPanel imagePane;
	private JLabel description;
	
	public AboutDialog (Container parent)
	{
		super((JFrame) parent, "�ber", true);
		
		imagePane = new JPanel();
		description = new JLabel("TestdsadasdsafdgfdfdsfesfrefDScfe");
		description.setForeground(Color.BLACK);
		
		img = Toolkit.getDefaultToolkit().getImage(MainFrame.WORKSPACE + "/images/banner.jpg");
		
		this.setLayout(new BorderLayout());
		this.add(imagePane, BorderLayout.CENTER);
		this.add(description, BorderLayout.SOUTH);
		
		this.pack();
		//this.setSize(img.getWidth(imagePane), img.getHeight(imagePane));
		this.setLocation(parent.getX() + 128, parent.getY() + 128);
		//this.setResizable(false);
		this.setVisible(true);
	}
	
	public void paint (Graphics g)
	{
		g = imagePane.getGraphics();
		g.drawImage(img, 0, 0, this);
	}
}