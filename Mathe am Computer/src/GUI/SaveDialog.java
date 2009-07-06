package GUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveDialog
{
	public void save ()
	{
		//Datei-Auswahl-Dialog mit �bergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser();
        
        //Typ des Datei-Auswahl-Dialoges wird auf "�ffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardm��ig ausgeblendet
        chooser.setFileFilter(new FileNameExtensionFilter("*.tsp;*.dat", "tsp", "dat") );
        
        //Blendet den Dialog ein und 
        int result = chooser.showSaveDialog(null);
        
        //�berpr�fung, ob der Dialog mit "�ffnen" best�tigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {
        	
        }
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
	}
}