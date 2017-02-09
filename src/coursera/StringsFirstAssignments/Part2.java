package coursera.StringsFirstAssignments;

/**
 * Created by mingjingtang on 11/18/16.
 */
public class Part2 {
	public String findSimpleGene(String dna, String startCodon, String stopCodon){
		int start = dna.indexOf(startCodon);
		if (start == -1) {
			return "";
		}

		int stop= dna.indexOf(stopCodon, start+3);
		if (stop == -1){
			return "";
		}
		if ((stop - start)%3==0){
			return dna.substring(start, stop+3);
		}
		return "";
	}

	public void testSimpleGene2(){
		String s1 = "TAAAAA";
		String s2 = "AATGATGATG";
		String s3 = "AAATTTGGG";
		String s4 = "AAATGAAAAAATAA";
		String s5 = "AAATGAATTTAA";

		String s6 = "ATGGGTTAAGTC";
		String s7 = "gatgctataat";

		System.out.println("DNA strand is: " + s7);

		boolean hasUppercase = !s7.equals(s7.toLowerCase());
		boolean hasLowercase = !s7.equals(s7.toUpperCase());

		if(!hasUppercase)
		{
			System.out.println("lowercase Character");
			String gene = findSimpleGene(s7, "atg", "taa");
			System.out.println("Gene is: " + gene);
		}

		if(!hasLowercase){
			System.out.println("uppercase Character");
			String gene = findSimpleGene(s7, "ATG", "TAA");
			System.out.println("Gene is: " + gene);
		}
	}
}
