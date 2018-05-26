package model;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import javax.imageio.*;

public class CoatOfArmsGeneratorOld {
	
	public Random r = new Random();
	public String path;
	public String[] symbolNames = {"Drache1", "Gral1", "Lilie1", "Löwe1", "Krake1", "Kralle1", "Schlüssel1", "Greif1", "Stern1", "Anker1", "Muschel1", "Adler1", "Wolf1"};
	public String[] overlayNames = {"Halb", "Halb2", "Viertel2", "Viertel", "Horizont", "Horizont2", "Diagonal", "Diagonal2", "Dreieck", "Dreieck2", "QuerViertel", "QuerViertel2"};
	public String[] patternNames = {"StreifenVertikal", "StreifenHorizontal"};
	public Color[] farben = {new Color(250,250,250), new Color(10,10,10), new Color(160,0,0), new Color(30,150,30), new Color(0,0,160), new Color(200,200,0), new Color(90,0,0), 
							 new Color(100,100,255), new Color(0,80,0), new Color(100,100,100), new Color(90,10,90), new Color(200,100,0), new Color(10,90,90), new Color(0,0,80)};
	public BufferedImage[] symbols = new BufferedImage[symbolNames.length+1];
	public BufferedImage[] shieldOverlays = new BufferedImage[overlayNames.length+1];
	public BufferedImage[] patterns = new BufferedImage[patternNames.length];
    private BufferedImage schildschatten;
    private BufferedImage schild;
    // Konstruktor
    public CoatOfArmsGeneratorOld(String dPath) throws Exception {
    	
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
    	schild = ImageIO.read(new File(path + "others\\" + "Schild.png"));
    	schildschatten = ImageIO.read(new File(path + "others\\" + "SchildSchatten.png"));
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
    	
        // Muster mit bestimmter Wahrscheinlichkeit einfuegen
        Color musterFarbe = farben[r.nextInt(farben.length)];
        if(r.nextInt(100) < 50) {
        	
        	// Muster auswuerfeln
        	BufferedImage muster = patterns[r.nextInt(patterns.length)];
        	
        	// Muster einfaerben
        	while(musterFarbe.equals(schildFarbe)) {
        		musterFarbe = farben[r.nextInt(farben.length)];
        	}
        	muster = dye(muster, new Color(musterFarbe.getRed(), musterFarbe.getGreen(), musterFarbe.getBlue(), 255));
        	g.drawImage(muster, 0, 0, null);
        }
        
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
        BufferedImage zeichen = symbols[r.nextInt(symbols.length-1)];
        Color zeichenFarbe = farben[r.nextInt(farben.length)];
        if(zeichen != null) {
        
        	// Zeichen einfaerben
        	while(zeichenFarbe.equals(schildFarbe) || zeichenFarbe.equals(overlayFarbe) || zeichenFarbe.equals(musterFarbe)) {
        		zeichenFarbe = farben[r.nextInt(farben.length)];
        	}
        	//Zeichenfarbe und -anzahl auswuerfeln
        	zeichen = dye(zeichen, new Color(zeichenFarbe.getRed(), zeichenFarbe.getGreen(), zeichenFarbe.getBlue(), 255));
        	int randomNumber = r.nextInt(5);
        	if(randomNumber <= 0 ){
        		g.drawImage(zeichen.getScaledInstance(200, 200, 2), 50, 90, null);
        		g.drawImage(zeichen.getScaledInstance(200, 200, 2), 300, 90, null);
        	}
        	else if(randomNumber >2 ){
            	g.drawImage(zeichen, 78, 124, null);
        	} else {
        		g.drawImage(zeichen.getScaledInstance(200, 200, 2), 50, 90, null);
        		g.drawImage(zeichen.getScaledInstance(200, 200, 2), 300, 90, null);
        		g.drawImage(zeichen.getScaledInstance(200, 200, 2), 176, 350, null);
        	}

        }
        
        // Schildschatten hinzufuegen
      //g.drawImage(schildschatten, 0, 0, null);
        
		return combined;
    }
    
