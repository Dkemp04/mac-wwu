package GUI;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import Persistenz.ObjectDeserialization;
import Logik.Logic;
import Logik.Problem;

/**
 * Dialog, der zum �ffnen von vorhandenen Problemen ge�ffnet wird
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class OpenDialog
{
	/**
	 * Methode, die den Dialog zum �ffnen von Dateien erstellt und initalisiert
	 * Au�erdem wird das ge�ffnete Problem (aus *.tsp-Dateien) geladen und zur�ckgegeben
	 * @param home Startverzeichnis, welches zu Anfang im Datei-Auswahl-Dialog ge�ffnet ist
	 * @return Gibt von der Festplatte geladene Problem zur�ck
	 */
    public Logic openLogic (String home)
    {
    	//Datei-Auswahl-Dialog mit �bergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser(home);
        
        //Typ des Datei-Auswahl-Dialoges wird auf "�ffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardm��ig ausgeblendet
        chooser.setFileFilter( new FileNameExtensionFilter("*.tsp;*.dat", "tsp", "dat") );
        
        //Blendet den Dialog ein und 
        int result = chooser.showOpenDialog(null);
        
        //Attribut, welches das zu ladenen Problem speichert
        Logic openLogic = new Logic();
        
        //�berpr�fung, ob der Dialog mit "�ffnen" best�tigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {        	openLogic = new ObjectDeserialization().openLogic(chooser.getSelectedFile().toString());}
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
        
        //Gibt das ge�ffnete Problem zur�ck
        return openLogic;
    }
    
    public Image openImage (String home)
    {
    	//Datei-Auswahl-Dialog mit �bergebenen Startverzeichnis wird erstellt
        JFileChooser chooser = new JFileChooser(home);
        
        //Typ des Datei-Auswahl-Dialoges wird auf "�ffnen-Dialog" festgelegt
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardm��ig ausgeblendet
        chooser.setFileFilter( new FileNameExtensionFilter("*.jpg; *.jpeg", "jpg", "jpeg") );
        
        //Blendet den Dialog ein und 
        int result = chooser.showOpenDialog(null);
        
        //Attribut, welches das zu ladenen Problem speichert
        Image openImage = null;
        
        //�berpr�fung, ob der Dialog mit "�ffnen" best�tigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {        	openImage = new ObjectDeserialization().openImage(chooser.getSelectedFile().toString());}
        
        //Blendet den Dialog aus
        chooser.setVisible(false);
        
        //Gibt das ge�ffnete Problem zur�ck
        return openImage;
    }
}