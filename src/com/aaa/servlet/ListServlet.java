package com.aaa.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaa.bean.Message;

/**
 * 列表的初始化控制
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			request.setCharacterEncoding("utf-8");
			String command = request.getParameter("command");
			String description = request.getParameter("description");
			request.setAttribute("command", command);
			request.setAttribute("description", description);
			new com.mysql.jdbc.Driver();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs?user=root&password=123456");
			// String sql = "select ID,COMMAND,DESCRIPTION,CONTENT from
			// message";
			// 用StringBuilder取代String从数据库中取值，StringBuiler可以长度变化，String不可变，影响性能
			StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1=1 ");
			//存储查询的参数，用于计算查询的词语的个数
			List<String> paramList = new ArrayList<String>();
			//精确查询command字段
			if (command != null && !"".equals(command.trim())) {
				sql.append(" and COMMAND=? ");
				paramList.add(command);
			}
			//模糊查询description字段
			if (description != null && !"".equals(description.trim())) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramList.add(description);
			}
			statement = connection.prepareStatement(sql.toString());
			//将sql查询语句的“？“问号的内容填上相应的查询词语
			for(int i=0; i<paramList.size(); i++) {
				statement.setString(i+1, paramList.get(i));
			}
			rs = statement.executeQuery();
			List<Message> messageList = new ArrayList<Message>();
			while (rs.next()) {
				Message message = new Message();
				messageList.add(message);
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
			}
			request.setAttribute("messageList", messageList);
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
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
