package GUI;
import java.awt.*;
import javax.swing.*;

/**Klasse, die einen leeren Rahmen um einen Container setzt, um das Layout zu verbessern
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class WhitespaceFrame extends JPanel
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse WhitespaceFrame
	private static final long serialVersionUID = -1436071032096961816L;
	
	/**Methode, welche mit Hilfe eines BorderLayouts ein Container dekoriert und diesen wieder zurückgibt
	 * @param parent Vater-Container, in welchem der Rahmen eingefügt werden soll
	 * @param child  Kind-Container, welches in die Mitte des Vater-Containers, umgeben von dem leeren Rahmen, eingefügt werden soll
	 * @return Gibt das modifizierte Vater-Element zurück
	 */
	public Container decorate (Container parent, Container center_child)
	{
		//Stellt das BordrLayout für den Vater-Container ein 
		parent.setLayout(new BorderLayout());
		
		//Hinzufügen des Kind-Container und des leeren Rahmens
		parent.add(new Label(""), BorderLayout.WEST);
		parent.add(new Label(""), BorderLayout.NORTH);
		parent.add(new Label(""), BorderLayout.EAST);
		parent.add(new Label(""), BorderLayout.SOUTH);
		parent.add(center_child, BorderLayout.CENTER);
		
		//Rückgabe des Vater-Containers
		return parent;
	}
}