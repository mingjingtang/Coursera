package coursera.JAVA2;

/**
 * Created by mingjingtang on 11/14/16.
 */
public class CaesarCipherTwo {
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainkey1;
	private int mainkey2;

	public CaesarCipherTwo(int key1, int key2){
		mainkey1 = key1;
		mainkey2 = key2;
		alphabet = "abcdefghijklmnopqrstuvwxyz";
		shiftedAlphabet1 = alphabet.substring(key1)+alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2)+alphabet.substring(0, key2);
	}

	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder(input);

		for(int i = 0; i < encrypted.length(); i++){
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(currChar);
			if(idx != -1){
				if(i%2 == 0){
					char newChar = shiftedAlphabet1.charAt(idx);
					encrypted.setCharAt(i,newChar);
				}
				if(i%2 == 1) {
					char newChar = shiftedAlphabet2.charAt(idx);
					encrypted.setCharAt(i, newChar);
				}
			}
		}
		return encrypted.toString();
	}

	public String decrypt(String input){
		CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainkey1, 26 - mainkey2);
		return cct.encrypt(input);
	}
}
