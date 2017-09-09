package com.mpicture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpicture.dao.CapitalMapper;
import com.mpicture.entity.Capital;
import com.mpicture.service.CapitalService;
@Service
@Transactional
public class CapitalServiceImpl implements CapitalService{
	@Autowired
	private CapitalMapper capitalMapper;
	/**
	 * 根据用户id查询交易明细
	 */
	@Override
	public List<Capital> ListCapital(Integer uid) {
		return capitalMapper.selectAll(uid);
	}
	@Override
	public Integer addCapital(Capital capital) {
		return capitalMapper.addCapital(capital);
	}

}
