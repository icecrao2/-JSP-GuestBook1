package dao;

public class MessageDaoProvider {
	
	private static MessageDaoProvider instance = new MessageDaoProvider();
	public static MessageDaoProvider getInstance() {
		return instance;
	}
	
	private MessageDaoProvider() {	}
	
	private MySQLMessageDAO mysqlDao = new MySQLMessageDAO();
	private MSSQLMessageDAO mssqlDao = new MSSQLMessageDAO();
	private OracleMessageDAO oracleDao = new OracleMessageDAO();
	private String dbms;
	
	public void setDbms(String Dbms) {this.dbms = Dbms;}
	
	public MessageDAO getMessageDAO() {
		if("oracle".equals(dbms))
			return oracleDao;
		else if ("mysql".equals(dbms))
			return mysqlDao;
		else if("mssql".equals(dbms))
			return mssqlDao;
		
		return null;
	}
}
