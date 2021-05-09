import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainClass {

	
	public static void main(String[] args) throws ParseException {
		ArrayList<Integer> idsRep = new ArrayList<Integer>();
		ArrayList<Integer> projectIDsRep = new ArrayList<Integer>();
		ArrayList<String> dateBegin = new ArrayList<String>();
		ArrayList<String> dateEnd = new ArrayList<String>();
		
		BufferedReader br = null;												//Reading the .txt file and writing down 
		   try {																//all the info down								
		      String currentLine;
		      br = new BufferedReader(new FileReader("fileName.txt"));
		      while ((currentLine = br.readLine()) != null) {
		       //System.out.println(currentLine);
		       String[] parts = currentLine.split(", ");
		       idsRep.add(Integer.parseInt(parts[0]));							//writing it down so making an array later of an object
		       projectIDsRep.add(Integer.parseInt(parts[1]));
		       dateBegin.add(parts[2]);
		       dateEnd.add(parts[3]);
		      }
		    } catch (IOException e) {
		         e.printStackTrace();
		    } finally {
		        try {
		            if (br != null)br.close();
		         } catch (IOException ex) {
		            ex.printStackTrace();
		     }
		   }
		   
		   Employee[] employee = new Employee[idsRep.size()];					//Employee is the class I use to write all the info from
		   																		//the file to an easily accessed place
		   for(int i = 0; i< idsRep.size(); i++) {								 					
			   employee[i] = new Employee(idsRep.get(i), projectIDsRep.get(i)	
					   , dateBegin.get(i), dateEnd.get(i));
		   }		
		   
		   ArrayList<DaysWorkedTogether> arraylist = new ArrayList<DaysWorkedTogether>(); 	//Those arrays are used to store how many hours 
		   ArrayList<DaysWorkedTogether> arraylist2 = new ArrayList<DaysWorkedTogether>();	//a specific couple has worked on a specific project
		   																					//the second array is the same but will be used 
		   																					//later so I can delete stuff from it
		   																					//making everything easier later in the code
		   
		   int days=0;																		//This integer is used to save space in the code
		   																					
		   for (int id1 = 0; id1 < ((idsRep.size()) - 1); id1++) {
			   for(int id2 = id1+1; id2<idsRep.size(); id2++) {
				   if(employee[id1].getProjectID() == employee[id2].getProjectID()) {
					   days = employee[id1].getDaysWorkedTogether(employee[id2].getBegginingDate(),
							   employee[id2].getEndindDate());
					   arraylist.add(new DaysWorkedTogether(employee[id1].getID(),
							   employee[id2].getID(), employee[id1].getProjectID(), days)); 
					   arraylist2.add(new DaysWorkedTogether(employee[id1].getID(),
							   employee[id2].getID(), employee[id1].getProjectID(), days)); 
				   } 
			   }
		   }
		   
		   
		   arraylist2.remove(0);																//Now i make a third arraylist so i can finally 
		   days = 0;																			//calculate how much time a couple has spent
		   ArrayList<CoupleWorkedTogether> arraylist3 = new ArrayList<CoupleWorkedTogether>();  //working together at all and storing it inside
		   int id2Saved=-1;																		//a third class so calculating which couple has
		   for(int i=0; i<arraylist.size(); i++) {												//worked together most easier
			   for(int j=0; j<arraylist2.size(); j++) {
				   days = arraylist.get(i).getDaysWorked();
				   
				   if(((arraylist.get(i).getEmpID1() == arraylist2.get(j).getEmpID1() && 
						   arraylist.get(i).getEmpID2() == arraylist2.get(j).getEmpID2()) ||
					   (arraylist.get(i).getEmpID2() == arraylist2.get(j).getEmpID1() && 
					       arraylist.get(i).getEmpID1() == arraylist2.get(j).getEmpID2())) &&
						arraylist.get(i).getProjectID() != arraylist2.get(j).getProjectID()) {
					   
					   
					   days = days + arraylist2.get(j).getDaysWorked();
					   if (arraylist.get(i).getEmpID1() == arraylist2.get(j).getEmpID1()) {
						   id2Saved = arraylist.get(i).getEmpID2();
					   }else {
						   id2Saved = arraylist.get(i).getEmpID1();
					   }
					   arraylist2.remove(j);
					   j--;
				   }
				   
			   }
			   if (days != 0) {
				   arraylist3.add(new CoupleWorkedTogether(arraylist.get(i).getEmpID1(), 
						   arraylist.get(i).getEmpID2(), days));
			   }
			   id2Saved = -1;
			   days=0;
		   }
		   	
		   for (int i=0; i<arraylist3.size(); i++) {											//This loop just deletes if there are 			
			  for (int j=i+1; j<arraylist3.size(); j++) {										//repetitive couples in the last class array
				  
				  if((arraylist3.get(i).getEmpID1() == arraylist3.get(j).getEmpID1() && 
						   arraylist3.get(i).getEmpID2() == arraylist3.get(j).getEmpID2()) ||
					   (arraylist3.get(i).getEmpID2() == arraylist3.get(j).getEmpID1() && 
					       arraylist3.get(i).getEmpID1() == arraylist3.get(j).getEmpID2())) {
					  
					  int daysDifference = arraylist3.get(i).getWorkedFor() - 
							  arraylist3.get(j).getWorkedFor();
					  if(daysDifference >= 0) {
						  arraylist3.remove(j);
					  }else {
						  arraylist3.remove(i);
					  }
					  j--;
				  }  
			  }
		   }
		   
		   int storedHours = 0;
		   for (int i=0; i<arraylist3.size(); i++) {
			   if(arraylist3.get(i).getWorkedFor() >= storedHours) {
				   storedHours = arraylist3.get(i).getWorkedFor();
			   }
		   }
		   for (int i=0; i<arraylist3.size(); i++) {
			   if (arraylist3.get(i).getWorkedFor() == storedHours){
				   System.out.println("The couple who has worked the most days together is:\n" + 
						   				"Employee with id "+ arraylist3.get(i).getEmpID1() + 
						   				" with employee with id " + arraylist3.get(i).getEmpID2() + 
						   				" for a total of "+ arraylist3.get(i).getWorkedFor() + " days");
			   }
		   }
		   
		   
	}

	private static Object arraylist(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
