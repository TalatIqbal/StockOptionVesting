package solium.talat.structures;

import java.util.Date;

public class ComputeParams {

	private Date runDate ;
	private double currentPrice ;
	
	public ComputeParams(){
		
	}
	
	public ComputeParams(Date date, double price){
		this.runDate = date ;
		this.currentPrice = price;
	}

	public Date getRunDate() {
		return runDate;
	}

	public void setRunDate(Date runDate) {
		this.runDate = runDate;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	
}
