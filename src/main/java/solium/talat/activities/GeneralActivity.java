package solium.talat.activities;

import java.util.Date;

import solium.talat.activities.ActivityConfig.ActivityType;

public class GeneralActivity {
	
	protected Date activityDate ;
	protected ActivityType activityType;

	/**
	 * {@inheritDoc}
	 */
	public Date getActivityDate() {
		return this.activityDate;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public ActivityType getActivityType() {
		return this.activityType;
	}
}
