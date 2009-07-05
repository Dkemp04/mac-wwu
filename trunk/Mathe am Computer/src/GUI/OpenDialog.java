package GUI;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Persistenz.ObjectDeserialization;

public class OpenDialog {

	public static void main(String[] args)
    {
    	OpenDialog op =  new OpenDialog();
    	op.open("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/");
    }
    
    public Object open(String home)
    {
        JFileChooser chooser = new JFileChooser(home);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileFilter( new FileNameExtensionFilter("*.tsp;*.dat", "tsp", "dat") ); 
        chooser.setCurrentDirectory(new File (home));

        chooser.setVisible(true);
        int result = chooser.showOpenDialog(null);
        
        Object obj = new Object();
        if (result == JFileChooser.APPROVE_OPTION) {
            obj = new ObjectDeserialization().open(chooser.getSelectedFile().toString());}
        chooser.setVisible(false);
        return obj;
    }
}