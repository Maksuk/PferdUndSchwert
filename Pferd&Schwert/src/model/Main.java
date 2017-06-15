package model;

import controller.FensterManager;
import view.Fenster;

public class Main {

	public static void main(String[] args) {
		
		Generator g = new Generator();
		FensterManager m = new FensterManager(g);
		g.arraysFuellen();
		
		Fenster f = new Fenster(g.Vorname + " " + g.Ehrenname + " von " + g.Stadt, m);
		
		//for(int i=0;i<20;i++) {
		
		//System.out.print(g.maennernamenGenerieren());
		//System.out.println(" " + g.ehrennamenGenerieren());
		//}
	}
}
