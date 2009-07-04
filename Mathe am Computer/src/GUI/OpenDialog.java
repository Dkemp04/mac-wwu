package GUI;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.*;
import Persistenz.ObjectDeserialization;

public class OpenDialog {

    public static void main(String[] args)
    {
    	OpenDialog op =  new OpenDialog();
    	op.open("C:/Users/Daniel Kemper/Desktop/Mathe am Computer/Workspace/Mathe am Computer/", "Test2.tsp");
    }
    
    public Object open(String path, String file)
    {
        JFileChooser chooser = new JFileChooser(path + file);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        File home = new File("/home");

        chooser.setCurrentDirectory(home);

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    File f = (File) e.getNewValue();
                }
            }
        });

        chooser.setVisible(true);
        int result = chooser.showOpenDialog(null);
        
        return new ObjectDeserialization(file);
        
        /*if (result == JFileChooser.APPROVE_OPTION) {
            File inputVerzFile = chooser.getSelectedFile();
            String inputVerzStr = inputVerzFile.getPath();
            System.out.println("Eingabepfad:" + inputVerzStr);
        }
        System.out.println("Abbruch");
        chooser.setVisible(false);*/
    }
}