package model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import javax.imageio.*;

public class CoatOfArmsGenerator {

	public Random r = new Random();
	public String path;
	public String[] symbolNames = { "Drache1", "Gral1", "Lilie1", "Löwe1", "Krake1", "Kralle1", "Schlüssel1", "Greif1", "Stern1", "Anker1", "Muschel1", "Adler1", "Wolf1", "Rose1", "Vogel1", "Greif2", "Helm1", "Karpfen1", "Hufeisen1", "Pferd1", "Schwan1"};
	public String[] overlayNames = {"Halb", "Halb2", "Viertel2", "Viertel", "Horizont", "Horizont2", "Diagonal", "Diagonal2", "Dreieck", "Dreieck2", "QuerViertel", "QuerViertel2", "EinzelstreifenVertikal"};
	public String[] patternNames = {"StreifenVertikal", "StreifenVertikal2", "StreifenHorizontal", "Schachbrett", "Schachbrett2", "Schachbrett3"};
	public Color[] farben = {new Color(250,250,250),//Weiß
							new Color(10,10,10),//Schwarz
							new Color(160,0,0),//Rot
							new Color(30,150,30),//Hellgrün
							new Color(0,0,160),//Dunkelblau
							new Color(200,200,0),//Gelb
							new Color(90,0,0), //Dunkelrot
							new Color(100,100,255),//Hellblau
							new Color(0,80,0),//Dunkelgrün
							new Color(100,100,100),//Grau
							new Color(90,10,90),//Lila
							new Color(200,100,0),//Orange
							new Color(10,90,90),//Türkis 
							new Color(0,0,80),//Dunkelblau
							new Color(183,51,135)};//Magenta
	public BufferedImage[] symbols = new BufferedImage[symbolNames.length];
	public BufferedImage[] shieldOverlays = new BufferedImage[overlayNames.length+1];
	public BufferedImage[] patterns = new BufferedImage[patternNames.length];
    private BufferedImage shieldshadow;
    private BufferedImage shield;
    
    private BufferedImage pattern;
    private BufferedImage overlay;
    private BufferedImage symbol1;
    private BufferedImage symbol2;
    private String symbolLayout = "ohne";
    private Color[] drawnColors = new Color[4];
    
    // Konstruktor
    public CoatOfArmsGenerator(String dPath) throws Exception {
    	
    	// datipfad bestimmen
    	this.path = dPath + "images\\";
    	
    	// bilder laden
    	for(int i=0; i<symbolNames.length; i++) {
    		symbols[i] = ImageIO.read(new File(path + "symbols\\" + symbolNames[i]+ ".png"));
    	}
    	for(int i=0; i<overlayNames.length; i++) {
    		shieldOverlays[i] = ImageIO.read(new File(path + "overlays\\" + overlayNames[i]+ ".png"));
    	}
    	for(int i=0; i<patternNames.length; i++) {
    		patterns[i] = ImageIO.read(new File(path + "patterns\\" + patternNames[i]+ ".png"));
    	}
    	shield = ImageIO.read(new File(path + "others\\" + "Schild.png"));
    	shieldshadow = ImageIO.read(new File(path + "others\\" + "SchildSchatten.png"));
    }
    
    public BufferedImage generateCoatOfArms() {
    	
    	//Schritt 0:
    	//Erstes Symbol auswuerfeln
    	symbol1 =symbols[r.nextInt(symbols.length)];
    	
    	// Schritt 1:
        // Auswürfeln des Overlays
        
        // Zufallszahl zwischen 0 und 99
        int ws = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Overlay auswählen
        String overlayName = "einfarbig";
        
        // 35% einfarbig
        if(ws < 35) { overlayName = "einfarbig"; }
        
        // 5% diagonal 1
        else if(ws < 0) { overlayName = "diagonal1"; }
        
        // 5% diagonal 2
        else if(ws < 0) { overlayName = "diagonal2"; }
        
        // 5% dreick 1
        else if(ws < 0) { overlayName = "dreieck1"; }
        
        // 5% dreick 2
        else if(ws < 0) { overlayName = "dreieck2"; }
        
        // 5% halb 1
        else if(ws < 0) { overlayName = "halb1"; }
        
        // 5% halb 2
        else if(ws < 0) { overlayName = "halb2"; }
        
        // 5% horizontal 1
        else if(ws < 80) { overlayName = "horizontal1"; }
        
        // 5% horizontal 2
        else if(ws < 90) { overlayName = "horizontal2"; }
        
        // 5% quer viertel 1
        else if(ws < 0) { overlayName = "querviertel1"; }
        
        // 5% quer viertel 2
        else if(ws < 0) { overlayName = "querviertel2"; }
        
        // 5% viertel 1
        else if(ws < 0) { overlayName = "viertel1"; }
        
        // 5% viertel 2
        else if(ws < 0) { overlayName = "viertel2"; }
        
        // 5% viertel 2
        else if(ws < 100) { overlayName = "einzelstreifenVertikal"; }
        
        switch(overlayName) {
        
	       // --------------- Wappen ohne Overlay ---------------
        	case "einfarbig":
        		
            	overlay = null;
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                int wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout auswählen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 0) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 21) { symbolLayout = "einfach";}
                
