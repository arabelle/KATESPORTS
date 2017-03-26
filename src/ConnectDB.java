import java.sql.*;
import java.util.*;
import java.text.*;
	
public class ConnectDB {
	private static String username = "ora_xxxx@ug";
	private static String pwd = "xxxxxxxxx";
	
	
	
	public static void main(String args[]) throws SQLException, ParseException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", username, pwd);
		
		con.setAutoCommit(false);
		PreparedStatement ps = con.prepareStatement("INSERT INTO equipment VALUES (?, ?, ?)");
		
		ps.setInt(1, 200);
		ps.setString(2, "gear");
		ps.setString(3, "hockey stick");
		ps.executeUpdate();
		con.commit();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM equipment");
		while(rs.next())
		{
			System.out.println("ID: " + Integer.toString(rs.getInt(1)));
			System.out.println("Type: " + rs.getString("item_type"));
			System.out.println("Name: " + rs.getString("item_name"));
		}
	}
}
