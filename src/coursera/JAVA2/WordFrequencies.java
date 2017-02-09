package coursera.JAVA2;

import edu.duke.*;
import java.util.ArrayList;

/**
 * Created by mingjingtang on 11/25/16.
 */
public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;

	public WordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}

	public void findUnique(){
		myWords.clear();
		myFreqs.clear();

		FileResource fr = new FileResource();

		for(String s : fr.words()){
			s = s.toLowerCase();
			int index = myWords.indexOf(s);
			if(index == -1){
				myWords.add(s);
				myFreqs.add(1);
			}
			else{
				int value = myFreqs.get(index);
				myFreqs.set(index, value + 1);
			}
		}
	}

	public int findIndexOfMax(){
		int value = myFreqs.get(0);
		for(int i = 0; i < myFreqs.size();i++){
			if(myFreqs.get(i)> value){
				value = myFreqs.get(i);
			}
		}
		return value;
	}

	public void tester(){
		findUnique();
		System.out.println("# unique words: "+ myWords.size());
		for(int k = 0; k < myWords.size(); k++){
			System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
		}
		int max = findIndexOfMax();
		System.out.println("the max value is: "+ max);
		for(int i = 0; i < myFreqs.size(); i++){
			if(myFreqs.get(i) == max){
				System.out.println("the max word is: "+ myWords.get(i));
			}
		}
	}
}
