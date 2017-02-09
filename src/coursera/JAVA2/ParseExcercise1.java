package coursera.JAVA2;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Created by mingjingtang on 1/9/17.
 */
public class ParseExcercise1 {
	public void tester(){
		FileResource fr = new FileResource();
		CSVParser parse = fr.getCSVParser();
//		System.out.println(countryInfo(parse,"Germany"));
//		listExportersTwoProdects(parse,"cotton","flowers");
//		System.out.println(numberOfExporters(parse, "cocoa"));
		bigExporters(parse,"$999,999,999,999");
	}

	public String countryInfo(CSVParser parser, String country){
		for(CSVRecord record: parser){
			String theRecordCountry = record.get("Country");
			if(theRecordCountry.contains(country)){
				String result = theRecordCountry +": "+ record.get("Exports") +": "+record.get("Value (dollars)");
				return result;
			}
			else{
				continue;
			}
		}
		return "NOT FOUND";
	}

	public void listExportersTwoProdects(CSVParser parser, String exportItem1, String exportItem2){
		for(CSVRecord record: parser){
			String theRecordExport = record.get("Exports");
			if(theRecordExport.contains(exportItem1) && theRecordExport.contains(exportItem2)){
				System.out.println(record.get("Country"));
			}
			else{
				continue;
			}
		}
	}

	public int numberOfExporters(CSVParser parser, String exportItem){
		int countCountry = 0;
		for(CSVRecord record: parser){
			String theRecordExport = record.get("Exports");
			if(theRecordExport.contains(exportItem)){
				countCountry++;
			}
			else {
				continue;
			}
		}
		return countCountry;
	}

	public void bigExporters(CSVParser parser, String amount){
		for(CSVRecord record: parser){
			String theRecordeValue = record.get("Value (dollars)");
			if(theRecordeValue.length()>amount.length()){
				System.out.println(record.get("Country") + " "+ theRecordeValue);
			}
			else {
				continue;
			}
		}
	}
}
