package coursera.Java2FinalProject;

/**
 * Created by mingjingtang on 1/17/17.
 */
import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
	public String sliceString(String message, int whichSlice, int totalSlices) {
		StringBuilder sb = new StringBuilder();
		for(int i = whichSlice; i < message.length(); i+=totalSlices){
			sb.append(message.charAt(i));
		}

		return sb.toString();
	}

	public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
		int[] key = new int[klength];
		for(int i = 0; i < klength; i++){
			String sliecdString = sliceString(encrypted, i, klength);
			CaesarCracker cc = new CaesarCracker(mostCommon);
			key[i] = cc.getKey(sliecdString);
		}
		return key;
	}

	public void breakVigenere () {
		FileResource fr = new FileResource();
		String message = fr.asString();

//		FileResource frn = new FileResource();
//		HashSet<String>  dictionarySet = readDictionary(frn);

		HashMap<String, HashSet<String>> hmDictionary = new HashMap<String, HashSet<String>>();
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			FileResource frn = new FileResource(f);
			String name = f.getName();
			HashSet<String>  dictionarySet = readDictionary(frn);
			if(!hmDictionary.containsKey(name)){
				hmDictionary.put(name, dictionarySet);
				System.out.println("put dictionary of "+ name + "is complete." );
			}
		}

		breakForAllLangs(message,hmDictionary);

//		int[] key = tryKeyLength(message, 38, 'e');
//		VigenereCipher vc = new VigenereCipher(key);
//		String decrypted = vc.decrypt(message);
//		int validWords = countWords(decrypted, dictionarySet);
//		System.out.println("the validWords are: "+ validWords);
//		System.out.println("the decrypted message is: "+ decrypted);

//		String decrypted = breakForLanguage(message, dictionarySet);
//		System.out.println("the decrypted message is: "+ decrypted);
	}

	public HashSet<String> readDictionary(FileResource fr){
		HashSet<String> hs = new HashSet<String>();
		for(String line: fr.lines()){
			String lowerCaseline = line.toLowerCase();
			hs.add(lowerCaseline);
		}
		return hs;
	}

	public int countWords(String message, HashSet<String> dictionary){
		int countRealWord = 0;
		ArrayList<String> wordOfEncryptedMessage = new ArrayList<String>();
		for(String word: message.split("\\W+")){
			String lowerCaseWord = word.toLowerCase();
			wordOfEncryptedMessage.add(lowerCaseWord);
			if(dictionary.contains(lowerCaseWord)){
				countRealWord++;
			}
			else {
				continue;
			}
		}
		return countRealWord;
	}

	public String breakForLanguage(String encrypted, HashSet<String> dictionary){
		int max = Integer.MIN_VALUE;
		String theString = "";
		int[] keySet = new int[encrypted.length()];
		int validWords = Integer.MIN_VALUE;
		char mostCommonChar = mostCommonCharIn(dictionary);
		System.out.println("the most common character in this dicitonary is: "+ mostCommonChar);

		for(int i = 1; i <= 100; i++){
			int[] keyArray = tryKeyLength(encrypted,i,mostCommonChar);
			VigenereCipher vc = new VigenereCipher(keyArray);
			String decrypted = vc.decrypt(encrypted);
			int countRealWord = countWords(decrypted, dictionary);
			if(max == Integer.MIN_VALUE){
				max = countRealWord;
				theString = decrypted;
				keySet = keyArray;
				validWords = countRealWord;

			}
			else {
				if(countRealWord > max){
					max = countRealWord;
					theString = decrypted;
					keySet = keyArray;
					validWords = countRealWord;
				}
				else {
					continue;
				}
			}
		}
		for(int i : keySet){
			System.out.println(i);
		}
		System.out.println("the key length is: "+ keySet.length);
		System.out.println("the valid word number are: "+ validWords);
		return theString;
	}

	public char mostCommonCharIn(HashSet<String> dictionary){
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for(String eachWord: dictionary){
			for(int i = 0; i < eachWord.length();i++){
				char currChar = eachWord.charAt(i);
				if(!hm.containsKey(currChar)){
					hm.put(currChar,1);
				}
				else {
					hm.put(currChar, hm.get(currChar)+1);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		char maxChar = ' ';
		for(char thisChar: hm.keySet()){
			if(max == Integer.MIN_VALUE){
				max = hm.get(thisChar);
				maxChar = thisChar;
			}
			else {
				if(hm.get(thisChar)> max){
					max = hm.get(thisChar);
					maxChar = thisChar;
				}
				else {
					continue;
				}
			}
		}
		return maxChar;
	}

	public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
		int maxRealWord = Integer.MIN_VALUE;
		String languageNeedUse = "";
		String decryptedStringUseChoiceLang = "";
		for(String lang: languages.keySet()){
			System.out.println("the name of the language is: "+ lang);
//			System.out.println("the dictionary of this language is: "+ languages.get(lang));
			String decryptedString = breakForLanguage(encrypted,languages.get(lang));
			System.out.println("the decrypted message in this language is: "+decryptedString);
			int realWordsInthisDictionary = countWords(decryptedString,languages.get(lang));
			if(maxRealWord == Integer.MIN_VALUE){
				maxRealWord = realWordsInthisDictionary;
				languageNeedUse = lang;
				decryptedStringUseChoiceLang = decryptedString;
			}
			else{
				if(realWordsInthisDictionary > maxRealWord){
					maxRealWord = realWordsInthisDictionary;
					languageNeedUse = lang;
					decryptedStringUseChoiceLang= decryptedString;
				}
				else {
					continue;
				}
			}
		}
		System.out.println("Final Language we are choice is: "+ languageNeedUse);
		System.out.println("Final decrypted string is: " + decryptedStringUseChoiceLang);
	}

	public void testVigenereBreaker(){
		System.out.println(sliceString("abcdefghijklm", 4, 5));

		FileResource fr = new FileResource();
		String message = fr.asString();
		int[] key = tryKeyLength(message, 5, 'e');
		for(int i = 0; i < 5; i++){
			System.out.println(key[i]);
		}
	}

}

