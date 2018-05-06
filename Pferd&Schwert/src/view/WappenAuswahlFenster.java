package view;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.FensterManager;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class WappenAuswahlFenster extends JFrame {

	public WappenAuswahlFenster(BufferedImage[] wappen, FensterManager m) {
		//Fenster erzeugen
		super("Wappenauswahl");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		
		//Panels erzeugen
		JPanel grundPanel = new JPanel(new BorderLayout());
		JPanel wappenGrundPanel = new JPanel(new BorderLayout());
		JPanel wappenGrundPanel2 = new JPanel(new BorderLayout());
		JPanel wappenGrundPanel3 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel = new JPanel(new BorderLayout());
		JPanel wappenBildPanel2 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel3 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel4 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel5 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel6 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel7 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel8 = new JPanel(new BorderLayout());
		JPanel wappenBildPanel9 = new JPanel(new BorderLayout());
		
		// Panelfarben festlegen
		grundPanel.setBackground(new Color(200,200,255));
		wappenGrundPanel.setBackground(new Color(200,200,255));
		wappenGrundPanel2.setBackground(new Color(200,200,255));
		wappenGrundPanel3.setBackground(new Color(200,200,255));
		wappenBildPanel.setBackground(new Color(200,200,255));
		wappenBildPanel2.setBackground(new Color(200,200,255));
		wappenBildPanel3.setBackground(new Color(200,200,255));
		wappenBildPanel4.setBackground(new Color(200,200,255));
		wappenBildPanel5.setBackground(new Color(200,200,255));
		wappenBildPanel6.setBackground(new Color(200,200,255));
		wappenBildPanel7.setBackground(new Color(200,200,255));
		wappenBildPanel8.setBackground(new Color(200,200,255));
		wappenBildPanel9.setBackground(new Color(200,200,255));

		
		// Raender festlegen
		grundPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		wappenGrundPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		wappenGrundPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		wappenGrundPanel3.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
		

		wappenGrundPanel.add(wappenBildPanel, BorderLayout.EAST);
		wappenGrundPanel.add(wappenBildPanel2, BorderLayout.CENTER);
		wappenGrundPanel.add(wappenBildPanel3, BorderLayout.WEST);
		
		wappenGrundPanel2.add(wappenBildPanel4, BorderLayout.EAST);
		wappenGrundPanel2.add(wappenBildPanel5, BorderLayout.CENTER);
		wappenGrundPanel2.add(wappenBildPanel6, BorderLayout.WEST);
		
		wappenGrundPanel3.add(wappenBildPanel7, BorderLayout.EAST);
		wappenGrundPanel3.add(wappenBildPanel8, BorderLayout.CENTER);
		wappenGrundPanel3.add(wappenBildPanel9, BorderLayout.WEST);

		grundPanel.add(wappenGrundPanel, BorderLayout.NORTH);
		grundPanel.add(wappenGrundPanel2, BorderLayout.CENTER);
		grundPanel.add(wappenGrundPanel3, BorderLayout.SOUTH);

	    getContentPane().add(grundPanel);
	    wappenBildPanel.add(new JLabel(new ImageIcon(wappen[0].getScaledInstance(184, 227, 2))));
	    wappenBildPanel2.add(new JLabel(new ImageIcon(wappen[1].getScaledInstance(184, 227, 2))));
	    wappenBildPanel3.add(new JLabel(new ImageIcon(wappen[2].getScaledInstance(184, 227, 2))));
	    wappenBildPanel4.add(new JLabel(new ImageIcon(wappen[3].getScaledInstance(184, 227, 2))));
	    wappenBildPanel5.add(new JLabel(new ImageIcon(wappen[4].getScaledInstance(184, 227, 2))));
	    wappenBildPanel6.add(new JLabel(new ImageIcon(wappen[5].getScaledInstance(184, 227, 2))));
	    wappenBildPanel7.add(new JLabel(new ImageIcon(wappen[6].getScaledInstance(184, 227, 2))));
	    wappenBildPanel8.add(new JLabel(new ImageIcon(wappen[7].getScaledInstance(184, 227, 2))));
	    wappenBildPanel9.add(new JLabel(new ImageIcon(wappen[8].getScaledInstance(184, 227, 2))));
	    

	    
		
		// Fenstereinstellungen
		setLocation(1000, 0);
		setSize(800, 1000);
		setVisible(true);
		getContentPane().setBackground(new Color(200,200,255));
	}
}