package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbcUtil.JdbcUtil;
import model.Message;

public class OracleMessageDAO extends MessageDAO{

	@Override
	public int insert(Connection conn, Message message) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into guestbook_message" + 
							"(message_id, guest_name, password, message)"+
							" values (MESSAGE_ID_SEQUENCE.nextval, ?, ?, ?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		}
		finally {
			JdbcUtil.close(pstmt);
		}
	}

	@Override
	public List<Message> selectList(Connection conn, int firstRow, int endRow) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("SELECT * FROM guestbook_message " + 
										" WHERE rownum >= ? and rownum <= ?");
			pstmt.setInt(1,  firstRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Message> messageList = new ArrayList<Message>();
				do {
					messageList.add(super.makeMessageFromResultSet(rs));
				} while(rs.next());
				
				return messageList;
			}
			else {
				return Collections.emptyList();
			}
		}
		finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		
	}
	
	
}
