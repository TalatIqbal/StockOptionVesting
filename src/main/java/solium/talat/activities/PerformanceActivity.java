package solium.talat.activities;

import solium.talat.activities.ActivityConfig.ActivityType;
import solium.talat.exception.SoliumCorruptActivityComponentsException;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.EmployeeStanding;
import solium.talat.utilities.DateFormatter;

public class PerformanceActivity extends GeneralActivity implements IActivity {
	
	private double performanceMultiplier ;

	public double getPerformanceMultiplier() {
		return performanceMultiplier;
	}

	public void setPerformanceMultiplier(double performanceMultiplier) {
		this.performanceMultiplier = performanceMultiplier;
	}

	public PerformanceActivity(){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populateComponents(String[] activityComponents) {
		
		try {
			if(activityComponents.length < 4){
				throw new SoliumCorruptActivityComponentsException("PERF activity should have 4 components");
			}
					
			this.activityType = ActivityType.PERF;
			this.activityDate = DateFormatter.toDate(activityComponents[2]);
			this.performanceMultiplier = Double.valueOf(activityComponents[3]);
		} catch (SoliumCorruptActivityComponentsException ex){
			
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void compute(EmployeeStanding empStanding, ComputeParams params){
		empStanding.multiplyUnitsAndGain(getPerformanceMultiplier());
	}
}