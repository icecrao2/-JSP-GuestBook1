package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jdbcUtil.JdbcUtil;
import model.Message;


public abstract class MessageDAO {
	
	public abstract int insert(Connection conn, Message message) throws SQLException;
	
	public Message select(Connection conn, int messageId) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				return makeMessageFromResultSet(rs);
			else {
				return null;
			}
		}
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
	}
	
	protected Message makeMessageFromResultSet(ResultSet rs) throws SQLException
	{
		Message message =  new Message();
		message.setId(rs.getInt("message_id"));
		message.setGuestName(rs.getString("guest_name"));
		message.setPassword(rs.getString("password"));
		message.setMessage(rs.getString("message"));
		return message;
	}
	
	public int selectCount(Connection conn) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from guestbook_message");
			rs.next();
			return rs.getInt(1);
		}
		finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(rs);
		}
	}
	
	public abstract List<Message> selectList(Connection conn, int firstRow, int endRow) 
			throws SQLException;
	
	public int delete(Connection conn, int messageId) throws SQLException {
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("delete from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			return pstmt.executeUpdate();
		}
		finally {
			JdbcUtil.close(pstmt);
		}
	}
}
