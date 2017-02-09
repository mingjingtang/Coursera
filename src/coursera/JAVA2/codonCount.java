package coursera.JAVA2;

import edu.duke.FileResource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mingjingtang on 12/7/16.
 */
public class codonCount {
	private HashMap<String, Integer> dnaMap;

	public codonCount(){
		dnaMap = new HashMap<String, Integer>();
	}

	public void buildCondonMap(int start, String dna) {
		dna = dna.substring(start);
		if(dna.length() < 3){
			System.out.println("the length of the dna is less than a regular codon length!");
		}
		start = 0;
		while (dna.length() >= 3) {
			String codon = dna.substring(start, start + 3);
			if(dnaMap.containsKey(codon)){
				int value = dnaMap.get(codon);
				dnaMap.put(codon, value + 1);
			}
			else{
				dnaMap.put(codon, 1);
			}
			dna = dna.substring(dna.indexOf(codon)+3);
		}
	}


	public String getMostCommonCodon(){
		int max = Integer.MIN_VALUE;
		String thelargestkey = "";
		for(String key : dnaMap.keySet()){
			int value = dnaMap.get(key);
			if(value > max){
				max = value;
				thelargestkey = key;
			}
			else{
				continue;
			}
		}
		System.out.println("the most common codon is: "+ thelargestkey + " with count "+ dnaMap.get(thelargestkey));
		return thelargestkey;
	}

	public void printCodonCounts(int start, int end){
		System.out.println("the codon's value between "+ start +" and " + end + " is: ");
		for(String key: dnaMap.keySet()){
			int value = dnaMap.get(key);
			if(value >= start && value <= end){
				System.out.println(key + " " + value);
			}
			else{
				continue;
			}
		}
	}

	public void tester(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		System.out.println("the original string is: " + st);

		st = st.trim();
		String stuc = st.toUpperCase();
		System.out.println("the string in uppercase and after trim is: " + stuc);
		System.out.println("\n");

		for(int i = 0; i < 3; i++){
			dnaMap.clear();
			buildCondonMap(i, stuc);
			System.out.println("Reading frame starting with "+ i + " results in "+ dnaMap.size() + " unique codons");
			System.out.println("the map of the dna which start at " + i + " is: " + dnaMap);
			System.out.println("\n");

			getMostCommonCodon();
			System.out.println("\n");

			printCodonCounts(7, 7);
			System.out.println("\n");
			System.out.println("\n");
		}
	}
}
