
public class CoupleWorkedTogether {											//Final class to store the completely calculated time a couple
	int empID1;																//has spent together working
	int empID2;
	int workedFor;
	
	public CoupleWorkedTogether(int empID1, int empID2, int workedFor) {
		this.empID1 = empID1;
		this.empID2 = empID2;
		this.workedFor = workedFor;
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

	public int getWorkedFor() {
		return workedFor;
	}

	public void setWorkedFor(int workedFor) {
		this.workedFor = workedFor;
	}
	
	
}
