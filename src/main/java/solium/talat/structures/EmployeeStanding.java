package solium.talat.structures;

public class EmployeeStanding {

	private double cashGain;
	private double saleGain;
	private double unitsAvailable;
	private double lastVestedUnits;
	private double lastVestedPrice;

	public double getLastVestedUnits() {
		return lastVestedUnits;
	}

	public void setLastVestedUnits(double lastVestedUnits) {
		this.lastVestedUnits = lastVestedUnits;
	}

	public double getLastVestedPrice() {
		return lastVestedPrice;
	}

	public void setLastVestedPrice(double lastVestedPrice) {
		this.lastVestedPrice = lastVestedPrice;
	}

	public double getCashGain() {
		return cashGain;
	}

	public void setCashGain(double cashGain) {
		this.cashGain = cashGain;
	}

	public double getSaleGain() {
		return saleGain;
	}

	public void setSaleGain(double saleGain) {
		this.saleGain = saleGain;
	}

	public double getUnitsAvailable() {
		return unitsAvailable;
	}

	public void setUnitsAvailable(double unitsAvailable) {
		this.unitsAvailable = unitsAvailable;
	}

	public EmployeeStanding() {

	}

	/**
	 * Multiplies the units available with a multiplyFactor
	 * 
	 * @param multiplyFactor
	 */
	public void multiplyUnitsAndGain(double multiplyFactor) {
		if (multiplyFactor > 0) {
			this.unitsAvailable *= multiplyFactor;
			this.cashGain *= multiplyFactor;
		}
	}

	/**
	 * Adds or subtracts the number of available units and if it goes negative, makes it 0
	 * @param units
	 */
	public void adjustUnits(int units) {
		this.unitsAvailable += units;
		// this.unitsAvailable = this.unitsAvailable < 0 ? 0 : this.unitsAvailable;
	}

	/**
	 * Adds or subtracts the cash gain and resets to 0 if it goes negative
	 * @param units
	 */
	public void adjustCashGain(double gainToBeAdjusted) {
		this.cashGain += gainToBeAdjusted;
		// this.cashGain = this.cashGain < 0 ? 0 : this.cashGain;
	}

	/**
	 * Adds or subtracts the number of available units and if it goes negative, makes it 0
	 * @param units
	 */
	public void adjustSaleGain(double gainToBeAdjusted) {
		this.saleGain += gainToBeAdjusted;
	}
}
