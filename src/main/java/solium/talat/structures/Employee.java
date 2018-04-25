package solium.talat.structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import solium.talat.activities.IActivity;

public class Employee {

	private String empId;
	private EmployeeStanding empStanding;
	private TreeMap<Date, List<IActivity>> mapActivities;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public EmployeeStanding getEmpStanding() {
		return this.empStanding;
	}

	public String getResourceId() {
		return getEmpId();
	}

	public TreeMap<Date, List<IActivity>> getActivities() {
		return mapActivities;
	}

	public void setActivities(TreeMap<Date, List<IActivity>> activities) {
		this.mapActivities = activities;
	}

	
	public Employee() {
		mapActivities = new TreeMap<>();
		empStanding = new EmployeeStanding();
	}
	
	public Employee(String empId) {
		this();
		this.empId = empId;
	}
	
	public Employee(String empId, IActivity activity) {
		this(empId);
		this.addActivity(activity);
	}
	
	/**
	 * Creates the Activities Map if it does not exist and adds an activity to the Activity Map 
	 * @param activity
	 */
	public void addActivity(IActivity activity){
		if(mapActivities == null) {
			mapActivities = new TreeMap<>();
		}
		
		if(!mapActivities.containsKey(activity.getActivityDate())) {
			mapActivities.put(activity.getActivityDate(), new ArrayList<>());
		}
		mapActivities.get(activity.getActivityDate()).add(activity);		
	}
}
