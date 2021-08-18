package db;
import java.sql.*;

public class Cp {
	public static Connection getCon()
	{
		String url="jdbc:oracle:thin:@localhost:1521:oracll";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url,"system","haritsharma16");
			//System.out.println("conndone");
			return con;
			
 		
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
		
	
	}
}
