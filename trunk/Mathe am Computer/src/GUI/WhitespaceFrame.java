package GUI;
import java.awt.*;
import javax.swing.*;

/**Klasse, die einen leeren Rahmen um einen Container setzt, um das Layout zu verbessern
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class WhitespaceFrame extends JPanel
{
	//Deklarierung der serialVersionUID f�r die serialisierbare Klasse WhitespaceFrame
	private static final long serialVersionUID = -1436071032096961816L;
	
	/**Methode, welche mit Hilfe eines BorderLayouts ein Container dekoriert und diesen wieder zur�ckgibt
	 * @param parent Vater-Container, in welchem der Rahmen eingef�gt werden soll
	 * @param child  Kind-Container, welches in die Mitte des Vater-Containers, umgeben von dem leeren Rahmen, eingef�gt werden soll
	 * @return Gibt das modifizierte Vater-Element zur�ck
	 */
	public Container decorate (Container center_child)
	{
		//Stellt das BordrLayout f�r den Vater-Container ein
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		//Hinzuf�gen des Kind-Container und des leeren Rahmens
		content.add(new Label(""), BorderLayout.WEST);
		content.add(new Label(""), BorderLayout.NORTH);
		content.add(new Label(""), BorderLayout.EAST);
		content.add(new Label(""), BorderLayout.SOUTH);
		content.add(center_child, BorderLayout.CENTER);
		
		//R�ckgabe des Vater-Containers
		return content;
	}
}