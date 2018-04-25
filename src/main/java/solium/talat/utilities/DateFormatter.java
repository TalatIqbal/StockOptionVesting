package solium.talat.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	/**
	 * Converts the date from a string format (yyyyMMdd) to a Date Object
	 * @param dateAsString
	 * @return date
	 */
	public static Date toDate(String dateAsString){
		Date returnDate = null;
		try {
			SimpleDateFormat soliumFormat = new SimpleDateFormat("yyyyMMdd");
			returnDate = soliumFormat.parse(dateAsString);
		} catch(ParseException ex){
			System.out.println("Check the date format");
		}		
		return returnDate;		
	}
}
