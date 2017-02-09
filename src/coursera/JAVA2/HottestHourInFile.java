package coursera.JAVA2;

/**
 * Created by mingjingtang on 1/9/17.
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class HottestHourInFile {
	public CSVRecord hottestHourInFile(CSVParser parser) {
		//start with largestSoFar as nothing
		CSVRecord largestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			//If largestSoFar is nothing
			if (largestSoFar == null) {
				largestSoFar = currentRow;
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp > largestTemp) {
					//If so update largestSoFar to currentRow
					largestSoFar = currentRow;
				}
			}
		}
			//The largestSoFar is the answer
			return largestSoFar;
	}



	public void testHottestInDay () {
		FileResource fr = new FileResource();
		CSVRecord largest = hottestHourInFile(fr.getCSVParser());
		System.out.println("hottest temperature was " + largest.get("TemperatureF") +
				" at " + largest.get("TimeEST"));
	}
}
