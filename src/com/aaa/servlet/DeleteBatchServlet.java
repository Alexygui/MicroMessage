package com.aaa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaa.service.MaintainService;

/**
 * 批量删除控制层
 */
public class DeleteBatchServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置页面编码防止乱码
		request.setCharacterEncoding("utf-8");
		//接受页面传递的值
		String[] ids = request.getParameterValues("id");
		MaintainService aMaintainService = new MaintainService();
		aMaintainService.deleteBatch(ids);
		request.getRequestDispatcher("/List.action").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
