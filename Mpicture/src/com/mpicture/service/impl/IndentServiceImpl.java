package com.mpicture.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpicture.dao.IndentMapper;
import com.mpicture.entity.Indent;
import com.mpicture.entity.PageClass;
import com.mpicture.service.IndentService;
import com.mpicture.util.IndentStatusEnum;

/**
 * 用户serverimpl实现类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class IndentServiceImpl implements IndentService{

	@Autowired
	private IndentMapper indentMapper;
	/**
	 * 根据用户id获取订单列表
	 */
	@Override
	public List<Indent> listMyIndent(Integer uid) {
		return indentMapper.listMyIndent(uid);
	}
	/**
	 * 根据状态查询我的订单
	 */
	@Override
	public List<Indent> listMyPicture(Integer uid, Integer status) {
		return indentMapper.listMyPicture(uid, status);
	}
	/**
	 * 查询单个订单
	 */
	@Override
	public Indent getdetails(Integer tid) {
		return indentMapper.selectOneByPrimaryKey(tid);
	}
	/**
	 * 新增订单
	 */
	@Override
	public Integer addIndent(Indent indent) {
		indentMapper.insert(indent);
		return indentMapper.selectPrimary();
	}
	@Override
	public Integer queryPrimary() {
		return indentMapper.selectPrimary();
	}
	@Override
	public Integer updateIndent(Indent indent) {
		return indentMapper.updateIndent(indent);
	}
	@Override
	public Integer updateIndentStatus(Indent indent) {
		return indentMapper.updateIndentStatus(indent);
	}
	@Override
	public List<Indent> newIndentList(Integer uid, Integer limit1, Integer limit2) {
		return indentMapper.selectnewIndentList(uid,limit1,limit2);
	}
	/**
	 * 后台查询订单列表
	 * @param uid
	 * @param status
	 * @return
	 */
	@Override
	public List<Indent> listIndentByStatus(PageClass pageClass, Integer status) {
		pageClass.setCount(indentMapper.listIndentByStatusCount(status));
		return indentMapper.listIndentByStatus(pageClass, status);
	}
	/**
	 * 删除订单
	 * @param tid
	 * @return
	 */
	@Override
	public Integer deleteIndent(Integer tid) {
		return indentMapper.deleteIndent(tid);
	}
	/**
	 * 后台修改订单状态
	 */
	@Override
	public Integer updateIndentStatus(Integer tid, Integer status) {
		IndentStatusEnum indentenum=IndentStatusEnum.getIndentStatusEnum(status);
		return indentMapper.updateIndentStatusByAdmin(tid,indentenum.getStatus(),indentenum.getDescribe());
	}
	
}
