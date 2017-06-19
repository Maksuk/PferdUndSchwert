package model;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;
import javax.imageio.*;

public class CoatOfArmsGenerator {
	
	public Random r = new Random();
	public String path;
	public String[] symbolNames = {"Pferd2b", "Stern2b", "Stern1b", "Rad1b"};
	public String[] overlayNames = {"Halb", "Halb2", "Viertel2", "Viertel", "Horizont", "Horizont2", "Diagonal", "Diagonal2", "Dreieck", "Dreieck2", "QuerViertel", "QuerViertel2"};
	public Color[] farben = {new Color(240,240,240), new Color(30,30,30), new Color(160,0,0), new Color(30,150,30), new Color(0,0,160), new Color(200,200,0), new Color(90,0,0), 
							 new Color(100,100,255), new Color(0,80,0), new Color(100,100,100), new Color(90,10,90), new Color(200,100,0), new Color(10,90,90), new Color(0,0,80)};
	public BufferedImage[] symbols = new BufferedImage[symbolNames.length+1];
	public BufferedImage[] shieldOverlays = new BufferedImage[overlayNames.length+1];
    private BufferedImage schildschatten;
    private BufferedImage schild;
    // Konstruktor
    public CoatOfArmsGenerator(String dPath) throws Exception {
    	
    	// datipfad bestimmen
    	this.path = dPath + "images\\";
    	
    	// bilder laden
    	for(int i=0; i<symbolNames.length; i++) {
    		symbols[i] = ImageIO.read(new File(path + symbolNames[i]+ ".png"));
    	}
    	for(int i=0; i<overlayNames.length; i++) {
    		shieldOverlays[i] = ImageIO.read(new File(path + "Schild" + overlayNames[i]+ ".png"));
    	}
    	schild = ImageIO.read(new File(path + "Schild2.png"));
    	schildschatten = ImageIO.read(new File(path + "SchildS2.png"));
    }
    
    // neues Wappen generieren
    public BufferedImage GenerateCoatOfArms() throws IOException{
    	
        // Wappen initialisieren
        BufferedImage combined = new BufferedImage(schildschatten.getWidth(), schildschatten.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();
    	
    	// Schild einfaerben
    	Color schildFarbe = farben[r.nextInt(farben.length)];
        schild = dye(schild, new Color(schildFarbe.getRed(), schildFarbe.getGreen(), schildFarbe.getBlue(), 255));
        g.drawImage(schild, 0, 0, null);
    	
        // Overlay auswuerfeln
        BufferedImage schildOverlay = shieldOverlays[r.nextInt(shieldOverlays.length)];
    	Color overlayFarbe = farben[r.nextInt(farben.length)];
        if(schildOverlay != null) {
        
        	// Overlay einfaerben
        	while(overlayFarbe.equals(schildFarbe)) {
        		overlayFarbe = farben[r.nextInt(farben.length)];
        	}
        	schildOverlay = dye(schildOverlay, new Color(overlayFarbe.getRed(), overlayFarbe.getGreen(), overlayFarbe.getBlue(), 255));
        	g.drawImage(schildOverlay, 0, 0, null);
        
        }
        
    	// Zeichen auswuerfeln
        BufferedImage zeichen = symbols[r.nextInt(symbols.length)];
        Color zeichenFarbe = farben[r.nextInt(farben.length)];
        if(zeichen != null) {
        
        	// Zeichen einfaerben
        	while(zeichenFarbe.equals(schildFarbe) || zeichenFarbe.equals(overlayFarbe)) {
        		zeichenFarbe = farben[r.nextInt(farben.length)];
        	}
        	zeichen = dye(zeichen, new Color(zeichenFarbe.getRed(), zeichenFarbe.getGreen(), zeichenFarbe.getBlue(), 120));
        	g.drawImage(zeichen, 150, 195, null);
        }
        
        // Schildschatten hinzufügen
        g.drawImage(schildschatten, 0, 0, null);
        
		return combined;
    }

    
    // faerbt ein Bild in einer Farbe ein
    private static BufferedImage dye(BufferedImage image, Color color) {
    	
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        
        return dyed;
    }
}