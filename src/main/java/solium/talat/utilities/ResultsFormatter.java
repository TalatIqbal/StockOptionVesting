package solium.talat.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResultsFormatter {

	/**
	 * Formats the Cash Gain to 2 decimal places
	 * @param value
	 * @return formatted as String
	 */
	public static String formatCashGain(double value) {
		return formatPrecision(value, 2);
	}

	/**
	 * Formats the Total Gain to 2 decimal places
	 * @param value
	 * @return formatted String
	 */
	public static String formatGain(double value) {
		return formatPrecision(value, 2);
	}

	/**
	 * Formats the double value to the give number of decimal places with Half-Up Rounding 
	 * @param value
	 * @param decimalPlaces
	 * @return formatted decimal to a string
	 */
	public static String formatPrecision(double value, int decimalPlaces) {
		value = toNonNegative(value);
		BigDecimal bigDecimal = new BigDecimal(value).setScale(decimalPlaces, RoundingMode.HALF_UP);
		return String.format("%." + decimalPlaces + "f", bigDecimal.doubleValue());
	}
	
	/**
	 * Returns 0 if the value sent is negative
	 * @param value
	 * @return non-negative value
	 */
	public static double toNonNegative (double value){
		if(value < 0){
			value = 0.00 ;
		}
		return value ;
	}
}
