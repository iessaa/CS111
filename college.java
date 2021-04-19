import java.sql.*;
import java.util.Scanner;

public class college
{
	private Connection connect() {
		// SQLite connection string
		String url = "jdbc:sqlite:/home/grad12/eimhmed/workspace/db/college.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public void selectAll()
	{
		String sql = "SELECT * from professor";
		try
		{
			Connection conn = this.connect();
			Statement stmt  = conn.createStatement();
			ResultSet rslt  = stmt.executeQuery(sql);
			System.out.println("Faculty ID\tName\tDegree");
			while (rslt.next()) {
				System.out.println(rslt.getInt("faculty_id") +  "\t" +
						rslt.getString("name") + "\t" +
						rslt.getString("degree"));
			}
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void insert(int faculty_id, String name, String degree)
	{

		String sql = "INSERT OR IGNORE INTO professor VALUES(?,?,?)";
		try
		{
			Connection conn = this.connect();
			PreparedStatement sqlstmt = conn.prepareStatement(sql);
			sqlstmt.setInt(1, faculty_id);
			sqlstmt.setString(2, name);
			sqlstmt.setString(3, degree);
			sqlstmt.executeUpdate();
		}catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		college c = new college();
		Scanner in = new Scanner(System.in);
		System.out.print("How many professor record you want to store:\t");
		int n  = in.nextInt();
		for(int i = 0; i < n; i++)
		{
			System.out.println("Please Enter the information of the new professor");
			System.out.print("Faculty ID:\t"); int faculty_id = in.nextInt();
			System.out.print("Name:\t"); String name = in.next();
			System.out.print("Degree:\t"); String degree = in.next();
			c.insert(faculty_id, name, degree);
			System.out.println("The information of the professor you just entered has been stored into the database");

		}

		c.selectAll();	   
	}
}
