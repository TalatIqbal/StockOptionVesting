package solium.talat.main;

import solium.talat.exception.StockVestIOException;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.SoliumData;

public class StockOptionVesting {

	public static void main(String[] args) throws StockVestIOException {
		

		SoliumData soliumData = SoliumData.getInstance();
		ComputeParams computeParams = new ComputeParams();
		
		SoliumStock soliumStock = new SoliumStock();
		computeParams = soliumStock.readInput(soliumData);
		soliumStock.computeGain(soliumData,computeParams);
		soliumStock.displayOutput(soliumData);
	}
	

}
