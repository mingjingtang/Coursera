package coursera.JAVA2;

import edu.duke.FileResource;

/**
 * Created by mingjingtang on 11/3/16.
 */
public class TestCaesarCipher {
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

	public String breakCaesarCipher(String input){
		int[] freqs = countLetters(input);
		int maxDex = maxIndex(freqs);
		int dkey = maxDex - 4;
		if(maxDex < 4){
			dkey = 26 - (4-maxDex);
		}
		System.out.println("the dkey is: "+dkey);
		CaesarCipher cc = new CaesarCipher(dkey);
		return cc.decrypt(input);
	}

	public void simpleTests(){
		FileResource fr = new FileResource();
		String messageOrg = fr.asString();
		System.out.println("the original message is: "+ messageOrg);

		String message = messageOrg.toLowerCase();
		System.out.println("the message to lower case is: "+ message);

		CaesarCipher cc = new CaesarCipher(15);
		String encrypted = cc.encrypt(message);
		System.out.println("the encrypted message is: "+ encrypted);

		String de = cc.decrypt(encrypted);
		System.out.println("the de message is: "+ de);

		String decrypted = breakCaesarCipher(encrypted);
		System.out.println("\n"+"the find key decrypted message is: "+ decrypted);
	}
}
