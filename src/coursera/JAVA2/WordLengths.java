package coursera.JAVA2;

/**
 * Created by mingjingtang on 10/28/16.
 */
import edu.duke.*;


public class WordLengths {
	public void countWordLengths(FileResource resource, int[]counts){
		for(String word: resource.words()){
			if(Character.isLetter(word.charAt(0)) || Character.isLetter(word.charAt(word.length()-1))){
				System.out.println("the word is: "+ word);

				int curLength = word.length();
				if(word.charAt(word.length()-1) == ',' || word.charAt(word.length()-1) == '.'){
					curLength = curLength - 1;
				}
				counts[curLength] +=1;
			}
		}
		for(int i = 0; i < counts.length; i++){
			System.out.println(counts[i]+" words of length "+ i + ": ");
		}
	}

	public int indexOfMax(int[] values){
		int maxDex = 0;
		for(int i = 0; i < values.length; i++){
			if(values[i] > values[maxDex]){
				maxDex = i;
			}
		}
		return maxDex;
	}

	public void testCountWordLengths(){
		FileResource fr = new FileResource();
		String st = fr.asString();
		System.out.println(st);

		int[] counts = new int[31];
		countWordLengths(fr, counts);

		System.out.println("the max index is: "+ indexOfMax(counts));
	}
}
