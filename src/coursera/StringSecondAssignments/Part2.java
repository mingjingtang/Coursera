package coursera.StringSecondAssignments;

/**
 * Created by mingjingtang on 11/21/16.
 */
public class Part2 {
	public int howMany(String stringa, String stringb){
		int count = 0;
		int stop = 0;
		int lengtha = stringa.length();
		int lengthb = stringb.length();
		while(stop <= lengthb){
			int start = stringb.indexOf(stringa);
			if(start == -1){
				return count;
			}
			count++;
			System.out.println("the count is: "+ count);
			stop = start + lengtha;
			System.out.println("the stop right now is: "+stop);
			stringb = stringb.substring(stop);
		}
		return count;
	}

	public void testHowMany(){
		String a = "an";
		String b = "banana";

		int answer = howMany(a,b);
		System.out.println("the total count is: "+ answer);
	}
}
