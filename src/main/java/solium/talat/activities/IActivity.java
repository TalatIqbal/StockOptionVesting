package solium.talat.activities;

import java.util.Date;

import solium.talat.activities.ActivityConfig.ActivityType;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.EmployeeStanding;

public interface IActivity {
	
	/**
	 * populates the different components of a activity to their object
	 * @param activityComponents
	 */
	public void populateComponents(String[] activityComponents);
	
	/**
	 * Gets the activity date
	 * @return activityDate
	 */
	public Date getActivityDate() ;
	
	/**
	 * Returns the type of Activity - PERF,VEST,SALE
	 * @return activtyType
	 */
	public ActivityType getActivityType() ;
	
	/**
	 * Computes the gains/loss and the number of units available for each activity
	 * @param empStanding
	 * @param params
	 */
	public void compute(EmployeeStanding empStanding, ComputeParams params) ;
}
