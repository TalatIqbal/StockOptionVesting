package solium.talat.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

public class DateFormatterTest {

	@Test
	public void testToDate_ValidFormat() {
		String dateAsString = "20120612";
		@SuppressWarnings("deprecation")
		Date expectedOutput = new Date(2012 - 1900, 05, 12);

		Date returnedDate = DateFormatter.toDate(dateAsString);

		assertEquals(expectedOutput, returnedDate);
	}

	@Test
	public void testToDate_InValidFormat() {
		String dateAsString = "123245";
		Date returnedDate = DateFormatter.toDate(dateAsString);

		assertEquals(null, returnedDate);
	}

}
