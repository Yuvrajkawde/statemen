

//assingment based on 26 nov 2022 lecture java version 17 database used mysql workbench 8.0 code is working on my machine


package in.ineuron.school;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student 
{
	
	String url ="jdbc:mysql://localhost:3306/school";
	String username = "root";
	String password = "Root,12345";
	
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;

	
	
	//=============================SELECT Method========================================================================	
	
	
	public void select() throws SQLException 
	{
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query
	   	        	String sqlSelectQuery = "select sid,sname,sage,saddr from student";
	   	        	resultset = statement.executeQuery(sqlSelectQuery);
	   	        	if(resultset!=null) 
		   	        {
	   	        		
	   	        		// process the result to get the dta
	   	        		System.out.println("SID\tSNAME\tSAGE\tSADDR");
	   	        		System.out.println("==============================");
	   	        		while(resultset.next()) 
	   	        		{
	   	        			int sid = resultset.getInt("sid");
	   	        			String sname= resultset.getString("sname");
	   	        			int sage = resultset.getInt("sage");
	   	        			String saddr = resultset.getString("saddr");
	   	        			System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddr);
	   	        		}
		   	        }
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	    
	}
	
	
	//=============================INSERT Method========================================================================
	
	
	public void insert() throws SQLException
	{
		
		Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter sage: ");
	    int sage = scanner.nextInt();
	    
	    System.out.println("Enter sname: ");
	    String sname = scanner.next();
	    
	    System.out.println("Enter saddress: ");
	    String saddr = scanner.next();
	    //sname = "'" + sname + "'"; traditional approch
	   // saddr = "'" + saddr + "'";
	    
	    try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String InsertSqlQuery = String.format("insert into student(sname,sage,saddr) values ('%s',%d,'%s')",sname,sage,saddr);
	   	        	/*/ traditional approch( +sname+","+sage+","+saddr+")";//"insert into student values(5,'yuvraj',29,'mi')";//"insert into student('sid','sname','sage','saddr') values(5,'yuvraj',29,'mi')";*/
	   	        	System.out.println(InsertSqlQuery);
	   	        	int noOfRows = statement.executeUpdate(InsertSqlQuery);
		   	      System.out.println("number of rows effected :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	       if(scanner!=null) 
	       {
	    	   scanner.close();
	       }
	  	   
	    }

	}
//=============================DELETE Method========================================================================
	
	public void delete()throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter student id to delete ");
		int sid = scanner.nextInt();
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String DeleteSqlQuery = String.format("delete from student where sid=%d",sid);
	   	        	int noOfRows = statement.executeUpdate(DeleteSqlQuery);
		   	      System.out.println("number of rows Deleted :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	}
//=====================update method======================================================
	public void update()throws SQLException
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("enter student name to update ");
		String  sname = scanner.next();
		
		System.out.println("enter student id to update ");
		int  sid = scanner.nextInt();
		
		try 
	    {
	    	connection = DriverManager.getConnection(url, username, password);
	    	if(connection!=null) 
	    	{
	    		//cretating statement object (to move the location using connection)
	   	        statement= connection.createStatement();
	   	        if(statement!=null) 
	   	        {
	   	        	//using statment object execut query      //
	   	        	String UpdateSqlQuery = String.format("update student set sname='%s' where sid=%d",sname,sid);
	   	        	int noOfRows = statement.executeUpdate(UpdateSqlQuery);
		   	      System.out.println("number of rows Updated :"+ noOfRows);
	   	        }
	    	}
	    }
	    catch(SQLException se)
	    {
	    	se.printStackTrace();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally 
	    {
	       if(resultset!=null)
	       {
	    	   resultset.close();
	       }
	       if(statement!=null) 
	       {
	    	   statement.close();
	       }
	       if(connection!=null) 
	       {
	    	   connection.close();
	       }
	  	   
	    }
	}
	
	public static void main(String[] args) throws SQLException
	{
		int operation = 0;
		Student std = new Student();
		//std.select();
		//std.insert();
		//std.delete();
		//std.update();
		System.out.println("please select your operation...... ");
		Scanner scan = new Scanner(System.in);
		
		System.out.println("to see data from student table press 1");
		System.out.println("to insert data into student table press 2");
		System.out.println("to delete data from student table press 3");
		System.out.println("to update data in student table press 4");
		operation = scan.nextInt();
		switch(operation) 
		{
		    case 1:
			    std.select();
			    break;
		    case 2:
				std.insert();
				break;
		    case 3:
				std.delete();
				break;
		    case 4:
		    	std.update();
		    	break;
		    	
		    default:
		    	System.out.println("please give proper input");
		    	std.main(args);
		    	break;
			
		}
		
	}

}
