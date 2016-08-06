package com.aaa.service;

import java.util.List;

import com.aaa.bean.Message;
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
		MessageDao aDao = new MessageDao();
		List<Message> messageList;
		//输入“查看”后返回的结果
		if(Iconst.HELP_COMMAND.equals(command)) {
			messageList = aDao.queryMessageList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i=0; i< messageList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + messageList.get(i).getCommand() + "]可以查看" + messageList.get(i).getDescription());
			}
			return result.toString();
		}
		messageList = aDao.queryMessageList(command, null);
		if(messageList.size() > 0) {
			return messageList.get(0).getContent();
		}
		//查询不到匹配字段时的回复字符串
		return Iconst.NO_MATCHING_CONTENT;
	}
}
