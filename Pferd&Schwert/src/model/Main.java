package model;

import javax.swing.SwingUtilities;

import controller.FensterManager;
import view.Fenster;

public class Main {

	public static void main(String[] args) {
		
		NamesGenerator g = new NamesGenerator();
		FensterManager m = new FensterManager(g);
		g.arraysFuellen();
		
		Fenster f = new Fenster(g.Vorname + " " + g.Ehrenname + " von " + g.Stadt, m);
		
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
