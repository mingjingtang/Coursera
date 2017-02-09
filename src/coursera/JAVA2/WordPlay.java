package coursera.JAVA2;
import edu.duke.*;

/**
 * Created by mingjingtang on 10/24/16.
 */
public class WordPlay {
	public boolean isVowel(char ch) {
		String vowel = "aeiou";
		for (int i = 0; i < vowel.length(); i++) {
			char eachVowel = vowel.charAt(i);
			if (ch == eachVowel) {
				return true;
			}
		}
		return false;
	}

	public String replaceVowels(String phrase, char ch){
		StringBuilder newPhrase = new StringBuilder(phrase);
		for(int i = 0; i< phrase.length(); i++) {
			char eachPhrase = phrase.charAt(i);
			if (isVowel(eachPhrase) == true) {
				char newChar = ch;
				newPhrase.setCharAt(i, newChar);
			} else
				newPhrase.setCharAt(i, eachPhrase);
		}
		return newPhrase.toString();
	}

	public String emphasize(String phrase, char ch){
		StringBuilder newPhrase = new StringBuilder(phrase);
		for(int i = 0; i< phrase.length(); i++) {
			char eachPhrase = phrase.charAt(i);
			if (isVowel(eachPhrase) == true) {
				if(i%2 == 0) {
					char newChar = ch;
					newPhrase.setCharAt(i, newChar);
				}
				if(i%2 ==1 ){
					char newChar = '+';
					newPhrase.setCharAt(i, newChar);
				}

			}
			else
				newPhrase.setCharAt(i, eachPhrase);
		}
		return newPhrase.toString();


	}

	public void testWordPlay(){
		FileResource fr = new FileResource("Hello World.txt");
		String message = fr.asString();
		System.out.println(message);
		String messageLower = message.toLowerCase();
		//System.out.println(replaceVowels(messageLower, '*'));
		System.out.println(emphasize(messageLower, '*'));
	}
}
