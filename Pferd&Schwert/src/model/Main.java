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
		
		String path = ".\\Pferd&Schwert\\resources\\";
		
		NamesGenerator g = new NamesGenerator(path);
		CoatOfArmsGenerator c = new CoatOfArmsGenerator(path);
		FensterManager m = new FensterManager(g,c);
		g.arraysFuellen();
		BufferedImage wappen = c.GenerateCoatOfArms();
		
		Fenster f = new Fenster(g.Vorname + " " + g.Ehrenname + " von " + g.Stadt, wappen, m);
	}
}
