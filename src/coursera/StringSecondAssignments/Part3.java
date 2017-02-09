package coursera.StringSecondAssignments;

/**
 * Created by mingjingtang on 11/21/16.
 */
public class Part3 {
	public String findGene(String dna){
		int startIndex = dna.indexOf("ATG");
		System.out.println("the star index is: "+ startIndex);

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

	public int findStopCodon(String dna, int startIndex, String stopCodon){
		int stop = dna.indexOf(stopCodon, startIndex + 3);
		if(stop == -1){
			return dna.length();
		}
		if((stop - startIndex)%3 == 0){
			System.out.println("the stop index is: "+stop);
			return stop;
		}
		return dna.length();
	}

	public int countGenes(String dna){
		int stop = 0;
		int count = 0;
		while(stop < dna.length()){
			String newGene = findGene(dna);
			if(newGene != "") {
				System.out.println("the new Gene is: " + newGene);
				count++;
				stop = dna.indexOf(newGene) + newGene.length();
				System.out.println("the stop here is: "+stop);
				dna = dna.substring(stop);
				System.out.println("the dna right now is: "+dna);
			}
		}
		return count;
	}

	public void testCountGenes(){
		String s1 = "ATGTAAGATGCCCTAGT";
		int answer = countGenes(s1);
		System.out.println("the number of the dna is: "+answer);
	}
}
