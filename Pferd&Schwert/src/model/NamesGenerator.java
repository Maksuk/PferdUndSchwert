package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class NamesGenerator {

	Random random = new Random();
	static String path;
	static String[] name_1 = new String[200];
	static String[] man = new String[200];
	static String[] frau = new String[200];
	static String[] held_1 = new String[200];
	static String[] held_2 = new String[200];
	
	// Listen fuer Stadtnamen
	static String[] town_prae_default = new String[1000];
	static String[] town_prae_coast = new String[200];
	static String[] town_prae_river = new String[200];
	static String[] town_prae_mountains = new String[200];
	static String[] town_suff_default = new String[1000];
	static String[] town_suff_coast = new String[200];
	static String[] town_suff_river = new String[200];
	static String[] town_suff_mountains = new String[200];
	
	public String Vorname;
	public String Ehrenname;
	public String Stadt;
	
	// Konstruktor 
	public NamesGenerator(String path) {
		this.path = path + "names\\";
		
	}
	
	// Laed Strings aus einer .txt Liste in ein Array
	public void ladeListe(String dataname, String[] liste) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(path + dataname)));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                liste[n] = line;
                n++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	// Laed alle externen Dateien fuer die Namensgenerierung in Arrays
	public void arraysFuellen() {        
        
        // Lade Listen fuer Stadtnamen
        ladeListe("towns\\town_prae_default.txt", town_prae_default);
        ladeListe("towns\\town_prae_coast.txt", town_prae_coast);
        ladeListe("towns\\town_prae_river.txt", town_prae_river);
        ladeListe("towns\\town_prae_mountains.txt", town_prae_mountains);
        ladeListe("towns\\town_suff_default.txt", town_suff_default);
        ladeListe("towns\\town_suff_coast.txt", town_suff_coast);
        ladeListe("towns\\town_suff_river.txt", town_suff_river);
        ladeListe("towns\\town_suff_mountains.txt", town_suff_mountains);
        
        // Lade Listen fuer Vornamen
        ladeListe("Name_1.txt", name_1);
        ladeListe("Männlich.txt", man);
        ladeListe("Weiblich.txt", frau);
        
        // Lade Listen fuer Ehrennamen
        ladeListe("Ehrenname_1.txt", held_1);
        ladeListe("Ehrenname_2.txt", held_2);
        
        // Erstelle initialen Namen
        Vorname = maennernamenGenerieren();
        Ehrenname = ehrennamenGenerieren();
    	Stadt = stadtnamenGenerieren(true,true,true);
    	
        return;
    }
	
	// Gibt an wie viele Worte in einem Array stehen
	public int wortanzahl(String[] a) {
		
		int anzahl = 0;
		
		for(int i=0; i<a.length; i++) {
			if(a[i] != null) {
				anzahl++;
			}
		}
		return anzahl-1;
	}
	
	// Generiert einen Namen fuer eine Stadt
	public String stadtnamenGenerieren(boolean coast, boolean river, boolean mountains) {
		
		// Stadtnamen initialisieren
		String s = "";
		String v = "";
		String n = "";
		
		// Unschoen dahingehackte Fallunterscheidung fuer die 8 Faelle von Listenkombinationen:
		
		// Fall 1: nur Standardnamen
		if(!coast && !river && !mountains) {
			s = town_prae_default[random.nextInt(wortanzahl(town_prae_default) + 1)] + town_suff_default[random.nextInt(wortanzahl(town_suff_default) + 1)];
		}
		
		// Fall 2: zusaetzlich Kuestennamen
		else if(coast && !river && !mountains) {
			
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_coast) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else {
				v = town_prae_coast[r - wortanzahl(town_prae_default)];
			}
			
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_coast) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else {
				n = town_suff_coast[r - wortanzahl(town_suff_default)];
			}
			s = v + n;
		}
		
		// Fall 3: zusaetzlich Flussnamen
		else if(!coast && river && !mountains) {
					
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_river) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else {
				v = town_prae_river[r - wortanzahl(town_prae_default)];
			}
					
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_river) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else {
				n = town_suff_river[r - wortanzahl(town_suff_default)];
			}
			s = v + n;
		}

		// Fall 4: zusaetzlich Bergnamen
		else if(!coast && !river && mountains) {
							
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_mountains) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else {
				v = town_prae_mountains[r - wortanzahl(town_prae_default)];
			}
							
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_mountains) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else {
				n = town_suff_mountains[r - wortanzahl(town_suff_default)];
			}
			s = v + n;
		}
		
		// Fall 5: zusaetzlich Kuesten- und Flussnamen
		else if(coast && river && !mountains) {
									
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_coast) + wortanzahl(town_prae_river) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else if(r <= wortanzahl(town_prae_default) + wortanzahl(town_prae_coast)){
				v = town_prae_coast[r - wortanzahl(town_prae_default)];
			}
			else {
				v = town_prae_river[r - wortanzahl(town_prae_default) - wortanzahl(town_prae_coast)];
			}
									
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_coast) + wortanzahl(town_suff_river) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else if(r <= wortanzahl(town_suff_default) + wortanzahl(town_suff_coast)){
				n = town_suff_coast[r - wortanzahl(town_suff_default)];
			}
			else {
				n = town_suff_river[r - wortanzahl(town_suff_default) - wortanzahl(town_suff_coast)];
			}
			s = v + n;
		}
		
		// Fall 6: zusaetzlich Fluss- und Bergnamen
		else if(!coast && river && mountains) {
											
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_river) + wortanzahl(town_prae_mountains) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else if(r <= wortanzahl(town_prae_default) + wortanzahl(town_prae_river)){
				v = town_prae_river[r - wortanzahl(town_prae_default)];
			}
			else {
				v = town_prae_mountains[r - wortanzahl(town_prae_default) - wortanzahl(town_prae_river)];
			}
											
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_river) + wortanzahl(town_suff_mountains) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else if(r <= wortanzahl(town_suff_default) + wortanzahl(town_suff_river)){
				n = town_suff_river[r - wortanzahl(town_suff_default)];
			}
			else {
				n = town_suff_mountains[r - wortanzahl(town_suff_default) - wortanzahl(town_suff_river)];
			}
			s = v + n;
		}
		
		// Fall 7: zusaetzlich Kuesten- und Bergnamen
		else if(coast && !river && mountains) {
													
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_coast) + wortanzahl(town_prae_mountains) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else if(r <= wortanzahl(town_prae_default) + wortanzahl(town_prae_coast)){
				v = town_prae_coast[r - wortanzahl(town_prae_default)];
			}
			else {
				v = town_prae_mountains[r - wortanzahl(town_prae_default) - wortanzahl(town_prae_coast)];
			}
													
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_coast) + wortanzahl(town_suff_mountains) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else if(r <= wortanzahl(town_suff_default) + wortanzahl(town_suff_coast)){
				n = town_suff_coast[r - wortanzahl(town_suff_default)];
			}
			else {
				n = town_suff_mountains[r - wortanzahl(town_suff_default) - wortanzahl(town_suff_coast)];
			}
			s = v + n;
		}
		
		// Fall 8: Namen aus allen Listen
		else {
															
			// Vorsilbe
			int r = random.nextInt(wortanzahl(town_prae_default) + wortanzahl(town_prae_coast) + wortanzahl(town_prae_river) + wortanzahl(town_prae_mountains) + 1);
			if(r <= wortanzahl(town_prae_default)) {
				v = town_prae_default[r];
			}
			else if(r <= wortanzahl(town_prae_default) + wortanzahl(town_prae_coast)){
				v = town_prae_coast[r - wortanzahl(town_prae_default)];
			}
			else if(r <= wortanzahl(town_prae_default) + wortanzahl(town_prae_coast) + wortanzahl(town_prae_river)){
				v = town_prae_river[r - wortanzahl(town_prae_default) - wortanzahl(town_prae_coast)];
			}
			else {
				v = town_prae_mountains[r - wortanzahl(town_prae_default) - wortanzahl(town_prae_coast) - wortanzahl(town_prae_river)];
			}
															
			// Nachsilbe
			r = random.nextInt(wortanzahl(town_suff_default) + wortanzahl(town_suff_coast) + wortanzahl(town_suff_river) + wortanzahl(town_suff_mountains) + 1);
			if(r <= wortanzahl(town_suff_default)) {
				n = town_suff_default[r];
			}
			else if(r <= wortanzahl(town_suff_default) + wortanzahl(town_suff_coast)){
				n = town_suff_coast[r - wortanzahl(town_suff_default)];
			}
			else if(r <= wortanzahl(town_suff_default) + wortanzahl(town_suff_coast) + wortanzahl(town_suff_river)){
				n = town_suff_river[r - wortanzahl(town_suff_default) - wortanzahl(town_suff_coast)];
			}
			else {
				n = town_suff_mountains[r - wortanzahl(town_suff_default) - wortanzahl(town_suff_coast) - wortanzahl(town_suff_river)];
			}
			s = v + n;
		}
		
		//TODO: Warum brauchen wir diese Zeile? Irgendwas stimmt nicht mit dem return...
		Stadt = s;
		return s;
	}
	
	public String maennernamenGenerieren() {
		
		String s = name_1[random.nextInt(wortanzahl(name_1) + 1)] + man[random.nextInt(wortanzahl(man) + 1)];
		Vorname = s;
			return s;
	}
	
	public String frauennamenGenerieren() {
		
		String s = name_1[random.nextInt(wortanzahl(name_1) + 1)] + frau[random.nextInt(wortanzahl(frau) + 1)];
		Vorname = s;
			return s;
	}

	public String ehrennamenGenerieren() {
	
	String s = held_1[random.nextInt(wortanzahl(held_1) + 1)] + held_2[random.nextInt(wortanzahl(held_2) + 1)];
	Ehrenname = s;
		return s;
	}
	
	public String gesamtnameGenerieren(boolean g) {
		
		if(g) {
			return maennernamenGenerieren() + " " + ehrennamenGenerieren() + " von " + stadtnamenGenerieren(true,true,true);
		}
		else {
			return frauennamenGenerieren() + " " + ehrennamenGenerieren() + " von " + stadtnamenGenerieren(true,true,true);
		}
	}
	
}
