package GUI;
import javax.swing.*;

public class Tab extends JPanel
{
	//Deklarierung der serialVersionUID f�r die serialisierbare Klasse Tab
	private static final long serialVersionUID = 4872614561375769981L;

	public Tab (String content)
	{
		JLabel l_content = new JLabel(content);
		l_content.setHorizontalAlignment(JLabel.LEFT);
		this.add(l_content);
	}
}