    // -----------------------------------------------------------------------------------------------------------------------------------
    // ein erster Versuch die Wappen mal strukturiert zu generieren
    public BufferedImage GenerateCoatOfArmsNEW() throws IOException{
    	
        // Wappen initialisieren
        BufferedImage combined = new BufferedImage(schildschatten.getWidth(), schildschatten.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();
    	
        // Schritt 1:
        // Die Farbe des Haupthintergrundes auswürfeln
        //	- bis jetzt wird zufällig eine ausgewürfelt
        Color hauptFarbe = farben[r.nextInt(farben.length)];
        schild = dye(schild, new Color(hauptFarbe.getRed(), hauptFarbe.getGreen(), hauptFarbe.getBlue(), 255));
        g.drawImage(schild, 0, 0, null);
        
        
        // Schritt 2:
        // Auswürfeln des Overlays
        
        // Zufallszahl zwischen 0 und 99
        int ws = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Overlay auswählen
        String overlay = "einfarbig";
        
        // 40% einfarbig
        if(ws < 40) { overlay = "einfarbig"; }
        
        // 5% diagonal 1
        else if(ws < 45) { overlay = "diagonal1"; }
        
        // 5% diagonal 2
        else if(ws < 50) { overlay = "diagonal2"; }
        
        // 5% dreick 1
        else if(ws < 55) { overlay = "dreieck1"; }
        
        // 5% dreick 2
        else if(ws < 60) { overlay = "dreieck2"; }
        
        // 5% halb 1
        else if(ws < 65) { overlay = "halb1"; }
        
        // 5% halb 2
        else if(ws < 70) { overlay = "halb2"; }
        
        // 5% horizontal 1
        else if(ws < 75) { overlay = "horizontal1"; }
        
        // 5% horizontal 2
        else if(ws < 80) { overlay = "horizontal2"; }
        
        // 5% quer viertel 1
        else if(ws < 85) { overlay = "querviertel1"; }
        
        // 5% quer viertel 2
        else if(ws < 90) { overlay = "querviertel2"; }
        
        // 5% viertel 1
        else if(ws < 95) { overlay = "viertel1"; }
        
        // 5% viertel 2
        else if(ws < 100) { overlay = "viertel2"; }
        
        
        // Schritt 3:
        // Generiere ein Wappen mit Layout passend zum Overlay
        
        BufferedImage schildOverlay = null;
        Color overlayFarbe = null;
        switch(overlay){
        
        // --------------- Wappen ohne Overlay ---------------
        case "einfarbig":

        	// Schritt 3a:
        	// Overlay einfügen und einfärben entfällt beim einfarbigen Wappen
        	
        	// Schritt 3b:
        	// Auswürfeln des Musters
        	
        	// Zufallszahl zwischen 0 und 99
            int wsp = r.nextInt(100);
            
            // Je nach Wahrscheinlichkeit ein Muster auswählen
            String muster = "ohne";
            
            // 60% ohne muster
            if(wsp < 60) { muster = "ohne"; }
            
            // 20% streifen 1
            else if(wsp < 80) { muster = "streifen1";}
            
            // 20% streifen 2
            else if(wsp < 100) { muster = "streifen2"; }
            
            
            // Schritt 3c:
            // Muster einfügen und einfärben
            
            BufferedImage schildMuster = null;
            Color musterFarbe = null;
            switch(muster){
            
            case "ohne":
            	break;
            	
            case "streifen1":
            	schildMuster = patterns[0];
            	musterFarbe = addPattern(g, schildMuster, hauptFarbe, null);
            	break;
            	
            case "streifen2":
            	schildMuster = patterns[1];
            	musterFarbe = addPattern(g, schildMuster, hauptFarbe, null);
            	break;
            	
            default:
            	break;
            }
            
            // Schritt 3d:
            // Symbolanzahl auswürfeln
            
            // Zufallszahl zwischen 0 und 99
            int wsa = r.nextInt(100);
            
            // Je nach Wahrscheinlichkeit Symbollayout auswählen
            String anzahl = "einzeln";
            
            // 60% einzeln
            if(wsa < 60) { anzahl = "einzeln"; }
            
            // 20% dreieck
            else if(wsa < 80) { anzahl = "dreieck";}
            
            // 20% drei
            else if(wsa < 100) { anzahl = "drei";}
            
            
         // Schritt 3e:
            // Symbole einfügen und einfärben
            
            BufferedImage zeichen = null;
            Color zeichenFarbe = null;
            switch(anzahl){
            
            case "einzeln":
            	
            	zeichen = symbols[r.nextInt(symbols.length-1)];
            	addSymbol(g, zeichen, 400,78, 124, hauptFarbe, overlayFarbe, musterFarbe, null);
            	break;
            	
            case "dreieck":
            	
            	zeichen = symbols[r.nextInt(symbols.length-1)];
            	zeichenFarbe = addSymbol(g, zeichen, 200,50, 90, hauptFarbe, overlayFarbe, musterFarbe, null);
            	addSymbol(g, zeichen, 200,300, 90, hauptFarbe, overlayFarbe, musterFarbe, zeichenFarbe);
            	addSymbol(g, zeichen, 200, 176, 350, hauptFarbe, overlayFarbe, musterFarbe, zeichenFarbe);
            	break;
            	
            case "drei":
            	
            	zeichen = symbols[r.nextInt(symbols.length-1)];
            	zeichenFarbe = addSymbol(g, zeichen, 180,185, 35, hauptFarbe, overlayFarbe, musterFarbe, null);
            	addSymbol(g, zeichen, 180,185, 235, hauptFarbe, overlayFarbe, musterFarbe, zeichenFarbe);
            	addSymbol(g, zeichen, 180, 185, 435, hauptFarbe, overlayFarbe, musterFarbe, zeichenFarbe);
            	break;
	
            default:
            	System.out.println("SYMBOL_AMOUNT_DEFAULT_CASE");
            	break;
            }
        	
        	break;
        
        // --------------- Geteilte Wappen mit Overlay rechts ---------------
        case "halb1":
        	
        	schildOverlay = shieldOverlays[0];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geteilte Wappen mit Overlay links ---------------
        case "halb2":
        	
        	schildOverlay = shieldOverlays[1];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geviertelte Wappen mit Overlay oben-rechts und unten-links ---------------
        case "viertel1":
        	
        	schildOverlay = shieldOverlays[2];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geviertelte Wappen mit Overlay oben-links und unten-rechts ---------------
        case "viertel2":
        	
        	schildOverlay = shieldOverlays[3];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geteilte Wappen mit Overlay oben ---------------
        case "horizontal1":
        	
        	// Schritt 3a:
        	// Overlay einfügen und einfärben
        	generateHorizontal1(g, hauptFarbe);
        	break;
        	
        // --------------- Geteilte Wappen mit Overlay unten ---------------
        case "horizontal2":
        	
        	schildOverlay = shieldOverlays[5];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geteilte Wappen mit Overlay unten-rechts ---------------
        case "diagonal1":
        	
        	schildOverlay = shieldOverlays[6];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geteilte Wappen mit Overlay unten-links ---------------
        case "diagonal2":
        	
        	schildOverlay = shieldOverlays[7];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Wappen mit Overlay mit dreieckiger Aussparung oben ---------------
        case "dreieck1":
        	
        	schildOverlay = shieldOverlays[8];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Wappen mit Dreiecksoverlay oben ---------------
        case "dreieck2":
        	
        	schildOverlay = shieldOverlays[9];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geviertelte Wappen mit Overlay oben und unten ---------------
        case "querviertel1":
        	
        	schildOverlay = shieldOverlays[10];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
        	
        // --------------- Geviertelte Wappen mit Overlay links und rechts ---------------
        case "querviertel2":
        	
        	schildOverlay = shieldOverlays[11];
        	overlayFarbe = addOverlay(g, schildOverlay, hauptFarbe);
        	break;
       
        default:
    	   System.out.println("OVERLAY_DEFAULT_CASE");
    	   break;
        }
        
        return combined;
    }
    
    // --------------------------------------------------------- Generierungsmethoden für Layouts -------------------------------------------------------------------
    
    // Generiert ein geteiltes Wappen mit Overlay oben
    private void generateHorizontal1(Graphics g, Color hauptFarbe) {
    	
    	BufferedImage schildMuster = null;
    	Color musterFarbe = null;
    	
    	// Schritt 1:
    	// Auswürfeln des Musters
    	
    	// Zufallszahl zwischen 0 und 99
        int wsp = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Muster auswählen
        
        // 60% ohne muster
        if(wsp < 60) { 
        	schildMuster = patterns[0];
    		musterFarbe = addPattern(g, schildMuster, hauptFarbe, null);
    	}
        
        // 20% streifen 1
        else if(wsp < 80) { 
        	schildMuster = patterns[1];
    		musterFarbe = addPattern(g, schildMuster, hauptFarbe, null);
        }
        
        // 20% streifen 2
        else if(wsp < 100) { 
        	schildMuster = patterns[2];
    		musterFarbe = addPattern(g, schildMuster, hauptFarbe, null);
        }
    	
    	// Schritt 1:
    	// Zufallszahl zwischen 0 und 99
        int wsa = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit Symbollayout auswählen
        String anzahl = "einzeln";
        
        // 60% einzeln
        if(wsa < 60) { anzahl = "einzeln"; }
        
        // 20% dreieck
        else if(wsa < 80) { anzahl = "dreieck";}
        
        // 20% drei
        else if(wsa < 100) { anzahl = "drei";}
        
        //overlayFarbe = addOverlay(g, shieldOverlays[4], hauptFarbe);
    }
    
    // ---------------------------------------------------------- Methoden zum Hinzufügen von Komponenten ------------------------------------------------------------
    
    // Fügt dem Wappen ein Overlay hinzu in einer Farbe, die zur Hauptfarbe passt
    private Color addOverlay(Graphics g, BufferedImage overlay, Color hauptFarbe) {
    	
    	Color overlayFarbe = farben[r.nextInt(farben.length)];
        while(overlayFarbe.equals(hauptFarbe)) {
        	overlayFarbe = farben[r.nextInt(farben.length)];
        }
        overlay = dye(overlay, new Color(overlayFarbe.getRed(), overlayFarbe.getGreen(), overlayFarbe.getBlue(), 255));
        g.drawImage(overlay, 0, 0, null);
        return overlayFarbe;
    }
    
 // Fügt dem Wappen ein Muster hinzu in einer Farbe, die zu Hauptfarbe und Overlayfarbe passt
    private Color addPattern(Graphics g, BufferedImage pattern, Color hauptFarbe, Color overlayFarbe) {
    	
    	Color musterFarbe = farben[r.nextInt(farben.length)];
        while(musterFarbe.equals(hauptFarbe) || musterFarbe.equals(overlayFarbe)) {
        	musterFarbe = farben[r.nextInt(farben.length)];
        }
        pattern = dye(pattern, new Color(musterFarbe.getRed(), musterFarbe.getGreen(), musterFarbe.getBlue(), 255));
        g.drawImage(pattern, 0, 0, null);
        return musterFarbe;
    }
    
 // Fügt dem Wappen ein Zeichen hinzu in einer Farbe, die zu Hauptfarbe, Overlayfarbe und Musterfarbe passt
    private Color addSymbol(Graphics g, BufferedImage zeichen, int größe, int x, int y, Color hauptFarbe, Color overlayFarbe, Color musterFarbe, Color zeichenFarbe) {
    	
    	// Zeichenfarbe auswürfeln falls es das erste zeichen auf dem wappen ist, sonst die übergebene farbe nehmen
    	if(zeichenFarbe == null) {
    		zeichenFarbe = farben[r.nextInt(farben.length)];
    		while(zeichenFarbe.equals(hauptFarbe) || zeichenFarbe.equals(overlayFarbe) || zeichenFarbe.equals(musterFarbe)) {
        		zeichenFarbe = farben[r.nextInt(farben.length)];
    		}
    	}
    	
    	if(zeichen != null) {
    		
    		// Zeichen einfaerben und hinzufügen
    		zeichen = dye(zeichen, new Color(zeichenFarbe.getRed(), zeichenFarbe.getGreen(), zeichenFarbe.getBlue(), 255));
    		g.drawImage(zeichen.getScaledInstance(größe, größe, 2), x, y, null);	
        }
        return zeichenFarbe;
    }
    // --------------------------------------------------------------------------------------------------------------------------------------------------
    
    
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
