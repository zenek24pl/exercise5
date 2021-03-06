import java.sql.SQLException;



/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {
	
	 public static void main(String[] args) {

	        Database.connectToAndQueryDatabase();
	        try {
	        	
	        	Database.dropTabless();
				Database.createTableStudent();
				Database.createTableFaculty();
				Database.createTableClass();;
				Database.createTableEnrollment();
								
				Database.insertToStudent("John Smith","male",23,2);
				Database.insertToStudent("Rebecca Milson","female",27,3);
				Database.insertToStudent("George Heartbreaker","male",19,1);
				Database.insertToStudent("Deepika Chopra","female",25,3);
				
				Database.insertToFaculty("Engineering");
				Database.insertToFaculty("Philosophy");
				Database.insertToFaculty("Law and administration");
				Database.insertToFaculty("Languages");
				
				Database.insertToEnrollment(1, 1000);
				Database.insertToEnrollment(1, 1002);
				Database.insertToEnrollment(1, 1003);
				Database.insertToEnrollment(1, 1004);
				Database.insertToEnrollment(2, 1002);
				Database.insertToEnrollment(2, 1003);
				Database.insertToEnrollment(4, 1000);
				Database.insertToEnrollment(4, 1002);
				Database.insertToEnrollment(4, 1003);
				
				Database.insertToClass("Introduction to labour law", 102);
				Database.insertToClass("Graph algorithms", 100);
				Database.insertToClass("Existentialism in 20th century", 101);
				Database.insertToClass("English grammar", 103);
				Database.insertToClass("From Plato to Kant", 101);

				Database.queryStudents();
				Database.queryPeopleWithoutSubject();
				Database.queryFacultyWithoutSigns();
				Database.queryWomensEgsistial();
				
				Database.dropConnection();


				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	    }
	
}
