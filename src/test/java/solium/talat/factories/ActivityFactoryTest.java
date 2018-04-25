package solium.talat.factories;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import solium.talat.activities.IActivity;
import solium.talat.activities.PerformanceActivity;
import solium.talat.activities.SaleActivity;
import solium.talat.activities.VestingActivity;

public class ActivityFactoryTest {

	@Test
	public void testClassifyActivity_PerfActivty() {
		String strActivtyType = "PERF";
		IActivity activity = ActivityFactory.classifyActivity(strActivtyType);

		assertThat(activity, instanceOf(PerformanceActivity.class));
	}

	@Test
	public void testClassifyActivity_VestActivty() {
		String strActivtyType = "VEST";
		IActivity activity = ActivityFactory.classifyActivity(strActivtyType);

		assertThat(activity, instanceOf(VestingActivity.class));
	}

	@Test
	public void testClassifyActivity_SaleActivty() {
		String strActivtyType = "SALE";
		IActivity activity = ActivityFactory.classifyActivity(strActivtyType);

		assertThat(activity, instanceOf(SaleActivity.class));
	}

	@Test
	public void testClassifyActivity_IllegalActivty() {
		String strActivtyType = "ABCD";
		IActivity activity = ActivityFactory.classifyActivity(strActivtyType);

		assertEquals(null, activity);
	}
	
	@Test
	public void testCreateActivity_LegalActivity(){
		String employeeId = "001Sol";
		String [] activityComponents = {
				"VEST",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.50) };
		
		IActivity activity = ActivityFactory.createActivity(activityComponents);
		
		assertThat(activity, not(equals(null)));
		assertThat(activity, instanceOf(VestingActivity.class));		
	}
	
	@Test
	public void testCreateActivity_IllegalActivity(){
		String employeeId = "001Sol";
		String [] activityComponents = {
				"ABCD",
				employeeId,
				"20120201",
				String.valueOf(1000),
				String.valueOf(0.50) };
		
		IActivity activity = ActivityFactory.createActivity(activityComponents);
		
		assertEquals(null,activity);		
	}
	
	@Test
	public void testCreateActivity_IllegalDate(){
		String employeeId = "001Sol";
		String [] activityComponents = {
				"ABCD",
				employeeId,
				"201202",
				String.valueOf(1000),
				String.valueOf(0.50) };
		
		IActivity activity = ActivityFactory.createActivity(activityComponents);
		
		assertEquals(null,activity);		
	}

}
