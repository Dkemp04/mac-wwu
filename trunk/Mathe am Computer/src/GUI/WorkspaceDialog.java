package GUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Persistenz.ObjectDeserialization;
import Logik.Problem;

/**
 * 
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class WorkspaceDialog
{
	/**
	 * 
	 */
    public String openDirectory(String home)
    {
    	//Datei-Auswahl-Dialog mit übergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser(home);
        
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //Blendet den Dialog ein und 
        int result = chooser.showOpenDialog(null);
        
        //
        String path = "";
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {        	path = chooser.getSelectedFile().toString();}
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
        
        //
        return path;
    }
}