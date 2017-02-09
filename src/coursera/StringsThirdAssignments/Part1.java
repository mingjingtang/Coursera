package coursera.StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

/**
 * Created by mingjingtang on 12/30/16.
 */
public class Part1 {
//	public int findStopCodon(String dna, int startIndex, String stopCodon){
//		int stop = dna.indexOf(stopCodon, startIndex + 3);
//		if(stop == -1){
//			return dna.length();
//		}
//		if((stop - startIndex)%3 == 0){
//			return stop;
//		}
//		return dna.length();
//	}

	public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
		int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);
		while (currIndex != -1 ) {
			int diff = currIndex - startIndex;
			if (diff % 3  == 0) {
				return currIndex;
			}
			else {
				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
			}
		}
		return -1;
	}

//	public String findGene(String dna){
//		int startIndex = dna.indexOf("ATG");
//		if(startIndex == -1){
//			return "";
//		}
//		int stopCoden1 = findStopCodon(dna, startIndex, "TAA");
//		int stopCoden2 = findStopCodon(dna, startIndex, "TAG");
//		int stopCoden3 = findStopCodon(dna, startIndex, "TGA");
//
//		int min = Math.min(Math.min(stopCoden1,stopCoden2),stopCoden3);
//		if(min == -1){
//			return "";
//
//		}
//		return dna.substring(startIndex, min +3);
//	}

	public String findGene(String dna, int where) {
		int startIndex = dna.indexOf("ATG", where);
		if (startIndex == -1) {
			return "";
		}
		int taaIndex = findStopCodon(dna,startIndex,"TAA");
		int tagIndex = findStopCodon(dna,startIndex,"TAG");
		int tgaIndex = findStopCodon(dna,startIndex,"TGA");
		int minIndex = 0;
		if (taaIndex == -1 ||
				(tgaIndex != -1 && tgaIndex < taaIndex)) {
			minIndex = tgaIndex;
		}
		else {
			minIndex = taaIndex;
		}
		if (minIndex == -1 ||
				(tagIndex != -1 && tagIndex < minIndex)) {
			minIndex = tagIndex;
		}
		if (minIndex == -1){
			return "";
		}
		return dna.substring(startIndex,minIndex + 3);
	}

	public float cgRatio(String dna){
		int countc = 0;
		int countg = 0;
		for(int i = 0; i < dna.length(); i++){
			if(dna.charAt(i) == 'C'){
				countc++;
			}
			if(dna.charAt(i) == 'G') {
				countg++;
			}
		}
		return (float)(countc+countg)/dna.length();
	}

	public int countCTG(String dna){
		int countctg = 0;
		int stop = 0;
		int length = dna.length();
		while(stop < length){
			int appearctg = dna.indexOf("CTG", stop);
			if(appearctg == -1){
				return countctg;
			}
			else{
				countctg++;
				stop = appearctg + 3;
			}
		}
		return countctg;
	}

//	public StorageResource getAllGenes(String dna){
//		int stop = 0;
//		StorageResource sr = new StorageResource();
//		while(stop < dna.length()){
//			String newGene = findGene(dna);
//			if(newGene != "") {
////				System.out.println("the new Gene is: " + newGene);
//				sr.add(newGene);
//				stop = dna.indexOf(newGene) + newGene.length();
////				System.out.println("the stop here is: "+stop);
//				dna = dna.substring(stop);
////				System.out.println("the dna right now is: "+dna);
//			}
//			else{
//				return sr;
//			}
//		}
//		return sr;
//	}

	public StorageResource getAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;
		while ( true ) {
			String currentGene = findGene(dna,startIndex);
			if (currentGene.isEmpty()) {
				break;
			}
			geneList.add(currentGene);
			startIndex = dna.indexOf(currentGene, startIndex) +
					currentGene.length();
		}
		return geneList;
	}

	public void processGenes(StorageResource sr){
		int count60 = 0;
		int countcgRatio = 0;
		int thelongest = 0;
		int numofgene = 0;
		for(String g: sr.data()){
			numofgene++;
			if(g.length() > thelongest){
				thelongest = g.length();
			}
			if(g.length() > 60){
				System.out.println("string length longer than 60: " + g);
				count60++;
			}
			if(cgRatio(g)> 0.35){
				System.out.println("the cg ratio is higher than 0.35: "+ g);
				countcgRatio++;
			}
		}
		System.out.println("the number of string length longer than 60 is: "+ count60);
		System.out.println("the number of cg ratio is higher than 0.35 is: "+ countcgRatio);
		System.out.println("the longest gene length is: "+ thelongest);
		System.out.println("the number of gene find is: "+ numofgene);
	}

//	public void testFindGene(){
//		String s1 = "AAAAAAA";
//		String s2 = "AATGAAATAAA";
//		String s3 = "AAATGGGG";
//		String s4 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
//		String s5 = "ATGCCATAG";
//		String s6 = "CTGAAACTG";
//		String s7 = "AATGCCCTAA";
//
//		System.out.println("the given dna is: "+ s7);
//
//		float cg = cgRatio(s5);
//		System.out.println("the cgRatio of this dna sting is: "+ cg);
//
//		int totalCTG = countCTG(s7);
//		System.out.println("the total number of CTG is: "+ totalCTG);
//	}

	public void testProcessGenes(){
//		String s1 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
//		String s2 = "ATGAAATAA";
//		String s3 = "AATGCCCTAA";
//		String s4 = "ATGAAATGA";

		FileResource fr = new FileResource();
		String dna = fr.asString();
		String ucdna = dna.toUpperCase();
		System.out.println("the data uppercase is: "+ ucdna);

		StorageResource storeGene = getAllGenes(ucdna);
		for(String g: storeGene.data()){
			System.out.println("the gene is: "+ g);
		}
		System.out.println("\n");
		processGenes(storeGene);

		System.out.println("\n");
		System.out.println("the number of CTG appear is: " + countCTG(ucdna));
	}
}
