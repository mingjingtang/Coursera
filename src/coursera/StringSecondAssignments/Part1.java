package coursera.StringSecondAssignments;


/**
 * Created by mingjingtang on 11/21/16.
 */
public class Part1 {
	public int findStopCodon(String dna, int startIndex, String stopCodon){
		int stop = dna.indexOf(stopCodon, startIndex + 3);
		if(stop == -1){
			return dna.length();
		}
		if((stop - startIndex)%3 == 0){
			return stop;
		}
		return dna.length();
	}

	public String findGene(String dna){
		int startIndex = dna.indexOf("ATG");
		if(startIndex == -1){
			return "";
		}
		int stopCoden1 = findStopCodon(dna, startIndex, "TAA");
		int stopCoden2 = findStopCodon(dna, startIndex, "TAG");
		int stopCoden3 = findStopCodon(dna, startIndex, "TGA");

		int min = Math.min(Math.min(stopCoden1,stopCoden2),stopCoden3);
		if(min!= dna.length()){
			System.out.println("the last index of the gene is: " + (min+2));

			return dna.substring(startIndex, min +3);
		}
		return "";
	}

	public void printAllGenes(String dna){
		int stop = 0;
		while(stop < dna.length()){
			String newGene = findGene(dna);
			if(newGene != "") {
				System.out.println("the new Gene is: " + newGene);
				stop = dna.indexOf(newGene) + newGene.length();
				System.out.println("the stop here is: "+stop);
				dna = dna.substring(stop);
				System.out.println("the dna right now is: "+dna);
			}
		}
	}

	public void testFindStopCodon(){
		String s1 = "ATGAAATAA";
		String s2 = "ATGAAAATAA";
		String s3 = "ATGAAAAAAAAAAAA";

		int result = findStopCodon(s3,0,"TAA");
		System.out.println("the result is: "+ result);
	}

	public void testFindGene(){
		String s1 = "AAAAAAA";
		String s2 = "AATGAAATAAA";
		String s3 = "AAATGGGG";
		String s4 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";

		System.out.println("the given dna is: "+ s4);
		String answer = findGene(s4);
		System.out.println("the gene is: "+ answer);

		printAllGenes(s4);
	}
}
