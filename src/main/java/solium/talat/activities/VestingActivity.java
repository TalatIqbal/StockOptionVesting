package solium.talat.activities;

import solium.talat.activities.ActivityConfig.ActivityType;
import solium.talat.exception.SoliumCorruptActivityComponentsException;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.EmployeeStanding;
import solium.talat.utilities.DateFormatter;

public class VestingActivity extends GeneralActivity implements IActivity {
	
	private int unitsCount ;
	private double grantPricePerUnit ;
	
	public double getGrantPricePerUnit() {
		return grantPricePerUnit;
	}
	public void setGrantPricePerUnit(double grantPricePerUnit) {
		this.grantPricePerUnit = grantPricePerUnit;
	}
	public int getUnitsCount() {
		return unitsCount;
	}
	public void setUnitsCount(int unitsCount) {
		this.unitsCount = unitsCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populateComponents(String[] activityComponents) {
		
		try {
			if(activityComponents.length < 5){
				throw new SoliumCorruptActivityComponentsException("VEST should have at least 5 acitivty components");
			}
			
			this.activityType = ActivityType.VEST;
			this.activityDate = DateFormatter.toDate(activityComponents[2]);
			this.unitsCount = Integer.valueOf(activityComponents[3]);
			this.grantPricePerUnit = Double.valueOf(activityComponents[4]);
			
		} catch (SoliumCorruptActivityComponentsException ex){
			// ex.printStackTrace();
		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void compute(EmployeeStanding empStanding, ComputeParams params){
		empStanding.setLastVestedPrice(this.grantPricePerUnit);
		empStanding.setLastVestedUnits(this.unitsCount);
		
		empStanding.adjustUnits(this.unitsCount);
		
		double cashGain = this.unitsCount*(params.getCurrentPrice()-this.grantPricePerUnit);
		
		empStanding.adjustCashGain(cashGain);
	}
}
