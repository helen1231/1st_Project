package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnection {

	/*
	public static Connection getConnection()throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@192.168.100.121:1521:xe";
		String user="project02";
		String pwd="project";
		Connection conn = DriverManager.getConnection(url,user,pwd);
		return conn;
		
	}
	*/
	
	public static Connection getConnection() throws Exception{

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@javaking.iptime.org:1521:orcl";
		String user = "test10";
		String pwd = "test";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
	
}
