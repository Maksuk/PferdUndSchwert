package model;

import java.awt.image.BufferedImage;
import controller.FensterManager;
import view.Fenster;
import view.WappenAuswahlFenster;

public class Main {

	public static void main(String[] args) throws Exception {
		
		String path = "..\\Pferd&Schwert\\resources\\";
		
		NamesGenerator g = new NamesGenerator(path);
		CoatOfArmsGenerator c = new CoatOfArmsGenerator(path);
		FensterManager m = new FensterManager(g,c);
		g.arraysFuellen();
		BufferedImage wappen = c.GenerateCoatOfArms();
		BufferedImage[] wappenAuswahl = new BufferedImage[9];
		for(int i=0; i<9; i++){
			wappenAuswahl[i] = c.GenerateCoatOfArms();
		}
		
		Fenster f = new Fenster(g.Vorname + " " + g.Ehrenname, g.Stadt, wappen, m);
		WappenAuswahlFenster w = new WappenAuswahlFenster(wappenAuswahl, m);
	}
}
