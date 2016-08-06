package com.aaa.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.aaa.bean.Message;
import com.aaa.db.DBAccess;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command, String description) {
		DBAccess aDbAccess = new DBAccess();
		SqlSession aSqlSession = null;
		List<Message> messageList = new ArrayList<Message>();
		try {
			aSqlSession = aDbAccess.getSqlSession();
			Message aMessage = new Message();
			aMessage.setCommand(command);
			aMessage.setDescription(description);
			// 通过SqlSession执行SQL语句，通过Message.xml配置文件读取相应的sql语句操作数据库
			messageList = aSqlSession.selectList("Message.queryMessageList", aMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (aSqlSession != null) {
				aSqlSession.close();
				aSqlSession = null;
			}
		}
		return messageList;
	}
	
	/**
	 * 删除单条记录
	 */
	public void deleteOne(int id) {
		DBAccess aDbAccess = new DBAccess();
		SqlSession aSqlSession = null;
		try {
			aSqlSession = aDbAccess.getSqlSession();
			//通过aSqlSession执行sql语句
			aSqlSession.delete("Message.deleteOne",id);
			aSqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(aSqlSession != null) {
				aSqlSession.close();
				aSqlSession = null;
			}
		}
	}

	/**
	 * 删除多条记录
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess aDbAccess = new DBAccess();
		SqlSession aSqlSession = null;
		try {
			aSqlSession = aDbAccess.getSqlSession();
			//通过aSqlSession执行sql语句
			aSqlSession.delete("Message.deleteBatch",ids);
			aSqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(aSqlSession != null) {
				aSqlSession.close();
				aSqlSession = null;
			}
		}
	}
	/**
	 * 根据查询条件查询消息列表
	 */
//	
//	  public List<Message> queryMessageList(String command, String description)
//	 { List<Message> messageList = new ArrayList<Message>();
//	  
//	  Connection connection = null; PreparedStatement statement = null;
//	  ResultSet rs = null; try {
//	  
//	  new com.mysql.jdbc.Driver(); connection = DriverManager.getConnection(
//	 "jdbc:mysql://localhost:3306/bbs?user=root&password=123456"); // String
//	  sql = "select ID,COMMAND,DESCRIPTION,CONTENT from // message"; //
//	  用StringBuilder取代String从数据库中取值，StringBuiler可以长度变化，String不可变，影响性能
//	  StringBuilder sql = new StringBuilder(
//	  "select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1 "); //
//	  存储查询的参数，用于计算查询的词语的个数 List<String> paramList = new ArrayList<String>(); //
//	  精确查询command字段 if (command != null && !"".equals(command.trim())) {
//	  sql.append(" and COMMAND=? "); paramList.add(command); } //
//	  模糊查询description字段 if (description != null &&
//	  !"".equals(description.trim())) { sql.append(
//	  " and DESCRIPTION like '%' ? '%'"); paramList.add(description); }
//	  statement = connection.prepareStatement(sql.toString()); //
//	  将sql查询语句的“？“问号的内容填上相应的查询词语 for (int i = 0; i < paramList.size(); i++) {
//	  statement.setString(i + 1, paramList.get(i)); } rs =
//	  statement.executeQuery(); while (rs.next()) { Message message = new
//	  Message(); messageList.add(message); message.setId(rs.getString("ID"));
//	  message.setCommand(rs.getString("COMMAND"));
//	  message.setDescription(rs.getString("DESCRIPTION"));
//	  message.setContent(rs.getString("CONTENT")); }
//	  
//	  } catch (SQLException e) { e.printStackTrace(); } finally { try { if (rs
//	  != null) { rs.close(); rs = null; } if (statement != null) {
//	  statement.close(); statement = null; } if (connection != null) {
//	  connection.close(); connection = null; } } catch (SQLException e) {
//	  e.printStackTrace(); } } return messageList; }

	/**
	 * 测试mybatis能否连接上数据库
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) {
		MessageDao aMessageDao = new MessageDao();
		aMessageDao.queryMessageList("", "");
		System.out.println("连接了");
		
	}
}
