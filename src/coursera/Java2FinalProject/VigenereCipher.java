package coursera.Java2FinalProject;

/**
 * Created by mingjingtang on 1/17/17.
 */

import edu.duke.*;
import java.util.*;

public class VigenereCipher {
	CaesarCipher[] ciphers;

	public VigenereCipher(int[] key) {
		ciphers = new CaesarCipher[key.length];
		for (int i = 0; i < key.length; i++) {
			ciphers[i] = new CaesarCipher(key[i]);
		}
	}

	public String encrypt(String input) {
		StringBuilder answer = new StringBuilder();
		int i = 0;
		for (char c : input.toCharArray()) {
			int cipherIndex = i % ciphers.length;
			CaesarCipher thisCipher = ciphers[cipherIndex];
			answer.append(thisCipher.encryptLetter(c));
			i++;
		}
		return answer.toString();
	}

	public String decrypt(String input) {
		StringBuilder answer = new StringBuilder();
		int i = 0;
		for (char c : input.toCharArray()) {
			int cipherIndex = i % ciphers.length;
			CaesarCipher thisCipher = ciphers[cipherIndex];
			answer.append(thisCipher.decryptLetter(c));
			i++;
		}
		return answer.toString();
	}

	public String toString() {
		return Arrays.toString(ciphers);
	}

	public void testVigenereCipher(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		System.out.println("the original message is: "+ message);
		System.out.println("the encrypt message is: "+ encrypt(message));
		System.out.println("the decrypt message is: "+ decrypt(encrypt(message)));
	}

}

