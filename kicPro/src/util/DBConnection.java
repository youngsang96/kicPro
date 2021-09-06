package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static Connection getConnection() {
		 Connection con=null;
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "yohesa";
		String passwd = "159951";
	    con = DriverManager.getConnection(url, userId, passwd);
		} catch (Exception e) {	    	e.printStackTrace();
	    }		
		return con;
	}

	public static void close(Connection con, Statement stmt, ResultSet rs ) {
		
			try {
				if(rs !=null) rs.close();
				if(stmt !=null) stmt.close();
				if(con !=null) {
					con.commit(); con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
}
