package com.aaa.dao;

import java.util.List;

import com.aaa.bean.Message;

/**
 * 与Message配置文件相对应的接口 
 */
public interface IMessage {
	public List<Message> queryMessageList(Message aMessage);
}
