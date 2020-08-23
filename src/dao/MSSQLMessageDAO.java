package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import model.Message;


//구현되지 않음
public class MSSQLMessageDAO extends MessageDAO{

	@Override
	public int insert(Connection conn, Message message) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
