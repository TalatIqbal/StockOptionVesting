package solium.talat.structures;

import java.util.TreeMap;

import solium.talat.activities.IActivity;

public class SoliumData {

	private TreeMap<String, Employee> employeeIndex;
	private static SoliumData instance = null;
	
	public TreeMap<String, Employee> getEmployeeData() {
		return employeeIndex;
	}

	private SoliumData() {
		this.employeeIndex = new TreeMap<>();
	}

	/**
	 * returns the only instance for SoliumData
	 * @return SoliumData instance
	 */
	public static SoliumData getInstance() {
		if (instance == null) {
			instance = new SoliumData();
		}
		return instance;
	}

	/**
	 * adds the non-existing employee to the index map, if the employee exists,<br> 
	 * updates the employee index map and adds a valid activity to the map, 
	 * 
	 * @param employeeId
	 * @param activity
	 */
	public void updateEmployeeIndex(String employeeId, IActivity activity) {
		
		if (!employeeIndex.containsKey(employeeId)) {
			Employee employee = new Employee(employeeId);
			employeeIndex.put(employeeId, employee);
		}
		if(activity != null) {
			employeeIndex.get(employeeId).addActivity(activity);
		}
	}
}
