package com.revature.superhuman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SuperSQL {
	static String URL = "";
    static String USERNAME = "";
	static String PASSWORD = "";
	static String name = null;
	static String hometown = null;
	static String age = "0";
	static String alignment = null;
	
	public static void SQLConnect() {
		try {
			List<String> ls = Files.readAllLines(Paths.get("C:\\Users\\tojum\\Documents\\workspace-sts-3.9.11.RELEASE\\Superhuman2\\sql.properties"));
			URL = ls.get(0);
			USERNAME = ls.get(1);
			PASSWORD = ls.get(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			if(conn != null) {
				System.out.println("Connected to the database");
			}
			else {
				System.out.println("not connected");
			}
			conn.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addSuperHumans() {
		SQLConnect();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "insert into superhumans values ('" + name + "','" + hometown + "', '" + age + "', '" + alignment + "');";
			//System.out.println(sql);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
//			while(rs.next()) {
//				//System.out.println(rs.getString("name") + "\t" + rs.getString("price"));
//				//System.out.printf("%30s: %10d%n", rs.getString("name"), rs.getInt("price"));
//				System.out.printf("%20s %10s %5d %10s%n", rs.getString("superhumanname"), rs.getString("hometown"), rs.getInt("age"), rs.getString("alignment"));
//			}
//			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void displaySuperHumans() {
		SQLConnect();
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String sql = "select * from superhumans";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//System.out.println(rs.getString("name") + "\t" + rs.getString("price"));
				//System.out.printf("%30s: %10d%n", rs.getString("name"), rs.getInt("price"));
				System.out.printf("%20s %10s %5d %10s%n", rs.getString("superhumanname"), rs.getString("hometown"), rs.getInt("age"), rs.getString("alignment"));
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//
		addSuperHumans();	
	}
}