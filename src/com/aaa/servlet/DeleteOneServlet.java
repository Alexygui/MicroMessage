package com.aaa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaa.service.MaintainService;

/**
 * 单条删除控制层
 */
public class DeleteOneServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置页面编码防止乱码
		request.setCharacterEncoding("utf-8");
		//接受页面传递的值
		String id = request.getParameter("id");
		MaintainService aMaintainService = new MaintainService();
		aMaintainService.deleteOne(id);
		request.getRequestDispatcher("/List.action").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
