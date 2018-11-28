package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Accesser {
	private static String dbName = "loveberry";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/" + dbName;
	private static String user = "root";
	private static String pass = "root";

//	ライダーのラズパイ
	private static String raspDbName = "LOVE_BERRY";
	private static String raspDriverName = "com.mysql.jdbc.Driver";
	private static String raspUrl = "jdbc:mysql://10.15.154.63/" + raspDbName;
	private static String raspUser = "admin";
	private static String raspPass = "root";

//	ぐっちのラズパイ
//	private static String raspDbName = "LOVE_BERRY";
//	private static String raspDriverName = "com.mysql.jdbc.Driver";
//	private static String raspUrl = "jdbc:mysql://10.15.154.9/" + raspDbName;
//	private static String raspUser = "testuser";
//	private static String raspPass = "testuser";


	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getRaspConnection() {
		Connection con = null;
		try {
			Class.forName(raspDriverName);
			con = DriverManager.getConnection(raspUrl, raspUser, raspPass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}



	public void close(Connection con) {
		try {
			if (con != null) {
				con.close();
				System.out.println("切断しました");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, Statement stmt) {
		try {
			if (con != null) {
				con.close();
				System.out.println("切断しました");
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}