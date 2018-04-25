package solium.talat.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import solium.talat.activities.IActivity;
import solium.talat.activities.VestingActivity;
import solium.talat.structures.SoliumData;

public class SoliumDataTest {

	SoliumData soliumData; 
	
	@Before
	public void before(){
		soliumData = SoliumData.getInstance();
		soliumData.getEmployeeData().clear();
	}
	
	@Test
	public void testUpdateEmployeeIndex_NoExistingEntry(){
		String employeeId = "113Sol";
		String [] activityComponents = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.50) };
		IActivity activity = new VestingActivity();
		activity.populateComponents(activityComponents);
		
		assertFalse(soliumData.getEmployeeData().containsKey(employeeId));
		soliumData.updateEmployeeIndex(employeeId, activity);
		assertTrue(soliumData.getEmployeeData().containsKey(employeeId));	
	}
	
	@Test
	public void testUpdateEmployeeIndex_ExistingEntry(){
		
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.50) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);
		
		String [] activityComponents2 = {
				"SALE",
				employeeId,
				"20120301",
				String.valueOf(500),
				String.valueOf(1.00) };
		IActivity activity2 = new VestingActivity();
		activity2.populateComponents(activityComponents2);
		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		assertEquals(1, soliumData.getEmployeeData().size());
		assertEquals(1, soliumData.getEmployeeData().get(employeeId).getActivities().size());
		
		soliumData.updateEmployeeIndex(employeeId, activity2);
		assertEquals(1, soliumData.getEmployeeData().size());
		assertEquals(2, soliumData.getEmployeeData().get(employeeId).getActivities().size());		
	}
	
	@Test
	public void testUpdateEmployeeIndex_ExistingEntryNullActivity(){
		
		String employeeId = "113Sol";
		String [] activityComponents1 = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.50) };
		IActivity activity1 = new VestingActivity();
		activity1.populateComponents(activityComponents1);
		
		IActivity activity2 = null;
		
		soliumData.updateEmployeeIndex(employeeId, activity1);
		assertEquals(1, soliumData.getEmployeeData().size());
		assertEquals(1, soliumData.getEmployeeData().get(employeeId).getActivities().size());
		
		soliumData.updateEmployeeIndex(employeeId, activity2);
		assertEquals(1, soliumData.getEmployeeData().size());
		assertEquals(1, soliumData.getEmployeeData().get(employeeId).getActivities().size());		
	}
}
