package coursera.JAVA2;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by mingjingtang on 1/5/17.
 */
public class GladLibMap {
	HashMap<String, ArrayList<String>> hm;
	private ArrayList<String> wordUsed;
	private Random myRandom;
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "datalong";


	public GladLibMap(){
		hm = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		wordUsed = new ArrayList<>();
	}

	public GladLibMap(String source){
		hm = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
		wordUsed = new ArrayList<>();
	}

	private void initializeFromSource(String source) {
		String[] categories = new String[9];
		categories[0] = "adjective";
		categories[1] = "noun";
		categories[2] = "color";
		categories[3] = "country";
		categories[4] = "name";
		categories[5] = "animal";
		categories[6] = "timeframe";
		categories[7] = "verb";
		categories[8] = "fruit";

		ArrayList<String> adjectiveList= readIt(source+"/adjective.txt");
		ArrayList<String> nounList = readIt(source+"/noun.txt");
		ArrayList<String> colorList = readIt(source+"/color.txt");
		ArrayList<String> countryList = readIt(source+"/country.txt");
		ArrayList<String> nameList = readIt(source+"/name.txt");
		ArrayList<String> animalList = readIt(source+"/animal.txt");
		ArrayList<String> timeList = readIt(source+"/timeframe.txt");
		ArrayList<String> verbList = readIt(source+"/verb.txt");
		ArrayList<String> fruitList = readIt(source+"/fruit.txt");

		hm.put(categories[0], adjectiveList);
		hm.put(categories[1], nounList);
		hm.put(categories[2], colorList);
		hm.put(categories[3], countryList);
		hm.put(categories[4], nameList);
		hm.put(categories[5], animalList);
		hm.put(categories[6], timeList);
		hm.put(categories[7], verbList);
		hm.put(categories[8], fruitList);
	}

	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}

	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(hm.get("country"));
		}
		if (label.equals("color")){
			return randomFrom(hm.get("color"));
		}
		if (label.equals("noun")){
			return randomFrom(hm.get("noun"));
		}
		if (label.equals("name")){
			return randomFrom(hm.get("name"));
		}
		if (label.equals("adjective")){
			return randomFrom(hm.get("adjective"));
		}
		if (label.equals("animal")){
			return randomFrom(hm.get("animal"));
		}
		if (label.equals("timeframe")){
			return randomFrom(hm.get("timeframe"));
		}
		if (label.equals("verb")){
			return randomFrom(hm.get("verb"));
		}
		if (label.equals("fruit")){
			return randomFrom(hm.get("fruit"));
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		return "**UNKNOWN**";
	}

	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}

	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String ws = w.substring(first+1,last);

		String sub = getSubstitute(ws);
		while(wordUsed.contains(sub)){
			sub = getSubstitute(ws);
		}
		wordUsed.add(sub);
		System.out.println("the sub is: "+ sub);
		System.out.println("the wordUsed list is: "+wordUsed);
		return prefix+sub+suffix;
	}

	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}

	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	public int totalWordsInMap(){
		int totalnumword = 0;
		for(ArrayList<String> al : hm.values()){
			int sizeofal = al.size();
			totalnumword = totalnumword + sizeofal;
		}
		return totalnumword;
	}

	public int totalWordsConsidered(){
		ArrayList<String> keylist = new ArrayList<String>();
		int totalnumworduseforparticular = 0;
		for(String word: wordUsed){
			for(String key: hm.keySet()){
				if(keylist.contains(key)){
					continue;
				}
				else{
					if(hm.get(key).contains(word)){
						int size = hm.get(key).size();
						totalnumworduseforparticular = totalnumworduseforparticular + size;
						keylist.add(key);
					}
					else{
						continue;
					}
				}
			}
		}
		return totalnumworduseforparticular;
	}

	public void makeStory(){
		wordUsed.clear();
		System.out.println("\n");
		String story = fromTemplate("datalong/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\n\n"+ "total number of words that were possible to pick from: "+ totalWordsInMap());
		System.out.println("\n"+ "the word used is: "+ wordUsed);
		System.out.println("\n"+ "total words used in particular gladlib is: "+ totalWordsConsidered());
	}
}
