package view;
import javax.swing.*;

import controller.FensterManager;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**Verwaltet Klicks und Textfeldeingaben beim Programmstart (Ports, IPs,...)
 *  */
public class MyListener implements ActionListener
{
  private JTextField textField;
  private JTextArea textArea;
  private int e;
  private FensterManager m;
  private boolean maennlich = true;

  public MyListener(JTextField tf, FensterManager ma)
  {
    textField = tf;
    m = ma;
  }

  public void actionPerformed(ActionEvent ae) {
	  
	  if(ae.getActionCommand().equals("Neu")) {
		  if(maennlich) {
			  m.generator.gesamtnameGenerieren(true);
			  textField.setText(m.generator.Vorname + " " + m.generator.Ehrenname + " von " + m.generator.Stadt);
		  }
		  else {
			  m.generator.gesamtnameGenerieren(false);
			  textField.setText(m.generator.Vorname + " " + m.generator.Ehrenname + " von " + m.generator.Stadt);
		  }
	  }
	  if(ae.getActionCommand().equals("männlich")) {
		  maennlich = true;
	  }
	  if(ae.getActionCommand().equals("weiblich")) {
		  maennlich = false;
	  }
    
  }
}