import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Employee {                                                               //A basic class to store everything form the file
	int id;																			  
	int projectID;
	String begginingDate;
	String endindDate;
	
	public Employee(int id, int projectID, String begginingDate, String endingDate) {
		this.id = id;
		this.projectID = projectID;
		this.begginingDate = begginingDate;
		this.endindDate = endingDate;
	}

	public int getID() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getBegginingDate() {
		return begginingDate;
	}

	public void setBegginingDate(String begginingDate) {
		this.begginingDate = begginingDate;
	}

	public String getEndindDate() {
		return endindDate;
	}

	public void setEndindDate(String endindDate) {
		this.endindDate = endindDate;
	}
	
	public int getDaysWorkedTogether(String empBeginDate, String empEndDate) throws ParseException {  //A method to calculate how many days
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");								  //a couple has worked together
		Date currentDate = new Date();																  //(the couple must be checked if they
		String currDate = format.format(currentDate);												  //are working on the same project or it wont
		if(empEndDate.contentEquals("NULL")) { 														  //be correct)
			empEndDate = currDate;
		}
		if(this.endindDate.equals("NULL")) {
			this.endindDate = currDate;
		}
		
		Date thisEmpBeginDate = format.parse(this.begginingDate);
		Date thisEmpEndDate = format.parse(this.endindDate);
		Date otherEmpBeginDate = format.parse(empBeginDate);
		Date otherEmpEndDate = format.parse(empEndDate);
		
		
		long diff;
		
		if(thisEmpBeginDate.getTime() - otherEmpBeginDate.getTime() >= 0) {   						  // To see how much they have worked together 
			if(thisEmpEndDate.getTime() - otherEmpEndDate.getTime() <=0) {   						  // not how much they have worked on the same project
				diff = thisEmpEndDate.getTime() - thisEmpBeginDate.getTime();
				if ((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <=0) {
					return 0;
				}else {
					return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				}
			}else {
				diff = otherEmpEndDate.getTime() - thisEmpBeginDate.getTime();
				if ((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <=0) {
					return 0;
				}else {
					return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				}
			}
		}else {
			if(thisEmpEndDate.getTime() - otherEmpEndDate.getTime() <=0) {    
				diff = thisEmpEndDate.getTime() - otherEmpBeginDate.getTime() ;
				if ((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <=0) {
					return 0;
				}else {
					return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				}
			}else {
				diff = otherEmpEndDate.getTime() - otherEmpBeginDate.getTime() ;
				if ((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <=0) {
					return 0;
				}else {
					return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				}	
			}
		}	
	}
}
