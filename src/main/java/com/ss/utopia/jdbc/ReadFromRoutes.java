/**
 * 
 */
package com.ss.utopia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author ppradhan
 *
 */
public class ReadFromRoutes {

	public static final String driver= "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost/utopia";
	public static final String username = "root";
	public static final String password = "root";
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1. Register driver
		Class.forName(driver); // optional - it won't throw error. But it's a good practice.
		//2. Create connection
		Connection conn = DriverManager.getConnection(url, username, password);
		//3. Statement Object
//		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement("select * from route where origin_id = ?");
		System.out.println("Enter origin id to search: ");
		Scanner scan = new Scanner(System.in);
		String originId = scan.nextLine();
//		String sql = "Select * from route where origin_id = '"+originId+"'";
		pstmt.setString(1, originId);
		
		//4. Exec
//		ResultSet rs = stmt.executeQuery(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.println("Route ID: "+rs.getInt("id"));
			System.out.println("Route Origin ID: "+rs.getString("origin_id"));
			System.out.println("Route Dest ID: "+rs.getString("destination_id"));
			System.out.println("-------------");
		}
	}

}
