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
import com.aaa.service.ListService;

/**
 * 列表的初始化控制
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置页面编码防止乱码
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		//向页面传值
		request.setAttribute("command", command);
		request.setAttribute("description", description);	
		ListService listService = new ListService();
		//查询消息列表并传给页面
		request.setAttribute("messageList", listService.queryMessageList(command, description));
		//向页面跳转
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
