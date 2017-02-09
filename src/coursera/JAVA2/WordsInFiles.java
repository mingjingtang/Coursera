package coursera.JAVA2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

/**
 * Created by mingjingtang on 1/4/17.
 */
public class WordsInFiles {
	private HashMap<String, ArrayList<String>> hm;

	public WordsInFiles(){
		hm = new HashMap<String, ArrayList<String>>();
	}

	private void addWordsFromFile(File f){
		FileResource fr = new FileResource(f);
		for(String word: fr.words()){
			if(!hm.containsKey(word)){
				ArrayList<String> nal = new ArrayList<String>();
				nal.add(f.getName());
				hm.put(word, nal);
			}
			else{
				if(hm.get(word).contains((f.getName()))){
					continue;
				}
				else{
					hm.get(word).add(f.getName());
					hm.put(word, hm.get(word));
				}
			}
		}
	}

	public void buildWordFileMap(){
		hm.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}

	public int maxNumber(){
		int maxnum = 0;
		for(ArrayList<String> value: hm.values()){
			int size = value.size();
			if(size > maxnum){
				maxnum = size;
			}
			else{
				continue;
			}
		}
		return maxnum;
	}

	public ArrayList<String> wordsInNumsFiles(int number){
		ArrayList<String> words = new ArrayList<String>();
		for(String word: hm.keySet()){
			int size = hm.get(word).size();
			if(size == number){
				words.add(word);
			}
			else{
				continue;
			}
		}
		return words;
	}

	public void printFilesIn(String word){
		for(String key: hm.keySet()){
			if(key.equals(word)){
				System.out.println("the file name exist word "  + word + " are: "+ hm.get(key));
			}
			else{
				continue;
			}
		}

	}

	public void tester(){
		buildWordFileMap();

		int numofword = 0;
		for(String word: hm.keySet()){
			System.out.println( word + " " + hm.get(word));
			numofword++;
		}
		System.out.println("the number of words in the all file is: "+ numofword);

		System.out.println("the maximum number of files of any word is: "+ maxNumber());

		System.out.println("number of words occur in the 4 file: " + wordsInNumsFiles(4).size());

		printFilesIn("tree");
	}
}
