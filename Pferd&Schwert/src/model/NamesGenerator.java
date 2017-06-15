package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class NamesGenerator {

	Random random = new Random();
	static String[] name_1 = new String[200];
	static String[] man = new String[200];
	static String[] frau = new String[200];
	static String[] held_1 = new String[200];
	static String[] held_2 = new String[200];
	static String[] stadt_1 = new String[200];
	static String[] stadt_2 = new String[200];
	public String Vorname;
	public String Ehrenname;
	public String Stadt;
	
	public void arraysFuellen() {

        // Schreibt Namensteile von Textdatei in Array
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Stadt_1.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                stadt_1[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Stadt_2.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                stadt_2[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Name_1.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                name_1[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Männlich.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                man[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Weiblich.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                frau[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Ehrenname_1.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                held_1[n] = line;
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
        
        br = null;
        try {
            br = new BufferedReader(new FileReader(new File(
                    ".\\Pferd&Schwert\\resources\\names\\Ehrenname_2.txt")));
            String line = null;
            int n = 0;
            while ((line = br.readLine()) != null) {

                held_2[n] = line;
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
        
        Vorname = maennernamenGenerieren();
        Ehrenname = ehrennamenGenerieren();
    	Stadt = stadtnamenGenerieren();
    	
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
	
	
	public String stadtnamenGenerieren() {
		
		String s = stadt_1[random.nextInt(wortanzahl(stadt_1) + 1)] + stadt_2[random.nextInt(wortanzahl(stadt_2) + 1)];
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
			return maennernamenGenerieren() + " " + ehrennamenGenerieren() + " von " + stadtnamenGenerieren();
		}
		else {
			return frauennamenGenerieren() + " " + ehrennamenGenerieren() + " von " + stadtnamenGenerieren();
		}
	}
	
}
