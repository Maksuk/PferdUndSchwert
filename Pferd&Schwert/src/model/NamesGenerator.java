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
        
        ladeListe("Name_1.txt", name_1);
        ladeListe("Männlich.txt", man);
        ladeListe("Weiblich.txt", frau);
        ladeListe("Ehrenname_1.txt", held_1);
        ladeListe("Ehrenname_2.txt", held_2);
                                                             
        Vorname = maennernamenGenerieren();
        Ehrenname = ehrennamenGenerieren();
    	Stadt = stadtnamenGenerieren(true,true,true);
    	
        return;
    }
	
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
		
		//TODO: Bisher werden nur die default Listen verwendet
		String s = town_prae_default[random.nextInt(wortanzahl(town_prae_default) + 1)] + town_suff_default[random.nextInt(wortanzahl(town_suff_default) + 1)];
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
