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
	public String[] symbolNames = {"Drache1", "Gral1", "Lilie1", "L�we1", "Krake1", "Kralle1", "Schl�ssel1", "Greif1", "Stern1", "Anker1", "Muschel1", "Adler1", "Wolf1", "Rose1", "Vogel1", "Greif2", "Helm1", "Karpfen1", "Hufeisen1", "Pferd1", "Schwan1", "Armbrust1", "Helm2", "Helm3", "L�we2", "Turm1", "Stier1", "Fuchs1", "Bogen1", "Schnecke1", "Schwalbe1", "Eichenblatt1", "Ahornblatt1", "Buchenblatt1", "Wildschwein1", "Linde1", "L�we3", "Fuchs2", "Einhorn1", "Drache2", "Krone1", "Krone2", "Krone3", "Stier2", "Einhorn2", "Pferd2", "Schiff1", "Rose2", "Tulpe1", "Waage1", "Axt1", "Rad1", "Mond1", "Elch1", "Greif3", "Sonne1", "B�r1", "Buche1", "Elch2", "Kleeblatt1", "Fl�gel1", "Eichh�rnchen1", "Drache3"};
	public String[] overlayNames = {"Halb", "Einzelviertel", "Viertel2", "Viertel", "Horizont", "Horizont2", "Diagonal", "Diagonal2", "Dreieck", "Dreieck2", "QuerViertel", "QuerViertel2", "EinzelstreifenVertikal", "EinzelstreifenHorizontal", "Schildrand", "DreieckUnten"};
	public String[] patternNames = {"StreifenVertikal", "StreifenVertikal2", "StreifenHorizontal", "Schachbrett", "Schachbrett2", "Schachbrett3", "StreifenDiagonal", "Streifendachform", "StreifenVForm"};
	public Color[] farben = {new Color(252,252,252),//Wei�
							new Color(10,10,10),//Schwarz
							new Color(160,0,0),//Rot
							new Color(30,150,30),//Hellgr�n
							new Color(0,0,160),//Dunkelblau
							new Color(220,220,0),//Dunkelgelb
							new Color(90,0,0), //Dunkelrot
							new Color(100,100,255),//Hellblau
							new Color(0,80,0),//Dunkelgr�n
							new Color(100,100,100),//Grau
							new Color(90,10,90),//Lila
							new Color(200,100,0),//Orange
							new Color(10,90,90),//T�rkis 
							new Color(0,0,80),//Dunkelblau
							new Color(183,51,135),//Magenta
							/*new Color(225,224,202),//Beige*/
							/*new Color(157,126,31),//Braunorange*/
							/*new Color(240,240,10)*/};//Gelb
	public BufferedImage[] symbols = new BufferedImage[symbolNames.length];
	public BufferedImage[] shieldOverlays = new BufferedImage[overlayNames.length+1];
	public BufferedImage[] patterns = new BufferedImage[patternNames.length];
    private BufferedImage shieldshadow;
    private BufferedImage shield;
    
    private BufferedImage pattern;
    private BufferedImage overlay;
    private BufferedImage symbol1;
    private BufferedImage symbol2;
    private String overlayName = "einfarbig";
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
    	symbol1 = symbols[r.nextInt(symbols.length)];
    	//Farben ausw�hlen
        pickColors();
    	
    	// Schritt 1:
        // Ausw�rfeln des Overlays
        
        // Zufallszahl zwischen 0 und 99
        int ws = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Overlay ausw�hlen
        overlayName = "einfarbig";
        
        // 12% einfarbig
        if(ws < 12) { overlayName = "einfarbig"; }
        
        // 5% diagonal 1
        //else if(ws < 0) { overlayName = "diagonal1"; }
        
        // 5% diagonal 2
        //else if(ws < 0) { overlayName = "diagonal2"; }
        
        // 5% dreick 1
        //else if(ws < 0) { overlayName = "dreieck1"; }
        
        // 10% dreick 2
        else if(ws < 22) { overlayName = "dreieck2"; }
        
        // 10% halb 1
        else if(ws < 32) { overlayName = "halb1"; }
        
        // 8% halb 2
        else if(ws < 40) { overlayName = "einzelviertel"; }
        
        // 10% horizontal 1
        else if(ws < 50) { overlayName = "horizontal1"; }
        
        // 10% horizontal 2
        else if(ws < 60) { overlayName = "horizontal2"; }
        
        // 5% quer viertel 1
        //else if(ws < 0) { overlayName = "querviertel1"; }
        
        // 5% quer viertel 2
        //else if(ws < 0) { overlayName = "querviertel2"; }
        
        // 5% viertel 1
        //else if(ws < 0) { overlayName = "viertel1"; }
        
        // 5% viertel 2
        //else if(ws < 0) { overlayName = "viertel2"; }
        
        // 10% einzelstreifenVertikal
        else if(ws < 70) { overlayName = "einzelstreifenVertikal"; }
        
        // 10% einzelstreifenHorizontal
        else if(ws < 80) { overlayName = "einzelstreifenHorizontal"; }
        
        // 10% schildrand
        else if(ws < 90) { overlayName = "schildrand"; }
        
        // 10% schildrand
        else if(ws < 100) { overlayName = "dreieckUnten"; }
        
        switch(overlayName) {
        
	       // --------------- Wappen ohne Overlay ---------------
        	case "einfarbig":
        		
            	overlay = null;
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                int wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 23) { symbolLayout = "einfach";}
                
                // 19%
                else if(wsp < 44) { symbolLayout = "dreieck"; }
                
                // 12%
                else if(wsp < 58) { symbolLayout = "dreiVert"; }
                
                // 12%
                else if(wsp < 72) { symbolLayout = "zweiGleichVert"; }
                
                // 12%
                else if(wsp < 86) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 12%
                else if(wsp < 100) { symbolLayout = "zweiGespiegelt"; }         
        		break;
        		
        	       // --------------- Geteilte Wappen mit Overlay rechts ---------------
            case "halb1":
            	      		
            	overlay = shieldOverlays[0];
            	
            	//Muster
            	pattern = choosePattern(100, false, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 23%
                else if(wsp < 18) { symbolLayout = "einfach";}
                
                // 25%
                else if(wsp < 36) { symbolLayout = "zweiGespiegelt"; }
                
                // 25%
                else if(wsp < 52) { symbolLayout = "zweiInversHor"; }
                
                // 25%
                else if(wsp < 68) { symbolLayout = "zweiVerschHor"; 
            	pattern = choosePattern(100, false, false);
            	symbol2 = symbols[r.nextInt(symbols.length)];
            	}
                
                // 25%
                else if(wsp < 84) { symbolLayout = "einzelnRechts"; 
            	pattern = choosePattern(10, false, false);
            	}
                
                // 25%
                else if(wsp < 100) { symbolLayout = "zweiundeins"; 
            	pattern = choosePattern(100, false, false);
            	symbol2 = symbols[r.nextInt(symbols.length)];
            	}

            	break;
            	
            // --------------- Geteilte Wappen mit Overlay links ---------------
            case "einzelviertel":
            	
            	overlay = shieldOverlays[1];
            	pattern = choosePattern(0, false, false);
            	
            	symbolLayout = "einzelnObenLinks";
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
            	
            	// Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
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
                else if(wsp < 67) {
                	symbolLayout = "zweiGleichVert"; 
                	pattern = choosePattern(90, true, true);
                }
                
                // 20%
                else if(wsp < 87) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                	pattern = choosePattern(90, true, true);
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
            	pattern = choosePattern(40, true, true);
            	
            	// Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
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
                else if(wsp < 67) { 
                	symbolLayout = "zweiGleichVert";
                	pattern = choosePattern(90, true, true);
                }
                
                // 20%
                else if(wsp < 87) {
                	symbolLayout = "zweiVerschVert";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                	pattern = choosePattern(90, true, true);
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
            	pattern = choosePattern(20, true, false);
            	
            	// Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 23%
                else if(wsp < 26) { symbolLayout = "einfach";}
                
                // 0%
                else if(wsp < 51) { symbolLayout = "einfachGanzOben";}
                
                // 0%
                else if(wsp < 75) { 
                	symbolLayout = "einfachUnten";
                	pattern = choosePattern(100, true, false);
                }
                
                // 0%
                else if(wsp < 100) { 
                	symbolLayout = "zweiVerschVertF�rDreieck";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                	pattern = choosePattern(100, true, false);
                }
                
            	
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
            	
            // --------------- Ein gro�er vertikaler Streifen in der Mitte ---------------
            case "einzelstreifenVertikal":
        		
            	overlay = shieldOverlays[12];
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 32) { symbolLayout = "einfach";}
                
                // 19%
                else if(wsp < 66) { 
                	symbolLayout = "dreieck";
                	pattern = null;
                	}
                
                // 12%
                else if(wsp < 100) { symbolLayout = "dreiVert"; }
         	   break;
           
            default:
        	   System.out.println("OVERLAY_DEFAULT_CASE");
        	   symbolLayout = "ohne";
        	   break;
        	   
               // --------------- Ein gro�er horizontaler Streifen in der Mitte ---------------
        	   
        	case "einzelstreifenHorizontal":
        		
            	overlay = shieldOverlays[13];
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 23) { symbolLayout = "einfach";}
                
                
                // 12%
                else if(wsp < 40) { symbolLayout = "zweiGespiegeltf�rEH"; }         
        		
                // 12%
                else if(wsp < 70) { 
                	symbolLayout = "dreieckf�rEH";
            		pattern = null;
                					}  
                
                // 12%
                else if(wsp < 100) { symbolLayout = "einfachf�rEH"; }  
        		break;
        		
     	       // --------------- Wappen mit Schildrand ---------------
        	case "schildrand":
        		
        		overlay = shieldOverlays[14];
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	if (pattern != null) {
            		System.out.println("HALLO!");
            		drawnColors[2] = drawnColors[3];
            	}
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                 wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 8) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 31) { symbolLayout = "einfachF�rRand";}
                
                // 19%
                else if(wsp < 54) { symbolLayout = "dreieckF�rRand"; }
                
                // 12%
                else if(wsp < 77) { symbolLayout = "zweiGleichVertF�rRand"; }
                
                // 12%
                else if(wsp < 100) {
                	symbolLayout = "zweiVerschVertF�rRand";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 12%
                else if(wsp < 0) { symbolLayout = "zweiGespiegelt"; }         
        		break;
        		
     	       // --------------- Wappen mit Dreiecksoverlay unten ---------------
        	case "dreieckUnten":
        		
            	overlay = shieldOverlays[15];
            	
            	//Muster
            	pattern = choosePattern(60, true, false);
            	
            	//Symbol
            	
            	// Zufallszahl zwischen 0 und 99
                wsp = r.nextInt(100);
                
                // Je nach Wahrscheinlichkeit ein Smbollayout ausw�hlen
                symbolLayout = "ohne";
                
                // 2% ohne symbol
                if(wsp < 2) { symbolLayout = "ohne"; }
                
                // 19%
                else if(wsp < 35) { symbolLayout = "einfach";}
                
                // 0%
                else if(wsp < 65) { 
                	symbolLayout = "einfachUnten";
                	pattern = choosePattern(100, true, false);
                }
                
                else if(wsp < 100) { 
                	pattern = choosePattern(100, true, false);
                	symbolLayout = "dreieckUnten";
                	symbol2 = symbols[r.nextInt(symbols.length)];
                }
                
                // 19%
                else if(wsp < 100) { symbolLayout = "dreieckf�rDU"; }      
        		break;
            }
        BufferedImage coatOfArms = drawCoatOfArms(pattern, overlay, symbol1, symbol2, symbolLayout, drawnColors);
        
    	return coatOfArms;
    }
    
    private BufferedImage choosePattern(int wspBlanc, boolean symetricHoriz, boolean horizontalHalfOnly){
    	
    	// Schritt 1:
    	// Ausw�rfeln des Musters
    	
    	// Zufallszahl zwischen 0 und 99
        int wsp = r.nextInt(100);
        
        // Je nach Wahrscheinlichkeit ein Muster ausw�hlen
        String muster = "ohne";
        
        int wspRest =  (100 - wspBlanc)/5;
        
        // 60% ohne muster
        if(wsp < wspBlanc) { 
        	muster = "ohne";
        	pattern =null;
        	}
        
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
        
        // 15% streifenDiagonal
        else if(wsp < wspBlanc + 2*wspRest + wspRest/2) { muster = "streifenDiagonalFlip"; }
        
        // 15% streifenDiagonal
        else if(wsp < wspBlanc + 3*wspRest) { muster = "streifenDiagonal"; }
        
        // 15% streifenVform
        else if(wsp < 3*wspRest + wspRest/2) { muster = "streifenVForm"; }
        
        // 15% streifenDachform
        else if(wsp < wspBlanc + 4*wspRest) { muster = "streifenDachform"; }
        
        // 10% schachbrettmuster
        else if(wsp < 100) {
        
        if(symetricHoriz && horizontalHalfOnly){
        	muster = "schachbrett3";
        } else if (symetricHoriz){
        	muster = "schachbrett1";
        } else { muster = "schachbrett2"; }
        					}
        
        
        // Schritt 2:
        // Muster einf�gen und einf�rben
        
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
        	
        case "streifenDiagonal":
        	shieldPattern = patterns[6];
        	break;
        	
        case "streifenDiagonalFlip":
        	shieldPattern = flipVertical(patterns[6]);
        	break;
        	
        case "streifenDachform":
        	shieldPattern = patterns[7];
        	break;
        	
        case "streifenVForm":
        	shieldPattern = patterns[8];
        	break;
        	
        default:
        	break;
        }
    	
    	return shieldPattern;
    }
    
    //W�hlt ein Set mit vier zusammepassenden Farben aus
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
    	
    	// vierte Farbe
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
        	g.drawImage(symbol1, 76, 104, null);
        	break;
        	
        case "einfachF�rRand":
        	g.drawImage(symbol1.getScaledInstance(370, 370, 2), 92, 120, null);
        	break;
        	
        case "einfachOben":
        	g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 55, null);
        	break;
        	
        case "einfachGanzOben":
        	g.drawImage(symbol1.getScaledInstance(200, 200, 2), 176, 30, null);
        	break;
        	
        case "einfachUnten":
        	g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "dreieckUnten":
        	symbol2 = dye(symbol2, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol2).getScaledInstance(180, 180, 2), 40, 70, null);
        		g.drawImage(symbol2.getScaledInstance(180, 180, 2), 330, 70, null);
        	} else {
        		g.drawImage(symbol2.getScaledInstance(180, 180, 2), 40, 70, null);
        		g.drawImage(flipVertical(symbol2).getScaledInstance(180, 180, 2), 330, 70, null);
        	}
        	g.drawImage(symbol1.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "dreieck":
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 50, 90, null);
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 300, 90, null);
    		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 176, 350, null);
        	break;
        	
        case "dreieckF�rRand":
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 92, 125, null);
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 279, 125, null);
    		g.drawImage(symbol1.getScaledInstance(180, 180, 2), 186, 350, null);
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
        	
        case "zweiGleichVertF�rRand":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(210, 210, 2), 171, 105, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(210, 210, 2), 171, 105, null);
        	}
    		g.drawImage(symbol1.getScaledInstance(210, 210, 2), 171, 330, null);
        	break;
        	
        case "zweiGleichOben":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(200, 200, 2), 50, 90, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 50, 90, null);
        	}
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(200, 200, 2), 300, 90, null);
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
        	if(overlayName == "horizontal1" || overlayName == "horizontal2"){
        		symbol2 = dye(symbol2, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
        		g.drawImage(symbol2.getScaledInstance(250, 250, 2), 151, 345, null);
        	}
    		g.drawImage(symbol2.getScaledInstance(250, 250, 2), 151, 345, null);
        	break;
        	
        case "zweiVerschVertF�rRand":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(210, 210, 2), 171, 105, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(210, 210, 2), 171, 105, null);
        	}
    		g.drawImage(symbol2.getScaledInstance(210, 210, 2), 171, 330, null);
        	break;
        	
        case "zweiVerschVertF�rDreieck":
    		symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(200, 200, 2), 176, 30, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 176, 30, null);
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
        	
        case "dreieckf�rEH":
        		g.drawImage(symbol1.getScaledInstance(165, 165, 2), 85, 30, null);
        		g.drawImage(symbol1.getScaledInstance(165, 165, 2), 307, 30, null);
        		g.drawImage(symbol1.getScaledInstance(165, 165, 2), 196, 450, null);
        	break;
        	
        case "einfachf�rEH":
        	if(r.nextBoolean()){
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 160, 205, null);
        	} else {
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 160, 205, null);
        	}
        	break;
        	
        case "zweiGespiegeltf�rEH":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 205, null);
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 286, 205, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 205, null);
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 286, 205, null);
        	}
        	break;
        	
        case "zweiVerschHor":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 190, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 190, null);
        	}
    		symbol2 = dye(symbol2, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
    		g.drawImage(symbol2.getScaledInstance(230, 230, 2), 286, 190, null);
        	break;
        	
        case "zweiundeins":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 190, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 190, null);
        	}
    		symbol2 = dye(symbol2, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
    		g.drawImage(symbol2.getScaledInstance(180, 180, 2), 305, 85, null);
    		g.drawImage(symbol2.getScaledInstance(180, 180, 2), 305, 315, null);
        	break;
        	
        case "zweiGespiegelt":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 190, null);
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 286, 190, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 190, null);
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 286, 190, null);
        	}
        	break;
        	
        case "einzelnRechts":
        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 190, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 190, null);
        	}
        	break;
        	
        case "zweiInversHor":
        	if(r.nextBoolean()){
        		symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 34, 190, null);
        		symbol1 = dye(symbol1, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 286, 190, null);
        	} else {
        		symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        		g.drawImage(symbol1.getScaledInstance(230, 230, 2), 34, 190, null);
        		symbol1 = dye(symbol1, new Color(drawnColors[2].getRed(), drawnColors[2].getGreen(), drawnColors[2].getBlue(), 255));
        		g.drawImage(flipVertical(symbol1).getScaledInstance(230, 230, 2), 286, 190, null);
        	}
        	break;
        	
        case "einzelnObenLinks":
        	if(r.nextBoolean()){
        		symbol1 = dye(symbol1, new Color(drawnColors[0].getRed(), drawnColors[0].getGreen(), drawnColors[0].getBlue(), 255));
        	} else {
        		symbol1 = dye(symbol1, new Color(drawnColors[1].getRed(), drawnColors[1].getGreen(), drawnColors[1].getBlue(), 255));
        	}

        	if(r.nextBoolean()){
        		g.drawImage(flipVertical(symbol1).getScaledInstance(200, 200, 2), 50, 90, null);
        	} else {
        		g.drawImage(symbol1.getScaledInstance(200, 200, 2), 50, 90, null);
        	}
        	break;
        
        default:
        	break;
        }
    	
        //System.out.println("Generating: " + symbolLayout);
        //Mit 50% Wahrscheinlichkeit das ganze Wappe spiegeln
        if(r.nextBoolean()){
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
