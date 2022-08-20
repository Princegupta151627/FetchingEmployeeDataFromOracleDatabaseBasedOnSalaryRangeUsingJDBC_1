// This is a application for fetching the data from the oracle database using JDBC
import java.sql.DriverManager;
import java.sql.Connection;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class  select {
	public static void main(String[] args) 
	{
		Scanner scn = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			//read inputs
			scn = new Scanner(System.in);
			float startSalary=0.0f,endSalary=0.0f;
			if(scn!=null){
				System.out.println("Enter Start Range Emp Salary");
				startSalary = scn.nextFloat();
                System.out.println("Enter End Range Emp Salary");
                endSalary = scn.nextFloat();
			}
			//load JDBC Driver(optional)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establish The Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","lion");
			//create jdbc statement obj
			if(con!=null)
				st = con.createStatement();
				//prepare SQL Queries
				String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>="+startSalary+"AND SAL<="+endSalary;
				System.out.println(query);
				//send and execute Query in DB Software
			if(st!=null)
				rs=st.executeQuery(query);
                if(rs!=null){
					while(rs.next()==true){
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}
                }
			}//try end
           catch(SQLException se){
			se.printStackTrace();
           }
		   catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
           }
		   finally{
			       try{
					if(rs!=null)
						rs.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}


					try{
						if(st!=null)
							st.close();
					}
						catch(SQLException se){
						se.printStackTrace();
					   }


                 try{
						if(scn!=null)
							scn.close();
						}
						catch(Exception e){
						e.printStackTrace();
					   }
					}
			       }
}