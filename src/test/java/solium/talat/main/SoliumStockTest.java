package solium.talat.main;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import solium.talat.activities.IActivity;
import solium.talat.activities.PerformanceActivity;
import solium.talat.activities.SaleActivity;
import solium.talat.activities.VestingActivity;
import solium.talat.structures.ComputeParams;
import solium.talat.structures.SoliumData;

public class SoliumStockTest {
	
	SoliumStock soliumStock ;
	SoliumData soliumData; 
	ComputeParams computeParams ;
	
	@SuppressWarnings("deprecation")
	@Before
	public void before(){
		soliumStock = new SoliumStock();
		soliumData = SoliumData.getInstance();
		soliumData.getEmployeeData().clear();
		computeParams = new ComputeParams(new Date(2014-1900,01,02), 1.00); // Feb 2nd, 2014
	}
	
	/**
	 * Check the vesting activity
	 */
	@Test
	public void testComputeGain_OnlyVest(){
		
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		soliumStock.computeGain(soliumData,computeParams);
		
		double expectedValue = 600.00 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getCashGain();
		
		assertEquals(expectedValue, returnedValue, 0.00);
	}
	
	/**
	 * Check the vesting and perform activity of an employee
	 */
	@Test
	public void testComputeGain_VestAndPerform(){
		
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents2 = {
				"PERF",
				employeeId,
				"20120301",
				String.valueOf(2)};
		IActivity activity2 = new PerformanceActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
		
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 2000 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getUnitsAvailable();
		
		assertEquals(expectedValue, returnedValue, 0.00);
	}
	
	/**
	 * Check for vesting and sale activity - number of units available 
	 * are more than/equal to what the user wants to sell 
	 */
	@Test
	public void testComputeGain_VestAndSale_LegalLimits(){
		
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(400),
				String.valueOf(1.5)};
		IActivity activity2 = new SaleActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
		
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 440.00 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getSaleGain();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);
		
		expectedValue = 360.00 ;
		returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getCashGain();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);
	}

	
	/**
	 * Check for vesting and sale, when the number of available units
	 * are less than the employee wants to sell, ignore the activity
	 */
	@Test
	public void testComputeGain_VestAndSale_IllegalLimits(){
		
		// Selling more than the available units
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(2000),
				String.valueOf(1.5)};
		IActivity activity2 = new SaleActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
		
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 1000 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getUnitsAvailable();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);		
	}
	
	/**
	 * Check for Vesting, Sale and Performance Activity
	 */
	@Test
	public void testComputeGain_VestSalePerf(){
		
		// Selling more than the available units
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(400),
				String.valueOf(1.5)};
		IActivity activity2 = new SaleActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
		
		String [] activityComponents3 = {
				"PERF",
				employeeId,
				"20120401",
				String.valueOf(2)};
		IActivity activity3 = new PerformanceActivity();
		activity3.populateComponents(activityComponents3);
		soliumData.updateEmployeeIndex(employeeId, activity3);
		
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 1200 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getUnitsAvailable();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);		
	}
	

	/**
	 * To check if the computations are chrnological 
	 * irrespective of the order the activities are available
	 */
	@Test
	public void testComputeGain_VestSalePerf_ChronologicalCompute(){
		
		// Selling more than the available units
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents3 = {
				"PERF",
				employeeId,
				"20120401",
				String.valueOf(2)};
		IActivity activity3 = new PerformanceActivity();
		activity3.populateComponents(activityComponents3);
		soliumData.updateEmployeeIndex(employeeId, activity3);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(400),
				String.valueOf(1.5)};
		IActivity activity2 = new SaleActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
				
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 1200 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getUnitsAvailable();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);		
	}
	
	/**
	 * To see if the parameters are changed, does it compute only upto the final date in params
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testComputeGain_VestSalePerf_DifferentParams(){
		
		computeParams = new ComputeParams(new Date(2012-1900,02,05),1.00); // March 5, 2012
		
		// Selling more than the available units
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.40) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(400),
				String.valueOf(1.5)};
		IActivity activity2 = new SaleActivity();
		activity2.populateComponents(activityComponents2);
		soliumData.updateEmployeeIndex(employeeId, activity2);
		
		// This activity (activity3) will not be considered
		String [] activityComponents3 = {
				"PERF",
				employeeId,
				"20120401",
				String.valueOf(2)};
		IActivity activity3 = new PerformanceActivity();
		activity3.populateComponents(activityComponents3);
		soliumData.updateEmployeeIndex(employeeId, activity3);
		
		soliumStock.computeGain(soliumData, computeParams);
		
		double expectedValue = 600 ;
		double returnedValue = soliumData.getEmployeeData().get(employeeId).getEmpStanding().getUnitsAvailable();
		
		assertEquals(expectedValue, returnedValue, 0.000000001);
	}
}
