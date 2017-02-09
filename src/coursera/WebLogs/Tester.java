package coursera.WebLogs;

/**
 * Created by mingjingtang on 1/11/17.
 */

import java.util.*;

public class Tester
{
	public void testLogEntry() {
		LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
		System.out.println(le);
		LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
		System.out.println(le2);
	}

	public void testLogAnalyzer() {
		// complete method
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("short-test_log");
		la.printAll();
	}

	public void testUniqueIP(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog2_log");
		System.out.println("the total ip address number is: "+ la.countUniqueIPs());
	}

	public void testHigherStatuscode(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog1_log");
		la.printAllHigherThanNum(400);
	}

	public void testUniqueIPVisitsOnDay(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog2_log");
		System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
	}

	public void testcountUniqueIPsInRangeStatus(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog2_log");
		System.out.println(la.countUniqueIPsInRange(400, 499));
	}

	public void testCountVisitsPerIP(){
		LogAnalyzer la = new LogAnalyzer();
		la.readFile("weblog2_log");
//		System.out.println(la.countVisitsPerIP());
//		System.out.println("the max num is: "+ la.mostNumberVisitsByIP(la.countVisitsPerIP()));
//		System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
		System.out.println(la.iPsForDays());
//		System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
		System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));

	}

}
