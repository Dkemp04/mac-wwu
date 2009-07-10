package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Persistenz.ObjectDeserialization;
import Logik.Logic;
import Logik.Problem;

/**
 * Dialog, der zum Öffnen von vorhandenen Problemen geöffnet wird
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class OpenDialog
{
	/**
	 * Methode, die den Dialog zum Öffnen von Dateien erstellt und initalisiert
	 * Außerdem wird das geöffnete Problem (aus *.tsp-Dateien) geladen und zurückgegeben
	 * @param home Startverzeichnis, welches zu Anfang im Datei-Auswahl-Dialog geöffnet ist
	 * @return Gibt von der Festplatte geladene Problem zurück
	 */
    public Logic openLogic (String home)
    {
    	//Datei-Auswahl-Dialog mit übergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser(home);
        
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
        chooser.setFileFilter( new FileNameExtensionFilter("*.tsp;*.dat", "tsp", "dat") );
        
        //Blendet den Dialog ein und 
        int result = chooser.showOpenDialog(null);
        
        //Attribut, welches das zu ladenen Problem speichert
        Logic openLogic = new Logic();
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {        	openLogic = new ObjectDeserialization().openLogic(chooser.getSelectedFile().toString());}
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
        
        //Gibt das geöffnete Problem zurück
        return openLogic;
    }
    
    public Image openImage (String home)
    {
    	//Datei-Auswahl-Dialog mit übergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser(home);
        
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
        chooser.setFileFilter( new FileNameExtensionFilter("*.jpg; *.jpeg", "jpg", "jpeg") );
        
        //Blendet den Dialog ein und 
        int result = chooser.showOpenDialog(null);
        
        //Attribut, welches das zu ladenen Problem speichert
        Image openImage = null;
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {        	openImage = new ObjectDeserialization().openImage(chooser.getSelectedFile().toString());}
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
        
        //Gibt das geöffnete Problem zurück
        return openImage;
    }
}