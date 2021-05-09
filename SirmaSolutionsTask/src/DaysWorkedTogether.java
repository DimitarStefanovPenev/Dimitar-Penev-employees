
public class DaysWorkedTogether {														//A class to store the already calculated 
	int empID1;																			//days a couple has worked on a similar project
	int empID2;
	int projectID;
	int daysWorked;
	
	public DaysWorkedTogether(int empID1, int empID2, int projectID, int daysWorked) {
		this.empID1 = empID1;
		this.empID2 = empID2;
		this.projectID = projectID;
		this.daysWorked = daysWorked;
	}

	public int getEmpID1() {
		return empID1;
	}

	public void setEmpID1(int empID1) {
		this.empID1 = empID1;
	}

	public int getEmpID2() {
		return empID2;
	}

	public void setEmpID2(int empID2) {
		this.empID2 = empID2;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public int getDaysWorked() {
		return daysWorked;
	}

	public void setDaysWorked(int daysWorked) {
		this.daysWorked = daysWorked;
	}
	
	public void printALL() {																	//Unused method in the code but was
		System.out.println("Employee with id "													//helpfull when testing
					+ this.empID1 + " has worked with employee with id "
					+ this.empID2 + " on project "
					+ this.projectID + " for "
					+ this.daysWorked + " days."); 
	}
	
	
	
}
