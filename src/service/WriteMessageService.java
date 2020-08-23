package service;

import java.sql.Connection;
import java.sql.SQLException;


import dao.MessageDAO;
import dao.MessageDaoProvider;
import jdbcUtil.JdbcUtil;
import loader.DBConnection;
import model.Message;

public class WriteMessageService {
	
	private static WriteMessageService instance = new WriteMessageService();
	
	private WriteMessageService() {}
	
	public static WriteMessageService getInstance( ) {
		return instance;
	}
	
	public void write(Message message) throws ServiceException {
		
		JudgeMessageValue(message);
		
		Connection conn = null;
		
		
		try {
			MessageDAO messageDao = 
					MessageDaoProvider.getInstance().getMessageDAO();
			
			conn = DBConnection.getConnection();
			messageDao.insert(conn, message);
		}catch(SQLException ex) {
			throw new ServiceException("메시지 등록 실패 : ", ex);
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	private void JudgeMessageValue(Message message) {
		if(message.getGuestName() == null || message.getGuestName().isEmpty()) {
			throw new IllegalArgumentException("Invalid guest name");
		}
	}
}
