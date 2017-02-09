package coursera.StringsFirstAssignments;

import edu.duke.URLResource;

/**
 * Created by mingjingtang on 11/19/16.
 */
public class Part4 {
	public void test() {
		URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		for (String s : ur.words()) {
			String lowercase = s.toLowerCase();
			String result = findWebLinks(lowercase);
			System.out.println(result);
		}
	}

	public String findWebLinks(String ns){
		int begin = ns.indexOf("\"");
		if(begin == -1) {
			return "";
		}
		int youtube = ns.lastIndexOf("youtube.com");
		if(youtube == -1) {
			return "";
		}
		int end = ns.indexOf("\"", youtube);
		if(end == -1) {
			return "";
		}

		return ns.substring(begin+1, end);
	}
}
