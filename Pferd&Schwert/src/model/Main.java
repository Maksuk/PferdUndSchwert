package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import controller.FensterManager;
import view.Fenster;

public class Main {

	public static void main(String[] args) throws Exception {
		
		NamesGenerator g = new NamesGenerator();
		CoatOfArmsGenerator c = new CoatOfArmsGenerator();
		FensterManager m = new FensterManager(g,c);
		g.arraysFuellen();
		BufferedImage wappen = c.GenerateCoatOfArms();
		
		Fenster f = new Fenster(g.Vorname + " " + g.Ehrenname + " von " + g.Stadt, wappen, m);
		
	    SwingUtilities.invokeLater(new Runnable() {
	    	
	    	public void run() {
	    		try {
	    			new CoatOfArmsGenerator();
	            }
	            catch (Exception e) {
	            	e.printStackTrace();
	            }
	        }
	     });
	}
}
