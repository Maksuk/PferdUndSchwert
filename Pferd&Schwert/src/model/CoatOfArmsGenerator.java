package model;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;
import javax.imageio.*;

public class CoatOfArmsGenerator {
	
	public Random r = new Random();
	public BufferedImage[] symbols = new BufferedImage[4];
	public BufferedImage[] schieldOverlays = new BufferedImage[12];
    private BufferedImage schildschatten = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildS2.png"));
    private BufferedImage schild = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\Schild2.png"));
    
    public CoatOfArmsGenerator() throws Exception {
    	

        symbols[0] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\Pferd2b.png"));
        symbols[1] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\Stern2b.png"));
        symbols[2] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\Stern1b.png"));
        symbols[3] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\Rad1b.png"));
        
        schieldOverlays[0] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildHalb.png"));
        schieldOverlays[1] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildHalb2.png"));
        schieldOverlays[2] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildViertel2.png"));
        schieldOverlays[3] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildViertel.png"));
        schieldOverlays[4] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildHorizont.png"));
        schieldOverlays[5] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildHorizont2.png"));
        schieldOverlays[6] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildDiagonal.png"));
        schieldOverlays[7] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildDiagonal2.png"));
        schieldOverlays[8] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildDreieck.png"));
        schieldOverlays[9] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildDreieck2.png"));
        schieldOverlays[10] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildQuerViertel.png"));
        schieldOverlays[11] = ImageIO.read(new File(".\\Pferd&Schwert\\resources\\images\\SchildQuerViertel2.png"));  
    }
    
    public BufferedImage GenerateCoatOfArms() throws IOException{
        BufferedImage wappen = symbols[r.nextInt(4)];
        BufferedImage schildOverlay = schieldOverlays[r.nextInt(12)];
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