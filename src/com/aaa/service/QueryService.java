package com.aaa.service;

import java.util.List;
import java.util.Random;

import com.aaa.bean.Command;
import com.aaa.bean.CommandContent;
import com.aaa.bean.Message;
import com.aaa.dao.CommandDao;
import com.aaa.dao.MessageDao;
import com.aaa.util.Iconst;

/**
 * 列表相关的业务功能
 */
public class QueryService {

	public List<Message> queryMessageList(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
	public String queryByCommand(String command) {
		CommandDao aDao = new CommandDao();
		List<Command> commandList;
		//输入“查看”后返回的结果
		if(Iconst.HELP_COMMAND.equals(command)) {
			commandList = aDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i=0; i< commandList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		commandList = aDao.queryCommandList(command, null);
		if(commandList.size() > 0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			int i = new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		//查询不到匹配字段时的回复字符串
		return Iconst.NO_MATCHING_CONTENT;
	}
}
