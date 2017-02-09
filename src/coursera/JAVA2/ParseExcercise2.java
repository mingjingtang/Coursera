package coursera.JAVA2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;

/**
 * Created by mingjingtang on 1/9/17.
 */
public class ParseExcercise2 {
	public CSVRecord coldestHourInFile(CSVParser parser){
		CSVRecord coldestSoFar = null;
		for(CSVRecord currentRow: parser){
			coldestSoFar = getColestOfTwo(currentRow, coldestSoFar);
		}
		return coldestSoFar;
	}

	public CSVRecord getColestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar){
		if(coldestSoFar == null || coldestSoFar.get("TemperatureF").equals("-9999")){
			coldestSoFar = currentRow;
			return coldestSoFar;
		}
		if(currentRow == null || currentRow.get("TemperatureF").equals("-9999")){
			return coldestSoFar;
		}
		coldestSoFar = Double.parseDouble(currentRow.get("TemperatureF")) < Double.parseDouble(coldestSoFar.get("TemperatureF"))?
				currentRow: coldestSoFar;
		return coldestSoFar;
	}

	public File fileWithColdestTemperature(){
		DirectoryResource dr = new DirectoryResource();
		CSVRecord coldestSoFar = null;
		File fileOfColestSoFar = null;
		for(File f: dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			coldestSoFar = getColestOfTwo(currentRow, coldestSoFar);
			if(coldestSoFar == currentRow){
				fileOfColestSoFar = f;
			}
			else{
				continue;
			}

		}
		return fileOfColestSoFar;
	}

	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowestHumiditySoFar = null;
		for (CSVRecord record : parser) {
			lowestHumiditySoFar = getLowestOfTwo(record, lowestHumiditySoFar);
		}
		return lowestHumiditySoFar;
	}

	public CSVRecord lowestHumidityInManyFiles(){
		CSVRecord LowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			LowestSoFar = getLowestOfTwo(currentRow, LowestSoFar);
		}
		return LowestSoFar;
	}

	public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestHumiditySoFar){
		if(lowestHumiditySoFar == null || lowestHumiditySoFar.get("Humidity").equals("N/A")){
			lowestHumiditySoFar = currentRow;
			return lowestHumiditySoFar;
		}

		// lowest != NA
		if(currentRow == null || currentRow.get("Humidity").equals("N/A")){
			return lowestHumiditySoFar;
		}

		// lowest , curRow != null || N/A
		lowestHumiditySoFar =
				Integer.parseInt(lowestHumiditySoFar.get("Humidity")) < Integer.parseInt(currentRow.get("Humidity")) ?
						lowestHumiditySoFar : currentRow;

		return lowestHumiditySoFar;
	}

	public double averageTemperatureInFile(CSVParser parser){
		int num = 0;
		double sum = 0;
		for(CSVRecord record: parser){
			double temperature = Double.parseDouble(record.get("TemperatureF"));
			if(sum == 0){
				sum = temperature;
				num++;
			}
			else{
				sum = sum + temperature;
				num++;
			}
		}
		double average = sum/num;
		return average;
	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		int num = 0;
		double sum = 0;
		for(CSVRecord record: parser){
			int humidity = Integer.parseInt(record.get("Humidity"));
			if(humidity >= value){
				double temperature = Double.parseDouble(record.get("TemperatureF"));
				if(sum == 0){
					sum = temperature;
					num++;
				}
				else {
					sum = sum + temperature;
					num++;
				}
			}
			else{
				continue;
			}
		}
		if(sum == 0){
			return 0;
		}
		else {
			double average = sum/num;
			return average;
		}
	}

	public void printAllTemperaturesOnOneDay(File f){
		FileResource fr = new FileResource(f);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord record: parser){
			System.out.println(record.get("DateUTC")+ " "+ record.get("TemperatureF"));
		}
	}

	public void testColdestHourInFile(){
		FileResource fr = new FileResource();
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("the coldest temperature was "+ coldest.get("TemperatureF")+" at "+ coldest.get("TimeEDT"));
	}

	public void testFileWithColdestTemperature(){
		String resultFile = fileWithColdestTemperature().getName();
		System.out.println("Coldest day was in file " + resultFile);

		FileResource fr = new FileResource(fileWithColdestTemperature());
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature on that day was "+ coldest.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were: ");
		printAllTemperaturesOnOneDay(fileWithColdestTemperature());
	}

	public void testLowestHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);
		System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at "+ csv.get("DateUTC"));
	}

	public void testLowestHumidityInManyFiles(){
		CSVRecord lowest = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
	}

	public void testAverageTemperatureInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temperature in file is " + averageTemperatureInFile(parser));
	}

	public void testAverageTemperatureWithHighHumidityInFile(){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		double result = averageTemperatureWithHighHumidityInFile(parser, 80);
		if(result == 0){
			System.out.println("No temperatures with that humidity");
		}
		else{
			System.out.println("Average Temp when high Humidity is: " + result);
		}
	}
}
