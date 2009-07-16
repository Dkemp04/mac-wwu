package GUI.Dialogs;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import GUI.Main.*;
import GUI.Tools.*;
import Logic.*;

/**
 * Dialog, der zum Öffnen von vorhandenen Problemen geöffnet wird
 * @author Steffen Pichler, Christian Vogel, Veysel Aksak, Daniel Kemper
 */
public class DataDialog extends JFileChooser
{
	private static final long serialVersionUID = 3312127889351988698L;
	
	public DataDialog ()
	{
		//
    	this.setCurrentDirectory(new File(MainFrame.WORKSPACE));
    	
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
    	this.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
    	this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	
	/**
	 * Methode, die den Dialog zum Öffnen von Dateien erstellt und initalisiert
	 * Außerdem wird das geöffnete Problem (aus *.tsp-Dateien) geladen und zurückgegeben
	 * @param home Startverzeichnis, welches zu Anfang im Datei-Auswahl-Dialog geöffnet ist
	 * @return Gibt von der Festplatte geladene Problem zurück
	 */
    public Logic openLogic (String ending)
    {
    	//Attribut, welches das zu ladenen Problem speichert
        Logic openLogic = null;
        
        //
    	this.setCurrentDirectory(new File(MainFrame.WORKSPACE));
    	
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
    	this.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
    	this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
        this.setFileFilter( new FileNameExtensionFilter("*." + ending, ending) );
        
        //Blendet den Dialog ein und 
        int result = this.showOpenDialog(null);
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {
        	//
        	openLogic = new StorageOrganisation().openLogic(this.getSelectedFile().toString(), ending);
        	
        	//Blendet den Dialog aus
            this.setVisible(false);
        	
        	//Gibt das geöffnete Problem zurück
            return openLogic;
        }
        return null;
    }
    
    public String openImage (String ending)
    {
        //Attribut, welches das zu ladenen Problem speichert
    	String imagePath = null;    	
        
    	//
    	this.setCurrentDirectory(new File(MainFrame.WORKSPACE));
    	
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
    	this.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
    	this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    	
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
    	this.setFileFilter( new FileNameExtensionFilter("*." + ending, ending) );
        
        //Blendet den Dialog ein und 
        int result = this.showOpenDialog(null);
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {
        	//
        	imagePath = this.getSelectedFile().toString();
        	
        	//Blendet den Dialog aus
            this.setVisible(false);
        	
        	//Gibt das geöffnete Problem zurück
            return imagePath;
        }
        return null;
    }
    
    public String saveLogic (Logic saveLogic, String ending)
	{
		//
    	this.setCurrentDirectory(new File(MainFrame.WORKSPACE));
		
        //Typ des Datei-Auswahl-Dialoges wird auf "Öffnen-Dialog" festgelegt
        this.setDialogType(JFileChooser.SAVE_DIALOG);
        
        //Einstellung, die bezweckt, dass sowohl Dateien als auch Verzeichnisse bei diesem Dialog angezeigt werden
        this.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //Setzt den Filter des Dialoges auf .tsp -und .dat - Dateien fest. Damit werden Dateien mit anderen Dateiendungen standardmäßig ausgeblendet
        this.setFileFilter(new FileNameExtensionFilter("*." + ending, ending) );
        //Blendet den Dialog ein und 
        int result = this.showSaveDialog(null);
        
        //
        String filename = this.getName(getSelectedFile());
        
        //Überprüfung, ob der Dialog mit "Öffnen" bestätigt wurde und Laden der Datei mit der Klasse "ObjectDeserialization"
        if (result == JFileChooser.APPROVE_OPTION)
        {
        	//
        	new StorageOrganisation().save(filename, ending, saveLogic);
        	
            //Blendet den Dialog aus
        	this.setVisible(false);
        	
        	//
            return filename;
        }
        return null;
	}
    
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
        {
        	//
        	path = chooser.getSelectedFile().toString();
        	
        	//Blendet den Dialog aus
            chooser.setVisible(false);
            
            //
            return path;
        }
        return null;
    }
}