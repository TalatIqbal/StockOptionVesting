package solium.talat.utilities;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultsFormatterTest {

	@Test
	public void testFormatCashGain_LessPrecise() {
		double value = 12.1;
		String expectedValue = "12.10" ;
		
		String returnValue = ResultsFormatter.formatCashGain(value);
		
		assertEquals(expectedValue, returnValue);
	}
	
	@Test
	public void testFormatCashGain_HighPrecise() {
		double value = 12.1555;
		String expectedValue = "12.16" ;
		
		String returnValue = ResultsFormatter.formatCashGain(value);
		
		assertEquals(expectedValue, returnValue);
	}
	
	
	@Test
	public void testFormatGain_LessPrecise() {
		double value = 12.1;
		String expectedValue = "12.10" ;
		
		String returnValue = ResultsFormatter.formatGain(value);
		
		assertEquals(expectedValue, returnValue);
	}
	
	@Test
	public void testFormatGain_HighPrecise() {
		double value = 12.1555;
		String expectedValue = "12.16" ;
		
		String returnValue = ResultsFormatter.formatGain(value);
		
		assertEquals(expectedValue, returnValue);
	}
	
	
	@Test
	public void testToNonNegative_NegativeNumber(){
		double value = -2.23 ;
		double expectedValue = 0.00 ;
		
		double returnedValue = ResultsFormatter.toNonNegative(value);
		
		assertEquals(expectedValue,returnedValue,0.00);
	}
	
	@Test
	public void testToNonNegative_PositiveNumber(){
		double value = 2.23 ;
		double expectedValue = 2.23 ;
		
		double returnedValue = ResultsFormatter.toNonNegative(value);
		
		assertEquals(expectedValue,returnedValue,0.00);
	}
	
	
	@Test
	public void testFormatPrecision_HighUpEquidistant(){
		double value = 4.535 ;
		int precision = 2 ;
		
		String expectedValue = "4.54" ;
		String returnValue = ResultsFormatter.formatPrecision(value, precision);
		
		assertEquals(expectedValue,returnValue);
	}
	
	@Test
	public void testFormatPrecision_HighUpRound(){
		double value = 4.5349 ;
		int precision = 2 ;
		
		String expectedValue = "4.53" ;
		String returnValue = ResultsFormatter.formatPrecision(value, precision);
		
		assertEquals(expectedValue,returnValue);
	}

}
