package coursera.JAVA2;

import edu.duke.FileResource;
import java.util.ArrayList;

/**
 * Created by mingjingtang on 11/28/16.
 */
public class CharactersInPlay {
	private ArrayList<String> charactersName;
	private ArrayList<Integer> counts;

	public CharactersInPlay(){
		charactersName = new ArrayList<String>();
		counts = new ArrayList<Integer>();
	}

	public void update(String person){
			if(!charactersName.contains(person)){
				charactersName.add(person);
				counts.add(1);
			}
			else{
				int index = charactersName.indexOf(person);
				int value = counts.get(index);
				counts.set(index, value + 1);
			}
	}

	public void findAllCharacter(){
		charactersName.clear();
		counts.clear();

		FileResource fr = new FileResource();

		for(String s : fr.lines()) {
			int period = s.indexOf(".");
			String name = s.substring(0,period+1);
			update(name);
		}
	}

	public void tester(){
		findAllCharacter();
		System.out.println("# of character name: "+ charactersName.size());
		for(int k = 0; k < charactersName.size(); k++){
			if(counts.get(k)>=10 && counts.get(k)<=15){
				System.out.println(counts.get(k)+"\t"+charactersName.get(k));
			}
		}
	}
}
