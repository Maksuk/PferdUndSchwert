package view;
import javax.swing.*;

import controller.FensterManager;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MyListener implements ActionListener
{
  private JTextField textField;
  private JTextArea textArea;
  private JPanel imagePanel;
  private int e;
  private FensterManager m;
  private boolean maennlich = true;

  public MyListener(JTextField tf, JPanel pn, FensterManager ma)
  {
    textField = tf;
    imagePanel = pn;
    m = ma;
  }

 
	  
  
  public void actionPerformed(ActionEvent ae) {
	  
	  if(ae.getActionCommand().equals("Neu")) {
		  
		  // neues Wappen
		  imagePanel.removeAll();
		  try {
			imagePanel.add(new JLabel(new ImageIcon(m.cGenerator.GenerateCoatOfArms())));
		  } 
		  catch (IOException e) {
			e.printStackTrace();
		  }
		  imagePanel.validate();
		  imagePanel.repaint();
	  
		 // neuer Name
		  if(maennlich) {
			  m.nGenerator.gesamtnameGenerieren(true);
			  textField.setText(m.nGenerator.Vorname + " " + m.nGenerator.Ehrenname + " von " + m.nGenerator.Stadt);
		  }
		  else {
			  m.nGenerator.gesamtnameGenerieren(false);
			  textField.setText(m.nGenerator.Vorname + " " + m.nGenerator.Ehrenname + " von " + m.nGenerator.Stadt);
		  }
	  }
	  
	  // radiobuttons updaten
	  if(ae.getActionCommand().equals("männlich")) {
		  maennlich = true;
	  }
	  if(ae.getActionCommand().equals("weiblich")) {
		  maennlich = false;
	  }
    
  }
}