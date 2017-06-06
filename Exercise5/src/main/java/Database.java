import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {
	static Connection connection;
	private static final String DB_USER = "SA";
	private static final String DB_PASSWORD = "";
    static PreparedStatement preparedStatement ;
	public static Connection  connectToAndQueryDatabase() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			
		}
		System.out.println("MySQL JDBC Driver Registered!");
		// connection = null;

		try {
			connection = DriverManager
			.getConnection("jdbc:hsqldb:hsql://127.0.0.1:9001/test-db",DB_USER, DB_PASSWORD);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return connection ;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;

		}
	public static void createTableStudent() throws SQLException {
		String createTableSQL = "CREATE TABLE STUDENT("
				+ "pkey INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "name VARCHAR(35) NOT NULL, "
				+ "sex VARCHAR(20) NOT NULL, "
				+ "age INTEGER NOT NULL, "  
				+"level INTEGER NOT NULL,"
				+"PRIMARY KEY (pkey) "
				+ ")";

		try {
			preparedStatement = connection.prepareStatement(createTableSQL);
			System.out.println(createTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Table \"Student\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			
		}

	}
	public static void createTableEnrollment() throws SQLException {
		String createTableSQL = "CREATE TABLE ENROLLMENT("
				+ "fkey_student INTEGER NOT NULL, "
				+ "fkey_class INTEGER NOT NULL, "
				+ ")";

		try {
			preparedStatement = connection.prepareStatement(createTableSQL);
			System.out.println(createTableSQL);
			preparedStatement.executeUpdate();
			connection.prepareStatement("ALTER TABLE ENROLLMENT"
										+"ADD FOREIGN KEY(fkey_student)REFERENCES STUDENT (pkey)").executeUpdate();
			connection.prepareStatement("ALTER TABLE ENROLLMENT"
										+"ADD FOREIGN KEY(fkey_class)REFERENCES CLASS(pkey)").executeUpdate();
			System.out.println("Table \"dbuser\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
		
		}

	}
	public static void createTableClass() throws SQLException {
		String createTableSQL = "CREATE TABLE CLASS("
				+ "pkey INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1), "
				+ "name VARCHAR(35) NOT NULL, "
				+ "fkey_faculty INTEGER NOT NULL,"
				+"PRIMARY KEY(pkey)"
				+ ")";

		try {
			preparedStatement = connection.prepareStatement(createTableSQL);
			System.out.println(createTableSQL);
			//preparedStatement.executeUpdate();
			connection.prepareStatement("ALTER TABLE CLASS "
										+"ADD FOREIGN KEY (fkey_faculty) REFERENCES FACULTY (pkey_fa)").executeUpdate();
			System.out.println("Table \"dbuser\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			
		}

	}
	public static void createTableFaculty() throws SQLException {
		String createTableSQL = "CREATE TABLE FACULTY ("
				+ "pkey_fa INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1), "
				+ "name VARCHAR(35) NOT NULL, "
				+"PRIMARY KEY(pkey_fa)"
				+ ")";

		try {
			preparedStatement = connection.prepareStatement(createTableSQL);
			//System.out.println(createTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Table \"Faculty\" is created!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			

		}

	}
	
	 public static void dropTabless() {
	        try {
	            connection.prepareStatement("DROP TABLE ENROLLMENT").executeUpdate();
	            connection.prepareStatement("DROP TABLE STUDENT").executeUpdate();
	            connection.prepareStatement("DROP TABLE CLASS").executeUpdate();
	            connection.prepareStatement("DROP TABLE FACULTY").executeUpdate();

	           System.out.println("tables dropped successfully");
	        } catch (SQLException e) {
	        }
	    }
	 public static void dropConnection() throws SQLException{
		 if(connection!=null){
			 connection.close();
		 }
		 if(preparedStatement!=null){
			 preparedStatement.close();
		 }
	 }
	
	public static void insertToStudent(String name,String sex,int age,int level)throws SQLException{
	

		String insertTableSQL = "INSERT INTO STUDENT"
				+ "(name, sex, age,level) VALUES("
				+ "'"+name+"',"+"'"+sex+"',"+"'"+age+"',"+"'"+level+"'"+")";

		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
		

		}
	}
	public static void insertToEnrollment(int fk_student,int fk_class)throws SQLException{
		

		String insertTableSQL = "INSERT INTO ENROLLMENT"
				+ "(fkey_student, fkey_class) VALUES"
				+ "("+"'"+fk_student+"',"+"'"+fk_class+"'"+")";

		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			

		}
	}
	public static void insertToClass(String name,int fkey_faculty)throws SQLException{
		

		String insertTableSQL = "INSERT INTO CLASS"
				+ "( name, fkey_faculty) VALUES"
				+ "("+"'"+name+"',"+"'"+fkey_faculty+"'"+")";

		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			

		}
	}
	public static void insertToFaculty(String name)throws SQLException{
		

		String insertTableSQL = "INSERT INTO FACULTY"
				+ "(name) VALUES"
				+ "("+"'"+name+"'"+")";

		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			

		}
	}
 public static void queryStudents(){
     String result = null;
     String selectString;
     selectString = "select * from STUDENT";
     result ="pkey\t\tname\n";
     try {
         preparedStatement = connection.prepareStatement(selectString);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
             int id = rs.getInt("pkey");
             String name = rs.getString("name");
             result+=id+"\t\t"+ name+"\n";
             System.out.println("Wynik zapytania <1>"+id+" "+name);

         }
        

     } catch(SQLException ex) {
         System.err.println("SQLException: " + ex.getMessage());
     }

		 
	 }
 public static void queryPeopleWithoutSubject(){
     String result = null;
     String selectString;
     selectString = "select * from STUDENT "+"LEFT JOIN ENROLLMENT on STUDENT.pkey=ENROLLMENT.fkey_student"+"WHERE ENROLLMENT.fkey_student is NULL";
     result ="pkey\t\tname\n";
     try {
         preparedStatement = connection.prepareStatement(selectString);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
             int id = rs.getInt("pkey");
             String name = rs.getString("name");
             result+=id+"\t\t"+ name+"\n";
             System.out.println("Wynik zapytania <2>"+id+" "+name);

         }
       

     } catch(SQLException ex) {
         System.err.println("SQLException: " + ex.getMessage());
     }

		 
	 }
 public static void queryWomensEgsistial(){
     String result = null;
     String selectString;
     selectString = "select * from STUDENT"+"LEFT JOIN ENROLLMENT on STUDENT.pkey=ENROLLMENT.fkey_student"+"WHERE ENROLLMENT.fkey_class=1002"+"AND STUDENT.sex LIKE 'female'";
     result ="pkey\t\tname\n";
     try {
         preparedStatement = connection.prepareStatement(selectString);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
             int id = rs.getInt("pkey");
             String name = rs.getString("name");
             result+=id+"\t\t"+ name+"\n";
             System.out.println("Wynik zapytania <3>"+result);

         }
     

     } catch(SQLException ex) {
         System.err.println("SQLException: " + ex.getMessage());
     }

		 
	 }
 public static void queryFacultyWithoutSigns(){
     String result = null;
     String selectString;
     selectString = "select * from FACULTY"+"JOIN CLASS on FACULTY.pkey=CLASS.fkey_faculty"+"LEFT JOIN ENROLLMENT on CLASS.pkey=ENROLLMENT.fkey_class"+"WHERE ENROLLMENT.fkey_class IS NULL";
     result ="name\n";
     try {
         preparedStatement = connection.prepareStatement(selectString);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
             String name = rs.getString("name");
             result+=name+"\n";
             System.out.println("Wynik zapytania <4>"+result);

         }
     

     } catch(SQLException ex) {
         System.err.println("SQLException: " + ex.getMessage());
     }

		 
	 }
 public static void queryMaxAgeOnLaw(){
     String result = null;
     String selectString;
     selectString = "select MAX(age) from STUDENT";
     result ="name\n";
     try {
         preparedStatement = connection.prepareStatement(selectString);
         ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
             String name = rs.getString("name");
             result+=name+"\n";
             System.out.println("Wynik zapytania <5>"+result);
         }
       

     } catch(SQLException ex) {
         System.err.println("SQLException: " + ex.getMessage());
     }

		 
	 }

}
