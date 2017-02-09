package coursera.Java2FinalProject;

import edu.duke.FileResource;

/**
 * Created by mingjingtang on 1/17/17.
 */
public class Drive {
	public static void main(String[] args){
//		CaesarCipher cc = new CaesarCipher(17);
//		cc.testCaesar();

//		CaesarCracker cr = new CaesarCracker('a');
//		cr.testCaesarCracker();

//		int[] rome = new int[]{17, 14, 12, 4};
//		VigenereCipher vc = new VigenereCipher(rome);
//		vc.testVigenereCipher();

		VigenereBreaker vb = new VigenereBreaker();
		vb.breakVigenere();
//		vb.testVigenereBreaker();


	}
}
