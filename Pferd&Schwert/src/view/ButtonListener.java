package view;
import javax.swing.*;

import controller.FensterManager;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ButtonListener implements ActionListener
{
  private JTextField textFieldN;
  private JTextField textFieldS;
  private JTextArea textArea;
  private JPanel imagePanel;
  private int e;
  private FensterManager m;
  private boolean maennlich = true;
  private boolean kueste = true;
  private boolean fluss = true;
  private boolean gebirge = true;

  // Konstruktor
  public ButtonListener(JTextField tfn, JTextField tfs, JPanel pn, FensterManager ma)
  {
    textFieldN = tfn;
    textFieldS = tfs;
    imagePanel = pn;
    m = ma;
  }

 // Wenn ein Knopf gedrueckt wurde
  public void actionPerformed(ActionEvent ae) {
	  
	  // "Neu" Knopf:
	  if(ae.getActionCommand().equals("Neu")) {
		  
		  // neues Wappen
		  imagePanel.removeAll();
		  imagePanel.add(new JLabel(new ImageIcon(m.cGenerator.generateCoatOfArms())));
		  imagePanel.validate();
		  imagePanel.repaint();
	  
		 // neuer Name
		  if(maennlich) {
			  m.nGenerator.gesamtnameGenerieren(true);
			  textFieldN.setText(m.nGenerator.Vorname + " " + m.nGenerator.Ehrenname);
		  }
		  else {
			  m.nGenerator.gesamtnameGenerieren(false);
			  textFieldN.setText(m.nGenerator.Vorname + " " + m.nGenerator.Ehrenname);
		  }
		  
		  // neue Stadt
		  m.nGenerator.stadtnamenGenerieren(kueste,fluss,gebirge);
		  textFieldS.setText(m.nGenerator.Stadt);
	  }
	  
	  // Radiobuttons:
	  if(ae.getActionCommand().equals("männlich")) {
		  maennlich = true;
	  }
	  if(ae.getActionCommand().equals("weiblich")) {
		  maennlich = false;
	  }
	  
	  // Checkboxen:
	  if(ae.getActionCommand().equals("küste")) {
		  kueste = !kueste;
	  }
	  if(ae.getActionCommand().equals("fluss")) {
		  fluss = !fluss;
	  }
	  if(ae.getActionCommand().equals("gebirge")) {
		  gebirge = !gebirge;
	  }
  }
}