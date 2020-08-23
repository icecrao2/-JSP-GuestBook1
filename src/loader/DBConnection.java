package loader;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import service.ServiceException;

public class DBConnection {

	 public static Connection getConnection() throws ServiceException
	 {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/orcl");
			Connection con = dataSource.getConnection(); 
			return con;
		}
		catch(Exception ex) {
			throw new ServiceException("연결 실패 : " + ex, ex);
		}
	 } 
    
}
