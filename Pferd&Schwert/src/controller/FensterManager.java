package controller;

import model.NamesGenerator;
import model.CoatOfArmsGenerator;


public class FensterManager {

	public static NamesGenerator nGenerator;
	public static CoatOfArmsGenerator cGenerator;
	
	public FensterManager(NamesGenerator n, CoatOfArmsGenerator c) {
		FensterManager.nGenerator = n;
		FensterManager.cGenerator = c;
	}
	
}
