package solium.talat.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import solium.talat.activities.IActivity;
import solium.talat.exception.SoliumCorruptActivityComponentsException;
import solium.talat.exception.SoliumDataFormatException;
import solium.talat.factories.ActivityFactory;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.EmployeeStanding;
import solium.talat.structures.Globals;
import solium.talat.structures.SoliumData;
import solium.talat.utilities.DateFormatter;
import solium.talat.utilities.ResultsFormatter;

public class SoliumStock {

	/**
	 * Reads and parses the input (file), and populates the data structure used
	 * to store data
	 * @param soliumData
	 * @return ComputeParams
	 */
	public ComputeParams readInput(SoliumData soliumData) {

		InputStreamReader isReader = null;
		BufferedReader brReader = null;
		ComputeParams computeParams = null;

		String currentLine;
		int recordCount = -1;

		try {
			isReader = new InputStreamReader(System.in);
			brReader = new BufferedReader(isReader, Globals.BUFFER_SIZE);
			currentLine = brReader.readLine();

			while (currentLine != null && recordCount == -1) {
				if (currentLine.trim().length() > 0) {
					recordCount = Integer.valueOf(currentLine);
				}
				currentLine = brReader.readLine();
			}

			for (; recordCount > 0; recordCount--) {

				if (!currentLine.isEmpty()) {

					String[] activityComponents = currentLine.split(Globals.INPUT_ITEM_DELIMITER);
					if (activityComponents.length < 3) {
						throw new SoliumCorruptActivityComponentsException("Not enough components for the activity");
					}

					String employeeId = activityComponents[1];
					IActivity activity = ActivityFactory.createActivity(activityComponents);

					soliumData.updateEmployeeIndex(employeeId, activity);
				}
				currentLine = brReader.readLine();

			}

			String[] runValues = currentLine.split(Globals.INPUT_ITEM_DELIMITER);

			Date runDate = DateFormatter.toDate(runValues[0]);
			double price = Double.valueOf(runValues[1]);

			if (runDate == null) {
				throw new SoliumDataFormatException("Date format (YYYYMMDD) does not match");
			}
			computeParams = new ComputeParams(runDate, price);

			isReader.close();
			brReader.close();
		} catch (SoliumDataFormatException ex) {
			System.out.println("Check the date format");
		} catch (SoliumCorruptActivityComponentsException ex) {
			
		} catch (IOException ex) {
			System.out.println("IO Exception: File does not exist");			
		}
		return computeParams;
	}

	/**
	 * Computes the cash gain and the sales gain of each employee for a given
	 * date and time
	 * @param soliumData
	 * @param computeParams
	 */
	public void computeGain(SoliumData soliumData, ComputeParams computeParams) {

		soliumData.getEmployeeData().forEach((employeeId, employee) -> {
			EmployeeStanding empStanding = employee.getEmpStanding();
			employee.getActivities().forEach((date, activitiesOnDate) -> {
				activitiesOnDate.forEach(activity -> {
					if (!computeParams.getRunDate().before(date)) {
						activity.compute(empStanding, computeParams);
					}
				});
			});
		});
	}

	/**
	 * Displays the required output to stdout
	 * @param soliumData
	 */
	public void displayOutput(SoliumData soliumData) {
		
		soliumData.getEmployeeData().forEach((resourceId, employee) -> {
			EmployeeStanding empStanding = employee.getEmpStanding();
			if(empStanding.getCashGain()>=0.00) {
				System.out.println(resourceId + Globals.OUTPUT_ITEM_DELIMITER
						+ ResultsFormatter.formatCashGain(empStanding.getCashGain())
						+ (Globals.DISPLAY_SALEGAIN
								? Globals.OUTPUT_ITEM_DELIMITER + ResultsFormatter.formatGain(empStanding.getSaleGain())
								: ""));
			}
		});
	}
}
