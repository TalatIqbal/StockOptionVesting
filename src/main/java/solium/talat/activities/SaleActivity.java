package solium.talat.activities;

import solium.talat.activities.ActivityConfig.ActivityType;
import solium.talat.exception.SoliumCorruptActivityComponentsException;
import solium.talat.exception.SoliumUnitsUnavailablException;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.EmployeeStanding;
import solium.talat.structures.Globals;
import solium.talat.utilities.DateFormatter;

public class SaleActivity extends GeneralActivity implements IActivity {
	
	private int unitsSold ;
	private double soldPricePerUnit ;
	
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public double getSoldPricePerUnit() {
		return soldPricePerUnit;
	}
	public void setSoldPricePerUnit(double soldPricePerUnit) {
		this.soldPricePerUnit = soldPricePerUnit;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void populateComponents(String[] activityComponents) {
		
		try {
			if(activityComponents.length < 5){
				throw new SoliumCorruptActivityComponentsException("SALE should have at least 5 acitivty components");
			}
					
			this.activityType = ActivityType.SALE;
			this.activityDate = DateFormatter.toDate(activityComponents[2]);
			this.unitsSold = Integer.valueOf(activityComponents[3]);
			this.soldPricePerUnit = Double.valueOf(activityComponents[4]);
			
			Globals.DISPLAY_SALEGAIN = true;
		} catch (SoliumCorruptActivityComponentsException ex){
			// ex.printStackTrace();
		}		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void compute(EmployeeStanding empStanding, ComputeParams params){
		try{
			if(empStanding.getUnitsAvailable() < this.unitsSold){
				throw new SoliumUnitsUnavailablException("Not enough units available to sell");
			}
			
			double saleGain = this.unitsSold*(this.soldPricePerUnit - empStanding.getLastVestedPrice());
			
			empStanding.adjustUnits(-this.unitsSold);
			empStanding.adjustSaleGain(saleGain);
			
			double totalCashReduction = this.unitsSold*(params.getCurrentPrice()-empStanding.getLastVestedPrice());
			empStanding.adjustCashGain(-totalCashReduction);
		} catch(SoliumUnitsUnavailablException ex){
			// ex.printStackTrace();
		}
	}
}