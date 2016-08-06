package com.aaa.service;

import java.util.ArrayList;
import java.util.List;

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

	/**
	 * 批量删除多条记录的功能
	 */
	public void deleteBatch(String[] ids) {
		MessageDao aDao = new MessageDao();
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids) {
			idList.add(Integer.parseInt(id));
		}
		aDao.deleteBatch(idList);
	}
}
