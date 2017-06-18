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
	public BufferedImage[] symbols = new BufferedImage[symbolNames.length];
	public BufferedImage[] shieldOverlays = new BufferedImage[overlayNames.length];
    private BufferedImage schildschatten;
    private BufferedImage schild;
    
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
        BufferedImage wappen = symbols[r.nextInt(4)];
        BufferedImage schildOverlay = shieldOverlays[r.nextInt(12)];
        wappen = dye(wappen, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 120));
        
        schild = dye(schild, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 255));
        schildOverlay = dye(schildOverlay, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 255));
        BufferedImage combined = new BufferedImage(schildschatten.getWidth(), schildschatten.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = combined.getGraphics();
        g.drawImage(schild, 0, 0, null);
        g.drawImage(schildOverlay, 0, 0, null);
        g.drawImage(wappen, 145, 145, null);
//        g.drawImage(image3, 120, 120, null);
        g.drawImage(schildschatten, 0, 0, null);
        
		return combined;
    }

    
    // bild einfaerben
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