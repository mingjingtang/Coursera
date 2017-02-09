package coursera.WebLogs;

/**
 * Created by mingjingtang on 1/11/17.
 */
import java.util.*;

import coursera.WebLogs.LogEntry;
import edu.duke.FileResource;

public class LogAnalyzer {
	private ArrayList<LogEntry> records;

	public LogAnalyzer() {
		// complete constructor
		records = new ArrayList<LogEntry>();
	}

	public void readFile(String filename) {
		// complete method
		FileResource fr = new FileResource(filename);
		for(String line: fr.lines()){
			LogEntry le = WebLogParser.parseEntry(line);
			records.add(le);
		}
	}

	public void printAll() {
		for (LogEntry le : records) {
			System.out.println(le);
		}
	}

	public int countUniqueIPs(){
		ArrayList<String> uniqueIps = new ArrayList<String>();
		for(LogEntry le : records){
			String ipAddr = le.getIpAddress();
			if(!uniqueIps.contains(ipAddr)){
				uniqueIps.add(ipAddr);
			}
		}
	return uniqueIps.size();
	}

	public void printAllHigherThanNum(int num){
		for(LogEntry le : records){
			int ipStatusCode = le.getStatusCode();
			if(ipStatusCode > num){
				System.out.println("the ip status code is: "+ ipStatusCode);
			}
			else {
				continue;
			}
		}
	}

	public ArrayList<String> uniqueIPVisitsOnDay(String someday){
		ArrayList<String> ipAddressReturn = new ArrayList<String>();
		for(LogEntry le : records){
			String time = le.getAccessTime().toString();
			if(!time.contains(someday)){
				continue;
			}
			else{
				if(!ipAddressReturn.contains(le.getIpAddress())){
					ipAddressReturn.add(le.getIpAddress());
				}
				else{
					continue;
				}
			}
		}
		return ipAddressReturn;
	}

	public int countUniqueIPsInRange(int low, int high){
		ArrayList<String> statusCodeInRangeIPAdd = new ArrayList<String>();
		for(LogEntry le : records){
			int statusCode = le.getStatusCode();
			if(statusCode >= low && statusCode <= high){
				if(!statusCodeInRangeIPAdd.contains(le.getIpAddress())){
					statusCodeInRangeIPAdd.add(le.getIpAddress());
				}
				else{
					continue;
				}
			}
			else{
				continue;
			}
		}
		return statusCodeInRangeIPAdd.size();
	}

	public HashMap<String, Integer> countVisitsPerIP(){
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(LogEntry le : records){
			String ipAddr = le.getIpAddress();
			if(!counts.containsKey(ipAddr)){
				counts.put(ipAddr,1);
			}
			else{
				counts.put(ipAddr, counts.get(ipAddr) + 1);
			}
		}
		return counts;
	}

	public int mostNumberVisitsByIP(HashMap<String, Integer> map){
		int max = Integer.MIN_VALUE;
		for(String ipAddr: map.keySet()){
			int nums = map.get(ipAddr);
			if(max == Integer.MIN_VALUE){
				max = nums;
			}
			if(nums > max){
				max = nums;
			}
			else{
				continue;
			}
		}
		return max;
	}

	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
		ArrayList<String> ipMostVisit = new ArrayList<String>();
		int max = mostNumberVisitsByIP(map);
		for(String ipAddr : map.keySet()){
			int nums = map.get(ipAddr);
			if(nums == max){
				ipMostVisit.add(ipAddr);
			}
			else{
				continue;
			}
		}
		return ipMostVisit;
	}

	public HashMap<String, ArrayList<String>> iPsForDays(){
		HashMap<String, ArrayList<String>>  dateAndIpMap = new HashMap<String, ArrayList<String>>();
		for(LogEntry le: records){
			String date = le.getAccessTime().toString().substring(4,10);
			String ipAddr = le.getIpAddress();
			if(!dateAndIpMap.containsKey(date)){
				ArrayList<String> ipArray = new ArrayList<String>();
				ipArray.add(ipAddr);
				dateAndIpMap.put(date, ipArray);
			}
			else{
				dateAndIpMap.get(date).add(ipAddr);
			}
		}
		return dateAndIpMap;
	}

	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
		int max = Integer.MIN_VALUE;
		String dayMax = "";
		for(String date: map.keySet()){
			if(max == Integer.MIN_VALUE){
				max = map.get(date).size();
				dayMax = date;
			}
			if(map.get(date).size() > max){
				max = map.get(date).size();
				dayMax = date;
			}
			else {
				continue;
			}
		}
		return dayMax;
	}

	public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
		ArrayList<String> mostVisitIp = new ArrayList<String>();
		for(String date: map.keySet()){
			if(date.equals(day)){
				HashMap<String, Integer> counts = new HashMap<String, Integer>();
				for(String sr : map.get(date)){
					if(!counts.containsKey(sr)){
						counts.put(sr,1);
					}
					else{
						counts.put(sr, counts.get(sr) + 1);
					}
				}
				return iPsMostVisits(counts);
			}
			else{
				continue;
			}
		}
		return null;
	}

}
