package model;

import java.util.Random;

public class WortGenerator {
	private String[] vowelsCommon = {"a", "au", "e", "ei", "i", "ie", "o", "u" };
	private String[] vowelsRare = {"ai", "ä", "eu", "ia", "ö", "ua", "ü"};
	private String[] vowelsVeryRare = {"aa", "ay", "ea", "ee", "oa", "oi", "oo", "ui", "uu"};
	private String[] consonantsCommon = {"b", "d", "f", "g", "h", "j", "l", "m", "n", "p", "r", "s", "t", "v", "w", "z"};
	private String[] consonantsRare = {"c", "ck", "ch", "dr", "dl", "dw", "ff", "fl", "fr", "k", "kl", "kr", "kw", "ll", "mm", "nd", "ng", "nn", "nk", "nl", "nt", "rl", "qu", "rr", "rs", "rt", "sk", "sp", "ss", "st", "x", "y"};
	private String[] startConsonants = {"B", "Bl", "Br", "Ch", "D", "Dr", "Dw", "F", "Fl", "Fr", "G", "Gl", "Gr", "Gw", "H", "Hj", "J", "K", "Kl", "Kr", "Kw", "L", "M", "N", "P", "Pl", "Pr", "Pw", "R", "S", "Sk", "Sp", "St", "T", "Tr", "Tw", "V", "W", "Z"};

	Random r = new Random();
	
	public WortGenerator(){
		for(int i = 0; i < 100; i++){
			System.out.println(generateWord());
		}

	}
	
	public String generateWord(){
		String word =startConsonants[r.nextInt(startConsonants.length-1)];
		int wordLength = r.nextInt(1)+2;
		for(int i =0; i<wordLength; i++){
			if ((i % 2) == 1){
				if(r.nextInt(5)>0){
					word = word + consonantsCommon[r.nextInt(consonantsCommon.length-1)];
				} else {
					word = word + consonantsRare[r.nextInt(consonantsRare.length-1)];
				}
			} else {
				int wsp = r.nextInt(100);
				if(wsp < 60){
					word = word + vowelsCommon[r.nextInt(vowelsCommon.length-1)];
				} else if(wsp < 90)  {
					word = word + vowelsRare[r.nextInt(vowelsRare.length-1)];
				} else if (wsp < 100) {
					word = word + vowelsVeryRare[r.nextInt(vowelsVeryRare.length-1)];
				}

			}


		}

		return word;
	}
}
