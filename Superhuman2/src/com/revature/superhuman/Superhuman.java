package com.revature.superhuman;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Superhuman
 */
@WebServlet("/submission")
public class Superhuman extends HttpServlet {
	
	public static void main(String[] args) {
		System.out.println("hello world");
	}
	
	static String URL = "";
    static String USERNAME = "";
	static String PASSWORD = "";
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Superhuman() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("superhumanname");
		String hometown = request.getParameter("hometown");
		String age = request.getParameter("age");
		String alignment = request.getParameter("alignment");
		PrintWriter writer = response.getWriter();
		writer.println(name);
		writer.println(hometown);
		writer.println(age);
		writer.println(alignment);
//		SuperSQL.name = name;
//		SuperSQL.hometown = hometown;
//		SuperSQL.age = age;
//		SuperSQL.alignment = alignment;
		try {
			List<String> ls = Files.readAllLines(Paths.get("C:\\Users\\tojum\\Documents\\workspace-sts-3.9.11.RELEASE\\Superhuman2\\sql.properties"));
			URL = ls.get(0);
			USERNAME = ls.get(1);
			PASSWORD = ls.get(2);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
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
		//doGet(request, response);
	}
}