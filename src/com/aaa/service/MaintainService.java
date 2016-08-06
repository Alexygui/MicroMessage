package com.aaa.service;

import com.aaa.dao.MessageDao;

/**
 * 维护相关的业务功能
 */
public class MaintainService {
	
	/**
	 * 删除单条记录的功能
	 */
	public void deleteOne(String id) {
		if (id != null && !"".equals(id)) {
			MessageDao aDao = new MessageDao();
			aDao.deleteOne(Integer.parseInt(id));
		}
	}
}
