package com.aaa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aaa.bean.Message;

/**
 * 和message表相关的数据库操作
 */
public class MessageDao {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command, String description) {
		List<Message> messageList = new ArrayList<Message>();

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {

			new com.mysql.jdbc.Driver();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs?user=root&password=123456");
			// String sql = "select ID,COMMAND,DESCRIPTION,CONTENT from
			// message";
			// 用StringBuilder取代String从数据库中取值，StringBuiler可以长度变化，String不可变，影响性能
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1 ");
			// 存储查询的参数，用于计算查询的词语的个数
			List<String> paramList = new ArrayList<String>();
			// 精确查询command字段
			if (command != null && !"".equals(command.trim())) {
				sql.append(" and COMMAND=? ");
				paramList.add(command);
			}
			// 模糊查询description字段
			if (description != null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramList.add(description);
			}
			statement = connection.prepareStatement(sql.toString());
			// 将sql查询语句的“？“问号的内容填上相应的查询词语
			for (int i = 0; i < paramList.size(); i++) {
				statement.setString(i + 1, paramList.get(i));
			}
			rs = statement.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				messageList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return messageList;
	}
}
