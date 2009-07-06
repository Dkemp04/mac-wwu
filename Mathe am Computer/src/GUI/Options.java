package GUI;
import java.awt.event.*;
import javax.swing.*;

/**
 * Optionen-Fenster, dass zur Einstellung des verschiedenen Look-and-Feels dient
 * @author d_kemp04, chrvogel, u_aksa01, s_pich02
 */
public class Options extends JDialog  implements ActionListener
{
	//Deklarierung der serialVersionUID für die serialisierbare Klasse Options
	private static final long serialVersionUID = 473842935713197692L;
	
	//Deklarierung der GUI-Elemente
	private JLabel header;		//JLabel, welches den Inhalt der Buttons erklärt
	private JButton metal;		//JButton, welches das "Metal"-Look-and-Feel aktiviert
	private JButton motif;		//JButton, welches das "Motif"-Look-and-Feel aktiviert
	private JButton windows;	//JButton, welches das "Windows"-Look-and-Feel aktiviert
	
	/**
	 * Konstruktor zur Einstellung und Darstellung der GUI-Elemente
	 * @param base Vater-Container der Options
	 */
	public Options (JFrame parent)
	{
		//Konstruktor der Oberklasse wird augerufen, dem der Vater-Container und der Titel übergeben wird.
		//Zusätzlich wird eingestellt, dass das Fenster modal ist, d.h. dass es bis zur Beendung fokussiert bleibt
		super(parent, "Optionen", true);
		
		//Die GUI-Elemente werden initialisiert, es werden ihnen ActionListener zugeordnet und die horizontale Ausrichtung gesetzt
		header = new JLabel(" Look-and-Feel ");
		metal = new JButton("Metal");
		metal.addActionListener(this);
		metal.setHorizontalAlignment(JButton.RIGHT);
		motif = new JButton("Motif");
		motif.addActionListener(this);
		motif.setHorizontalAlignment(JButton.RIGHT);
		windows = new JButton("Windows");
		windows.addActionListener(this);
		windows.setHorizontalAlignment(JButton.RIGHT);
		
		//Das Layout des Optionen-Dialoges wird auch das BoxLayout gesetzt und diesem werden die GUI-Elemente (JLabel und 4 JButtons) hinzugefügt
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		getContentPane().add(header);
		getContentPane().add(metal);
		getContentPane().add(motif);
		getContentPane().add(windows);
		
		//Allgemeine Einstellung des Dialoges und des Fensters
		this.setLocation((int) parent.getLocation().getX()+100,(int) parent.getLocation().getY()+100);
		this.setResizable(false);
		this.setSize(310,65);
		this.setVisible(true);
		this.pack();
	}
	
	/**
	 * Methode, die auf Aktionen auf ihr zugeordneter GUI-Elemente reagiert und ausgeführt wird
	 * @param e Ereignis, das eingetroffen ist und 
	 */
	public void actionPerformed(ActionEvent a)
	{
		try
		{
			//Speicherung der Suchpfades des Look-and-Feels
			String plaf = "unknown";
			
			//Überprüfung, welches GUI-Element, die Aktion ausgelöst hat
			//und entsprechende Zwischenspeicherung des Suchpfades, in dem das Look-and-Feel liegt
		    if (a.getActionCommand().equals("Metal")) {
		    	plaf = "javax.swing.plaf.metal.MetalLookAndFeel";}
		    else if (a.getActionCommand().equals("Motif")) {
		    	plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";}
		    else if (a.getActionCommand().equals("Windows")) {
		        plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";}
		    
		    //Anwendung des Look-and-Feels und Aktualisierung aller Fenster
			UIManager.setLookAndFeel(plaf);
			SwingUtilities.updateComponentTreeUI(this);
		    for (int i=0; i < JFrame.getFrames().length; i++)
		    {
		    	SwingUtilities.updateComponentTreeUI(JFrame.getFrames()[i]);
		    }
		}
		//Abfang von Exceptions, die beim Wechseln des Look-and-Feels auftreten können
		catch (UnsupportedLookAndFeelException e) {
			System.err.println(e.toString());}
		catch (ClassNotFoundException e) {
		    System.err.println(e.toString());}
		catch (InstantiationException e) {
		      System.err.println(e.toString());}
		catch (IllegalAccessException e) {
		      System.err.println(e.toString());}
	}
}