                // 19%
                else if(wsp < 40) { symbolLayout = "dreieck"; }
                
                // 12%
                else if(wsp < 52) { symbolLayout = "dreiVert"; }
                
                // 12%
                else if(wsp < 64) { symbolLayout = "zweiGleichVert"; }
                
                // 12%
                else if(wsp < 76) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 12%
                else if(wsp < 88) { symbolLayout = "zweiGespiegelt"; }
                
                // 12%
                else if(wsp < 0) { symbolLayout = "dreiVertAlt"; }
        		break;
        		
        	       // --------------- Geteilte Wappen mit Overlay rechts ---------------
            case "halb1":
            	
            	overlay = shieldOverlays[0];
            	pattern = choosePattern(60, false, false);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geteilte Wappen mit Overlay links ---------------
            case "halb2":
            	
            	overlay = shieldOverlays[1];
            	pattern = choosePattern(60, false, false);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geviertelte Wappen mit Overlay oben-rechts und unten-links ---------------
            case "viertel1":
            	
            	overlay = shieldOverlays[2];
            	pattern = choosePattern(60, false, false);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geviertelte Wappen mit Overlay oben-links und unten-rechts ---------------
            case "viertel2":
            	
            	overlay = shieldOverlays[3];
            	pattern = choosePattern(60, false, false);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geteilte Wappen mit Overlay oben ---------------
            case "horizontal1":
            	
            	overlay = shieldOverlays[4];
            	pattern = choosePattern(40, true, true);
            	
            	// Je nach Wahrscheinlichkeit ein Smbollayout auswählen
                symbolLayout = "ohne";
                
                // Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 5%
                else if(wsp < 7) { symbolLayout = "einfach";}
                
                // 20%
                else if(wsp < 27) { symbolLayout = "einfachOben";}
                
                // 20%
                else if(wsp < 47) { symbolLayout = "zweiGleichOben";}
                
                // 20%
                else if(wsp < 67) { symbolLayout = "zweiGleichVert"; }
                
                // 20%
                else if(wsp < 87) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 13%
                else if(wsp < 100) {
                	symbolLayout = "zweiInvVert";
                	pattern = null;
                }
                break;
            	
            // --------------- Geteilte Wappen mit Overlay unten ---------------
            case "horizontal2":
            	
            	overlay = shieldOverlays[5];
            	pattern = choosePattern(40, false, false);
            	
            	// Je nach Wahrscheinlichkeit ein Smbollayout auswählen
                symbolLayout = "ohne";
                
                // Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 5%
                else if(wsp < 7) { symbolLayout = "einfach";}
                
                // 20%
                else if(wsp < 27) { symbolLayout = "einfachUnten";}
                
                // 20%
                else if(wsp < 47) { symbolLayout = "zweiGleichUnten";}
                
                // 20%
                else if(wsp < 67) { symbolLayout = "zweiGleichVert"; }
                
                // 20%
                else if(wsp < 87) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 13%
                else if(wsp < 100) {
                	symbolLayout = "zweiInvVert2";
                	pattern = null;
                }
            	break;
            	
            // --------------- Geteilte Wappen mit Overlay unten-rechts ---------------
            case "diagonal1":
            	
            	overlay = shieldOverlays[6];
            	pattern = choosePattern(60, true, true);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geteilte Wappen mit Overlay unten-links ---------------
            case "diagonal2":
            	
