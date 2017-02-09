package coursera.JAVA2;

import edu.duke.FileResource;

/**
 * Created by mingjingtang on 11/14/16.
 */
public class TestCaesarCipherTwo {
	public String halfOfString(String message, int start) {
		StringBuilder newHalfString0 = new StringBuilder();
		StringBuilder newHalfString1 = new StringBuilder();

		for(int i = 0; i < message.length(); i++){
			if(i%2 == 0){
				char currChar = message.charAt(i);
				newHalfString0.append(currChar);
			}
			if(i%2 != 0){
				char currChar = message.charAt(i);
				newHalfString1.append(currChar);
			}
		}
		if(start%2 == 0){
			return newHalfString0.toString();
		}
		else
			return newHalfString1.toString();
	}

	public int[] countLetters (String message) {
		String alph = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for (int k = 0; k < message.length(); k++) {
			char ch = Character.toLowerCase(message.charAt(k));
			int dex = alph.indexOf(ch);
			if (dex != -1) {
				counts[dex] += 1;
			}
		}
		return counts;
	}

	public int maxIndex(int[]vals){
		int maxDex = 0;
		for(int k = 0; k < vals.length; k++){
			if(vals[k] > vals[maxDex]){
				maxDex = k;
			}
		}
//		System.out.println("the max index is:  "+ maxDex);
		return maxDex;
	}

	public int getKey(String s){
		int[] array = countLetters(s);
		int maxDex = maxIndex(array);
		int dkey = maxDex - 4;
		if(maxDex < 4){
			dkey = 26 - (4-maxDex);
		}
		System.out.println("this dkey is: "+ dkey);
		return dkey;
	}

	public String breakCaesarCipher(String input){
		String oddString = halfOfString(input, 0);
		String evenString = halfOfString(input, 1);
		int oddKey = getKey(oddString);
		int evenKey = getKey(evenString);

		CaesarCipherTwo cct = new CaesarCipherTwo(26-oddKey,26-evenKey);
//		CaesarCipherTwo cct = new CaesarCipherTwo(26-14,26-24);
		return cct.encrypt(input);
	}

	public void simpleTests(){
		FileResource fr = new FileResource();
		String messageOrg = fr.asString();
		System.out.println("the original message : "+ messageOrg);

		String message = messageOrg.toLowerCase();
		System.out.println("the message to lower case : " + message);

//		CaesarCipherTwo cct = new CaesarCipherTwo(14,24);
//		String encrypted = cct.encrypt(message);
//		System.out.println("the encrypted message is: "+encrypted);
//
//		String de = cct.decrypt(encrypted);
//		System.out.println("the de message is: "+ de);

//		String decrypted = breakCaesarCipher(encrypted);
		String decrypted = breakCaesarCipher(message);
		System.out.println("\n"+ "the find key decrypted message is: " + decrypted);
	}
}
