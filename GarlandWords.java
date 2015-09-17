// https://www.reddit.com/r/dailyprogrammer/comments/3d4fwj/20150713_challenge_223_easy_garland_words/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class GarlandWords {

	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, Integer> garMap = new HashMap<String, Integer>(); //wanted to practice maps
		Set<String> garSet = garMap.keySet();
		garMap.put("Programmer", garDeg("Programmer"));
		garMap.put("Ceramic", garDeg("Ceramic"));
		garMap.put("Onion", garDeg("Onion"));
		garMap.put("Alfalfa", garDeg("Alfalfa"));

		for(String s : garSet ){
			if(garMap.get(s)==0){
				System.out.println(s + " is not a Garland word!");
			} else {
				System.out.println(s + " is a Garland word with degree " + garMap.get(s) + "!");
				garChain(s, 5);
			}
		}

		maxGarFind("enable1.txt");
		
	}

	public static int garDeg(String str){
		str = str.toLowerCase();
		if(str.length()==1){
			return 1;
		} else {
			for(int i = str.length() - 1; i >= 0; i--) {
				if(str.substring(0, i).equals(str.substring(str.length() - i, str.length()))) {
					return i;
				}
			}	
		}
		return 0;
	}

	public static void garChain(String str, int repeatVal){
		int deg = garDeg(str);
		String strChain = str.toLowerCase().substring(deg);
		
		for(int i=1; i<repeatVal; i++){
			str += strChain;
		}
		
		System.out.println(str);
	}
	
	public static void maxGarFind(String filename) throws FileNotFoundException{
		int maxDeg = 0;
		int degCheck;
		String maxGar = "";
		String garCheck;
		Scanner input = new Scanner(new File(filename));
		
		while(input.hasNextLine()) {
			garCheck = input.nextLine(); 
			degCheck = garDeg(garCheck);
			if(degCheck > maxDeg){
				maxGar = garCheck;
				maxDeg = degCheck;
			}
		}
		System.out.println("The largest Garland word is " + maxGar + " with degree " + maxDeg + "!");
		garChain(maxGar, 5);
	}

}
