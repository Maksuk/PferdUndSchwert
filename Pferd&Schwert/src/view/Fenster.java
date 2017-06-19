package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.FensterManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Fenster extends JFrame {

	public Fenster(String standardname, BufferedImage wappen, FensterManager m) {
		//Fenster erzeugen
		super("Pferd & Schwert");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		
		//Panels erzeugen
		JPanel grundPanel = new JPanel(new BorderLayout());
		JPanel namensGrundPanel = new JPanel(new BorderLayout());
		JPanel namensPanel = new JPanel(new BorderLayout());
		JPanel nObenPanel = new JPanel(new BorderLayout());
		JPanel nUntenPanel = new JPanel(new BorderLayout());
		JPanel nlinksPanel = new JPanel(new BorderLayout());
		JPanel nrechtsPanel = new JPanel(new BorderLayout());
		JPanel sLinksPanel = new JPanel(new BorderLayout());
		JPanel sMittePanel = new JPanel(new BorderLayout());
		JPanel sRechtsPanel = new JPanel(new BorderLayout());
		JPanel wappenGrundPanel = new JPanel(new BorderLayout());
		JPanel wappenTextPanel = new JPanel(new BorderLayout());
		JPanel wappenBildPanel = new JPanel(new BorderLayout());
		
		// Panelfarben festlegen
		grundPanel.setBackground(new Color(200,200,255));
		namensGrundPanel.setBackground(new Color(200,200,255));
		namensPanel.setBackground(new Color(200,200,255));
		nObenPanel.setBackground(new Color(200,200,255));
		nUntenPanel.setBackground(new Color(200,200,255));
		nlinksPanel.setBackground(new Color(200,200,255));
		nrechtsPanel.setBackground(new Color(200,200,255));
		sLinksPanel.setBackground(new Color(200,200,255));
		sMittePanel.setBackground(new Color(200,200,255));
		sRechtsPanel.setBackground(new Color(200,200,255));
		wappenGrundPanel.setBackground(new Color(200,200,255));
		wappenTextPanel.setBackground(new Color(200,200,255));
		wappenBildPanel.setBackground(Color.DARK_GRAY);
		
		// Raender festlegen
		grundPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		namensPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 50, 5));
		nrechtsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		nlinksPanel.setBorder(BorderFactory.createEmptyBorder(0, 120, 0, 0));
		sRechtsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		wappenGrundPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		
		// Fenster zusammenpuzzeln
		nObenPanel.add(nlinksPanel, BorderLayout.WEST);
		nObenPanel.add(nrechtsPanel, BorderLayout.EAST);
		namensPanel.add(nObenPanel, BorderLayout.NORTH);
		namensPanel.add(nUntenPanel, BorderLayout.SOUTH);
		namensGrundPanel.add(namensPanel, BorderLayout.CENTER);
		wappenGrundPanel.add(wappenTextPanel, BorderLayout.NORTH);
		wappenGrundPanel.add(wappenBildPanel, BorderLayout.CENTER);
		grundPanel.add(namensGrundPanel, BorderLayout.CENTER);
		grundPanel.add(wappenGrundPanel, BorderLayout.NORTH);
	    getContentPane().add(grundPanel);
	    
		//Label mit Heldennamen Text
		JLabel text1 = new JLabel( "Euer Heldenname:",JLabel.CENTER);
		text1.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		namensGrundPanel.add(text1,BorderLayout.NORTH);
		
		//Textfeld für Namen
		JTextField textField = new JTextField(standardname);
		textField.setPreferredSize( new Dimension( 300, 24 ) );
		nlinksPanel.add(textField,BorderLayout.EAST);
		
		//Knopf für Neugenerierung von Namen
		JButton button = new JButton("Neu");
		button.setActionCommand("Neu");
		button.setBackground(new Color(150,150,255));
		nrechtsPanel.add(button,BorderLayout.CENTER);
		
		//Geschlechterwahl Radiobuttons
		JRadioButton maennlich = new JRadioButton("männlich");
		maennlich.setActionCommand("männlich");
	    maennlich.setSelected(true);
	    maennlich.setBackground(new Color(200,200,255));
	    maennlich.setBorder(BorderFactory.createEmptyBorder(10, 150, 0, 0));
	    JRadioButton weiblich = new JRadioButton("weiblich");
		weiblich.setActionCommand("weiblich");
		weiblich.setBackground(new Color(200,200,255));
		weiblich.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 150));
		
		ButtonGroup group = new ButtonGroup();
	    group.add(maennlich);
	    group.add(weiblich);
	    nUntenPanel.add(maennlich, BorderLayout.WEST);
	    nUntenPanel.add(weiblich, BorderLayout.EAST);
	    	
	    //Label fuer Wappen
	    JLabel text2 = new JLabel( "Euer Wappen:",JLabel.CENTER);
		text2.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		wappenTextPanel.add(text2,BorderLayout.CENTER);
	    wappenBildPanel.add(new JLabel(new ImageIcon(wappen)));
	    
	    //Button Listener
		ButtonListener l = new ButtonListener(textField, wappenBildPanel, m);
		button.addActionListener(l);
		maennlich.addActionListener(l);
		weiblich.addActionListener(l);
		
		// Fenstereinstellungen
		setLocation(200, 10);
		setSize(800, 900);
		setVisible(true);
		getContentPane().setBackground(new Color(200,200,255));
	}
}