            	overlay = shieldOverlays[7];
            	pattern = choosePattern(60, true, true);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Wappen mit Overlay mit dreieckiger Aussparung oben ---------------
            case "dreieck1":
            	
            	overlay = shieldOverlays[8];
            	pattern = choosePattern(60, true, true);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Wappen mit Dreiecksoverlay oben ---------------
            case "dreieck2":
            	
            	overlay = shieldOverlays[9];
            	pattern = choosePattern(60, true, false);
            	
            	symbolLayout = "ohne";

            	break;
            	
            // --------------- Geviertelte Wappen mit Overlay oben und unten ---------------
            case "querviertel1":
            	
            	overlay = shieldOverlays[10];
            	pattern = choosePattern(100, true, true);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Geviertelte Wappen mit Overlay links und rechts ---------------
            case "querviertel2":
            	
            	overlay = shieldOverlays[11];
            	pattern = choosePattern(100, true, true);
            	
            	symbolLayout = "ohne";
            	break;
            	
            // --------------- Ein großer vertikaler Streifen in der Mitte ---------------
            case "einzelstreifenVertikal":
        		
            	overlay = shieldOverlays[12];
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout auswählen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 0) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 21) { symbolLayout = "einfach";}
                
                // 19%
                else if(wsp < 40) { symbolLayout = "dreieck"; }
                
                // 12%
                else if(wsp < 52) { symbolLayout = "dreiVert"; }
                
                // 12%
                else if(wsp < 100) { symbolLayout = "dreiVertAlt"; }
        		break;
        		
           
            default:
        	   System.out.println("OVERLAY_DEFAULT_CASE");
        	   symbolLayout = "ohne";
        	   break;
            }
        pickColors();
        BufferedImage coatOfArms = drawCoatOfArms(pattern, overlay, symbol1, symbol2, symbolLayout, drawnColors);
        
    	return coatOfArms;
    }
    
    private BufferedImage choosePattern(int wspBlanc, boolean symetricHoriz, boolean horizontalHalfOnly){
    	
    	// Schritt 1:
    	// Auswürfeln des Musters
    	
    	// Zufallszahl zwischen 0 und 99
        int wsp = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Muster auswählen
        String muster = "ohne";
        
        int wspRest =  (100 - wspBlanc)/3;
        
        // 60% ohne muster
        if(wsp < wspBlanc) { muster = "ohne"; }
        
        // 15% streifen 1
        else if(wsp < wspBlanc + wspRest) { 
        	
            if(symetricHoriz){
            	muster = "streifenVert1";
            } else{
            	muster = "streifenVert2";
            }
        									}
        
        // 15% streifen 2
        else if(wsp < wspBlanc + 2*wspRest) { muster = "streifen1"; }
        
        // 10% schachbrettmuster
        else if(wsp < 100) {
        
        if(symetricHoriz && horizontalHalfOnly){
        	muster = "schachbrett3";
        } else if (symetricHoriz){
        	muster = "schachbrett1";
        } else { muster = "schachbrett2"; }
        					}
        
        
        // Schritt 2:
        // Muster einfügen und einfärben
        
        BufferedImage shieldPattern = null;
        switch(muster){
        
        case "ohne":
        	break;
        	
        case "streifenVert1":
        	shieldPattern = patterns[0];
        	break;
        	
        case "streifenVert2":
        	shieldPattern = patterns[1];
        	break;
        	
        case "streifen1":
        	shieldPattern = patterns[2];
        	break;
        	
        case "schachbrett1":
        	shieldPattern = patterns[3];
        	break;
        	
        case "schachbrett2":
        	shieldPattern = patterns[4];
        	break;
        	
        case "schachbrett3":
        	shieldPattern = patterns[5];
        	break;
        	
        default:
        	break;
        }
    	
    	return shieldPattern;
    }
    
    //Wählt ein Set mit vier zusammepassenden Farben aus
    private void pickColors() {
    	
    	// erste Farbe
    	drawnColors[0] = farben[r.nextInt(farben.length)];
    	
    	// zweite Farbe
    	drawnColors[1] = farben[r.nextInt(farben.length)];
    	while(drawnColors[1] == drawnColors[0]) {
    		drawnColors[1] = farben[r.nextInt(farben.length)];
    	}
    	
    	// dritte Farbe
    	drawnColors[2] = farben[r.nextInt(farben.length)];
    	while(drawnColors[2] == drawnColors[0] || drawnColors[2] == drawnColors[3]) {
    		drawnColors[2] = farben[r.nextInt(farben.length)];
    	}
    	
    	// viete Farbe
    	drawnColors[3] = farben[r.nextInt(farben.length)];
    	while(drawnColors[3] == drawnColors[0] || drawnColors[3] == drawnColors[1] || drawnColors[3] == drawnColors[2]) {
    		drawnColors[3] = farben[r.nextInt(farben.length)];
    	}
    }
    
    public BufferedImage drawCoatOfArms(BufferedImage pattern, BufferedImage overlay,BufferedImage symbol1, BufferedImage symbol2, String symbolLayout, Color[] drawnColors){
    	
        // Wappen initialisieren
        BufferedImage drawnCoatOfArms = new BufferedImage(shield.getWidth(), shield.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = drawnCoatOfArms.getGraphics();
        
    	// Schildbasis einfaerben und hinzufuegen
        shield = dye(shield, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        g.drawImage(shield, 0, 0, null);
        
    	// Muster einfaerben und hinzufuegen
        if(pattern != null) {
            pattern = dye(pattern, new Color(drawnColors[1].getRed(), drawnColors[1].getGreen(), drawnColors[1].getBlue(), 255));
            g.drawImage(pattern, 0, 0, null);
        }
        
    	// Overlay einfaerben und hinzufuegen
        if(overlay != null) {
        	overlay = dye(overlay, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
            g.drawImage(overlay, 0, 0, null);
        }
        
        //Symbole einfaerben und hinzufuegen
    	symbol1 = dye(symbol1, new Color(drawnColors[3].getRed(), drawnColors[3].getGreen(), drawnColors[3].getBlue(), 255));
    	
    	if(symbol2 != null) {
        	symbol2 = dye(symbol2, new Color(drawnColors[3].getRed(), drawnColors[3].getGreen(), drawnColors[3].getBlue(), 255));
    	}
        
        switch(symbolLayout) {
        
        case "einfach":
        	g.drawImage(symbol1, 78, 124, null);
        	break;
        	
        case "einfachOben":
        	g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	break;
        	
        case "einfachUnten":
        	g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "dreieck":
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 50, 90, null);
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 300, 90, null);
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 176, 350, null);
        	break;
        	
        case "dreiVert":
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 185, 35, null);
    		if(r.nextBoolean()){
        		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 185, 235, null);
    		} else {
        		g.drawImage(flipVertical(symbol1).getScaledInstance(180, 180, 2), 185, 235, null);
    		}
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 185, 435, null);
        	break;
        	
        case "zweiGleichVert":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(250, 250, 2), 151, 55, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	}
    		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "zweiGleichOben":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(200, 200, 2), 50, 90, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 50, 90, null);
        	}
        	if(r.nextBoolean()){
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 300, 90, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 300, 90, null);
        	}
        	break;
        
        case "zweiGleichUnten":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(180, 180, 2), 70, 340, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 70, 340, null);
        	}
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 300, 340, null);
        	break;
        	
        case "zweiVerschVert":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(250, 250, 2), 151, 55, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	}
    		g.drawImage(symbol2.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "zweiInvVert":
        	symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(250, 250, 2), 151, 55, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	}
    		symbol1 = dye(symbol1, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
    		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "zweiInvVert2":
        	symbol1 = dye(symbol1, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(250, 250, 2), 151, 55, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	}
    		symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
    		g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        
        default:
        	break;
        }
    	
        //System.out.println("Generating: " + symbolLayout);
        //Mit 50% Wahrscheinlichkeit das ganze Wappe spiegeln
        if(r.nextInt(2)>0){
    		g.drawImage(shieldshadow, 0, 0, null);
        	return drawnCoatOfArms;
        } else{
       		g.drawImage(flipVertical(shieldshadow), 0, 0, null);
        	return flipVertical(drawnCoatOfArms);
        }

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
    
    //Spiegelt ein Bild an der vertikalen Achse
    public BufferedImage flipVertical(BufferedImage src){
        AffineTransform tx=AffineTransform.getScaleInstance(-1.0,1.0);  //scaling
        tx.translate(-src.getWidth(),0);  //translating
        AffineTransformOp tr=new AffineTransformOp(tx,null);  //transforming
        
        return tr.filter(src, null);  //filtering
       }
}
