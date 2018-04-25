package solium.talat.factories;

import solium.talat.activities.IActivity;
import solium.talat.activities.PerformanceActivity;
import solium.talat.activities.SaleActivity;
import solium.talat.activities.VestingActivity;
import solium.talat.exception.SoliumCorruptActivityComponentsException;
import solium.talat.structures.Globals;

public class ActivityFactory {

	/**
	 * Creates and new IActivity for every line of activity
	 * 
	 * @param activityComponents
	 * @return activity
	 */
	public static IActivity createActivity(String[] activityComponents) {
		IActivity activity = null;

		try {
			activity = classifyActivity(activityComponents[0]);

			if (activity == null) {
				throw new SoliumCorruptActivityComponentsException("Activity is not supported");
			}
			activity.populateComponents(activityComponents);
			
			if(activity.getActivityDate() == null){
				activity = null;
			}
		} catch (SoliumCorruptActivityComponentsException ex) {
			
		} catch (Exception ex) {
			
		}
		return activity;
	}

	/**
	 * Classifies activity based on the first component of the activity<br>
	 * Currently supports the following activity<br>
	 * 1) PERF<br>
	 * 2) VEST<br>
	 * 3) SALE<br>
	 * 
	 * @param activityType
	 * @return activity
	 */
	public static IActivity classifyActivity(String activityType) {

		switch (activityType) {
			case Globals.PERF:
				return new PerformanceActivity();
			case Globals.VEST:
				return new VestingActivity();
			case Globals.SALE:
				return new SaleActivity();
		}
		return null;
	}
}
