package coursera.JAVA2;
import edu.duke.*;


/**
 * Created by mingjingtang on 10/24/16.
 */
public class CaesarCipher {
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey;

	public CaesarCipher(int key){
		mainKey = key;
		alphabet = "abcdefghijklmnopqrstuvwxyz";
		shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
	}

	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder(input);
		for(int i = 0; i < encrypted.length(); i++){
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(currChar);
			if(idx != -1){
				char newChar = shiftedAlphabet.charAt(idx);
				encrypted.setCharAt(i,newChar);
			}
		}
		return encrypted.toString();
	}

	public String decrypt(String input){
		CaesarCipher cc = new CaesarCipher(26-mainKey);
		return cc.encrypt(input);
	}


//	public String encrypt(String input, int key){
//		StringBuilder encrypted = new StringBuilder(input);
//		String alphabet = "abcdefghijklmnopqrstuvwxyz";
//
//		String shiftedAlphabet = alphabet.substring(key)+alphabet.substring(0,key);
//		for(int i = 0; i < encrypted.length(); i++){
//			char currChar = encrypted.charAt(i);
//			int idx = alphabet.indexOf(currChar);
//			if(idx != -1){
//				char newChar = shiftedAlphabet.charAt(idx);
//				encrypted.setCharAt(i,newChar);
//			}
//		}
//		return encrypted.toString();
//	}

//	public String encryptTwoKeys(String input, int key1, int key2){
//		StringBuilder encrypted = new StringBuilder(input);
//		String alphabet = "abcdefghijklmnopqrstuvwxyz";
//
//		String shiftedAlphabet0 = alphabet.substring(key1)+alphabet.substring(0,key1);
//		String shiftedAlphabet1 = alphabet.substring(key2)+alphabet.substring(0,key2);
//
//		for(int i = 0; i < encrypted.length(); i++){
//			char currChar = encrypted.charAt(i);
//			int idx = alphabet.indexOf(currChar);
//			if(idx != -1){
//				if(i%2 == 0){
//					char newChar = shiftedAlphabet0.charAt(idx);
//					encrypted.setCharAt(i,newChar);
//				}
//				if(i%2 == 1) {
//					char newChar = shiftedAlphabet1.charAt(idx);
//					encrypted.setCharAt(i, newChar);
//				}
//			}
//		}
//		return encrypted.toString();
//	}

	public void testCaesar(){
		FileResource fr = new FileResource();
		String messageOrg = fr.asString();
		System.out.println("the original message is: "+ messageOrg);

		String message = messageOrg.toLowerCase();
		System.out.println("the message to lower case is: " + message);

//		String encrypted = encrypt(message, 4);
//		System.out.println("key is " + 4 + "\n" + encrypted);

//		String encrypted2 = encryptTwoKeys(message, 2,20);
//		System.out.println("key is : "+ 2 +" "+ 20 +"\n"+ encrypted2);
	}
}
