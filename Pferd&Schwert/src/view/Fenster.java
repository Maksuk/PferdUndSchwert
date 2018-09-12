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
	
	public WappenAuswahlFenster wappenAuswahlFenster;

	public Fenster(String standardname, String standardstadt, BufferedImage wappen, FensterManager m) {
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
		JPanel stadtGrundPanel = new JPanel(new BorderLayout());
		JPanel stadtTextGrundPanel = new JPanel(new BorderLayout());
		JPanel stadtButtonGrundPanel = new JPanel(new BorderLayout());
		JPanel stadtTextfeldPanel = new JPanel(new BorderLayout());
		JPanel stadtCheckboxPanel = new JPanel(new BorderLayout());
		JPanel stadtTextfeldRandLPanel = new JPanel(new BorderLayout());
		JPanel stadtTextfeldRandRPanel = new JPanel(new BorderLayout());
		
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
		stadtGrundPanel.setBackground(new Color(200,200,255));
		stadtTextGrundPanel.setBackground(new Color(200,200,255));
		stadtButtonGrundPanel.setBackground(new Color(200,200,255));
		stadtTextfeldPanel.setBackground(new Color(200,200,255));
		stadtTextfeldPanel.setBackground(new Color(200,200,255));
		stadtTextfeldRandLPanel.setBackground(new Color(200,200,255));
		stadtTextfeldRandRPanel.setBackground(new Color(200,200,255));
		
		// Raender festlegen
		grundPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		namensPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 20, 5));
		nrechtsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		nlinksPanel.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		sRechtsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		wappenGrundPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		stadtTextfeldRandLPanel.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		stadtTextfeldRandRPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		
		// Fenster zusammenpuzzeln
		nObenPanel.add(nlinksPanel, BorderLayout.WEST);
		nObenPanel.add(nrechtsPanel, BorderLayout.EAST);
		namensPanel.add(nObenPanel, BorderLayout.NORTH);
		namensPanel.add(nUntenPanel, BorderLayout.SOUTH);
		namensGrundPanel.add(namensPanel, BorderLayout.CENTER);
		wappenGrundPanel.add(wappenTextPanel, BorderLayout.NORTH);
		wappenGrundPanel.add(wappenBildPanel, BorderLayout.CENTER);
		stadtTextGrundPanel.add(stadtTextfeldPanel, BorderLayout.CENTER);
		stadtTextGrundPanel.add(stadtTextfeldRandLPanel, BorderLayout.WEST);
		stadtTextGrundPanel.add(stadtTextfeldRandRPanel, BorderLayout.EAST);
		stadtButtonGrundPanel.add(stadtCheckboxPanel, BorderLayout.CENTER);
		stadtGrundPanel.add(stadtTextGrundPanel, BorderLayout.CENTER);
		stadtGrundPanel.add(stadtButtonGrundPanel, BorderLayout.SOUTH);
		grundPanel.add(namensGrundPanel, BorderLayout.CENTER);
		grundPanel.add(wappenGrundPanel, BorderLayout.NORTH);
		grundPanel.add(stadtGrundPanel,BorderLayout.SOUTH);
	    getContentPane().add(grundPanel);
	    
		//Label mit Heldennamen Ueberschrift
		JLabel text1 = new JLabel( "Euer Heldenname:",JLabel.CENTER);
		text1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		namensGrundPanel.add(text1,BorderLayout.NORTH);
		
		//Textfeld für Namen
		JTextField textField = new JTextField(standardname);
		textField.setPreferredSize( new Dimension( 200, 24 ) );
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
	    maennlich.setBorder(BorderFactory.createEmptyBorder(10, 170, 0, 0));
	    JRadioButton weiblich = new JRadioButton("weiblich");
		weiblich.setActionCommand("weiblich");
		weiblich.setBackground(new Color(200,200,255));
		weiblich.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 175));
		
		ButtonGroup group = new ButtonGroup();
	    group.add(maennlich);
	    group.add(weiblich);
	    nUntenPanel.add(maennlich, BorderLayout.WEST);
	    nUntenPanel.add(weiblich, BorderLayout.EAST);
	    	
	    //Label fuer Wappen
	    JLabel text2 = new JLabel( "Euer Wappen:",JLabel.CENTER);
		text2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		wappenTextPanel.add(text2,BorderLayout.CENTER);
	    wappenBildPanel.add(new JLabel(new ImageIcon(wappen)));
	    
	    //Label mit Stadt Ueberschrift
	    JLabel textS = new JLabel( "Eure Heimstadt:",JLabel.CENTER);
	    textS.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	    stadtGrundPanel.add(textS,BorderLayout.NORTH);
	    
	    //Textfeld für Stadt
	    JTextField textFieldS = new JTextField(standardstadt);
	    textFieldS.setPreferredSize( new Dimension( 200, 24 ) );
	    stadtTextfeldRandLPanel.add(textFieldS,BorderLayout.EAST);
	    
	    //Checkboxen fuer Stadtnamen
	    JCheckBox kueste = new JCheckBox("Küste");
	    kueste.setActionCommand("küste");
	    kueste.setSelected(true);
	    kueste.setBackground(new Color(200,200,255));
	    kueste.setBorder(BorderFactory.createEmptyBorder(10, 170, 0, 0));
	    JCheckBox fluss = new JCheckBox("Fluss");
	    fluss.setActionCommand("fluss");
	    fluss.setBackground(new Color(200,200,255));
	    fluss.setSelected(true);
	    fluss.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));
	    JCheckBox gebirge = new JCheckBox("Gebirge");
	    gebirge.setActionCommand("gebirge");
	    gebirge.setSelected(true);
	    gebirge.setBackground(new Color(200,200,255));
	    gebirge.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 180));
	    
	    stadtCheckboxPanel.add(kueste, BorderLayout.WEST);
	    stadtCheckboxPanel.add(fluss, BorderLayout.CENTER);
	    stadtCheckboxPanel.add(gebirge, BorderLayout.EAST);
	    
	    //Button Listener
		ButtonListener l = new ButtonListener(textField, textFieldS, wappenBildPanel, m, wappenAuswahlFenster);
		button.addActionListener(l);
		maennlich.addActionListener(l);
		weiblich.addActionListener(l);
		kueste.addActionListener(l);
		fluss.addActionListener(l);
		gebirge.addActionListener(l);
		
		// Fenstereinstellungen
		setLocation(200, 0);
		setSize(800, 1000);
		setVisible(true);
		getContentPane().setBackground(new Color(200,200,255));
	}
}