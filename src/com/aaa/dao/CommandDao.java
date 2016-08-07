package com.aaa.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.aaa.bean.Command;
import com.aaa.db.DBAccess;

/**
 * 和Command表相关的数据库操作
 */
public class CommandDao {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(String name, String description) {
		DBAccess aDbAccess = new DBAccess();
		SqlSession aSqlSession = null;
		List<Command> CommandList = new ArrayList<Command>();
		try {
			aSqlSession = aDbAccess.getSqlSession();
			Command aCommand = new Command();
			aCommand.setName(name);;
			aCommand.setDescription(description);
			// 通过SqlSession执行SQL语句，通过Command.xml配置文件读取相应的sql语句操作数据库
			CommandList = aSqlSession.selectList("Command.queryCommandList", aCommand);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (aSqlSession != null) {
				aSqlSession.close();
				aSqlSession = null;
			}
		}
		return CommandList;
	}

}
