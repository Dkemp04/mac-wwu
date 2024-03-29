package Test;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FrameTest extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -2378851118179445922L;
	private static final String[] MONTHS = {
    "Januar",    "Februar", "M�rz",     "April",
    "Mai",       "Juni",    "Juli",     "August",
    "September", "Oktober", "November", "Dezember"
  };

  public FrameTest()
  {
    super("Mein erstes Swing-Programm");
    //Panel zur Namenseingabe hinzuf�gen
    JPanel namePanel = new JPanel();
    JLabel label = new JLabel(
      "Name:",
      new ImageIcon("triblue.gif"), 
      SwingConstants.LEFT
    );
    namePanel.add(label);
    JTextField tf = new JTextField(30);
    tf.setToolTipText("Geben Sie ihren Namen ein");
    namePanel.add(tf);
    namePanel.setBorder(BorderFactory.createEtchedBorder()); 
    getContentPane().add(namePanel, BorderLayout.NORTH); 
    //Monatsliste hinzuf�gen
    JList list = new JList(MONTHS);
    list.setToolTipText("W�hlen Sie ihren Geburtsmonat aus");
    getContentPane().add(new JScrollPane(list), BorderLayout.CENTER); 
    //Panel mit den Buttons hinzuf�gen
    JPanel buttonPanel = new JPanel();
    JButton button1 = new JButton("Metal");
    button1.addActionListener(this);
    button1.setToolTipText("Metal-Look-and-Feel aktivieren");
    buttonPanel.add(button1);
    JButton button2 = new JButton("Motif");
    button2.addActionListener(this);
    button2.setToolTipText("Motif-Look-and-Feel aktivieren");
    buttonPanel.add(button2);
    JButton button3 = new JButton("Windows");
    button3.addActionListener(this);
    button3.setToolTipText("Windows-Look-and-Feel aktivieren");
    buttonPanel.add(button3);
    buttonPanel.setBorder(BorderFactory.createEtchedBorder()); 
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    //Windows-Listener
    addWindowListener(new WindowClosingAdapter(true));
  }

  public void actionPerformed(ActionEvent event)
  {
    String cmd = event.getActionCommand();
    try {
      //PLAF-Klasse ausw�hlen 
      String plaf = "unknown";
      if (cmd.equals("Metal")) {
        plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
      } else if (cmd.equals("Motif")) {
        plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
      } else if (cmd.equals("Windows")) {
        plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
      }
      //LAF umschalten
      UIManager.setLookAndFeel(plaf);
      SwingUtilities.updateComponentTreeUI(this);
    } catch (UnsupportedLookAndFeelException e) {
      System.err.println(e.toString());
    } catch (ClassNotFoundException e) {
      System.err.println(e.toString());
    } catch (InstantiationException e) {
      System.err.println(e.toString());
    } catch (IllegalAccessException e) {
      System.err.println(e.toString());
    }
  }

  public static void main(String[] args)
  {
	FrameTest frame = new FrameTest();
    frame.setLocation(100, 100);
    frame.pack();
    frame.setVisible(true);
  }
}