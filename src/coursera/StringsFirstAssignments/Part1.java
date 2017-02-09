package coursera.StringsFirstAssignments;

/**
 * Created by mingjingtang on 11/18/16.
 */
public class Part1 {
	public String findSimpleGene(String dna){
		int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}

		int stop= dna.indexOf("TAA", start+3);
		if (stop == -1){
			return "";
		}
		if ((stop - start)%3==0){
			return dna.substring(start, stop+3);
		}
		return "";
	}

	public void testSimpleGene(){
		String s1 = "TAAAAA";
		String s2 = "AATGATGATG";
		String s3 = "AAATTTGGG";
		String s4 = "AAATGAAAAAATAA";
		String s5 = "AAATGAATTTAA";

		System.out.println("DNA strand is: " + s4);
		String gene = findSimpleGene(s4);
		System.out.println("Gene is: " + gene);
	}
}
