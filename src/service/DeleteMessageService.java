package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.MessageDAO;
import dao.MessageDaoProvider;
import jdbcUtil.JdbcUtil;
import loader.DBConnection;
import model.Message;

public class DeleteMessageService {
	
	private static DeleteMessageService instance = new DeleteMessageService();
	private DeleteMessageService() {	}
	
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	public void delete(int messageId, String password) throws ServiceException {
		
		Connection conn = null;
		MessageDAO messageDao = 
				MessageDaoProvider.getInstance().getMessageDAO();
		
		try {
			conn = DBConnection.getConnection();
			
			conn.setAutoCommit(false);
			
			Message message = messageDao.select(conn, messageId);
			
			
			JudgeMessageValue(message, password);

			messageDao.delete(conn, messageId);
			
			conn.commit();
			
		}catch(SQLException ex) {
			JdbcUtil.rollback(conn);
			throw new ServiceException("���� ó���� ������ �߻��߽��ϴ�.");
		}catch(ServiceException ex) {
			JdbcUtil.rollback(conn);
			throw ex;
		}finally {
			if(conn != null) {
				try {
					conn.setAutoCommit(false);
				}catch(SQLException e) {
					
				}
				JdbcUtil.close(conn);
			}
		}
		
	}
	
	private void JudgeMessageValue(Message message, String password) throws ServiceException {
		
		if(message == null)
			throw new ServiceException("message�� �������� �ʽ��ϴ�.");
		else if(!message.hasPassword())
			throw new ServiceException("��й�ȣ�� �������� �ʽ��ϴ�.");
		else if(!password.equals(message.getPassword()))
			throw new ServiceException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
	}
	
}
