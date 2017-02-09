package coursera.JAVA2;
import edu.duke.*;

/**
 * Created by mingjingtang on 10/28/16.
 */


public class CaesarBreaker {
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
		return maxDex;
	}

//	public String decrypt(String encrypted){
//		CaesarCipher cc = new CaesarCipher();
//		int[] freqs = countLetters(encrypted);
//		int maxDex = maxIndex(freqs);
//		int dkey = maxDex -4;
//		if(maxDex < 4){
//			dkey = 26 - (4-maxDex);
//		}
//		System.out.println("the dkey is: "+dkey);
//		return cc.encrypt(encrypted, 26- dkey);
//	}

//	public String halfOfString(String message, int start) {
//		StringBuilder newHalfString0 = new StringBuilder();
//		StringBuilder newHalfString1 = new StringBuilder();
//
//		for(int i = 0; i < message.length(); i++){
//			if(i%2 == 0){
//				char currChar = message.charAt(i);
//				newHalfString0.append(currChar);
//			}
//			if(i%2 != 0){
//				char currChar = message.charAt(i);
//				newHalfString1.append(currChar);
//			}
//		}
//		if(start%2 == 0){
//			return newHalfString0.toString();
//		}
//		else
//			return newHalfString1.toString();
//	}

//	public int getKey(String s){
//		int[] array = countLetters(s);
//		int maxDex = maxIndex(array);
//		int dkey = maxDex - 4;
//		if(maxDex < 4){
//			dkey = 26 - (4-maxDex);
//		}
//		System.out.println("this dkey is: "+ dkey);
//		return dkey;
//	}

//	public String decryptTwoKeys(String encrypted){
//		String oddString = halfOfString(encrypted, 0);
//		String evenString = halfOfString(encrypted, 1);
//		int oddKey = getKey(oddString);
//		int evenKey = getKey(evenString);
//
//		CaesarCipher cc = new CaesarCipher();
//		return cc.encryptTwoKeys(encrypted, 26-oddKey, 26-evenKey);
//	}

	public void testCaesar(){
		FileResource fr = new FileResource();
		String messageOrg = fr.asString();
		System.out.println("the original message is: "+ messageOrg);

		String message = messageOrg.toLowerCase();
		System.out.println("the lower case message is: "+ message);

//		System.out.println("the decrypt of one key message: "+ decrypt(message));

//		System.out.println(halfOfString(message,1));
//
//		System.out.println(halfOfString(message,0));

//		String decrypted = (decryptTwoKeys(message));
//		System.out.println("the decrypt of two key is: "+ decrypted);
	}
}
