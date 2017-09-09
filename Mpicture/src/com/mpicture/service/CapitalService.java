package com.mpicture.service;

import java.util.List;

import com.mpicture.entity.Capital;

public interface CapitalService {
	/**
	 * 根据uid查询所有资金状况
	 * @param uid
	 * @return
	 */
	List<Capital> ListCapital(Integer uid);
	/**
	 * 新增资金状况
	 * @param capital
	 * @return
	 */
	Integer addCapital(Capital capital);
}
