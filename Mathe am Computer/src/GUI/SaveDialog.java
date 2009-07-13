package GUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Logic.*;
import Storage.ObjectSerialization;

public class SaveDialog extends JFileChooser
{
	private static final long serialVersionUID = 9132757613161804632L;

	public String save (Logic saveLogic)
	{
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
        this.setDialogType(JFileChooser.SAVE_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
        this.setFileFilter(new FileNameExtensionFilter("*.tsp", "tsp") );
        
        //Blendet den Dialog ein und 
        int result = this.showSaveDialog(null);
        
        //
        String filename = this.getName(getSelectedFile());
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {
        	new ObjectSerialization().save(filename, saveLogic);
        }
        
        //Blendet den Dialog aus
        this.setVisible(false);
        return filename;
	}
}