package coursera.StringsFirstAssignments;

/**
 * Created by mingjingtang on 11/19/16.
 */
public class Part3 {
	public boolean twoOccurrences(String stringa, String  stringb){
		int start = stringb.indexOf(stringa);
		int length = stringa.length();
		if(start == -1){
			return false;
		}
		int stop = stringb.indexOf(stringa, start+length);
		if (stop == -1){
			return false;
		}
		return true;
	}

	public String lastPart(String stringa, String stringb){
		int start = stringb.indexOf(stringa);
		int length = stringa.length();
		if(start == -1){
			return stringb;
		}
		else {
			String result = stringb.substring(start + length, stringb.length());
			return result;
		}
	}

	public void testing(){
		if(twoOccurrences("atg", "ctgtatgta")){
			System.out.println("the result is true.");
		}
		else{
			System.out.println("the result is false.");
		}
		String answer = lastPart("zoo","forest");
		System.out.println("the answer is: "+ answer);
	}
}
