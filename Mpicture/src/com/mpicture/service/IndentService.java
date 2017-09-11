package com.mpicture.service;

import java.util.List;

import com.mpicture.entity.Indent;
import com.mpicture.entity.PageClass;

public interface IndentService {
	/**
	 * 根据uid获取订单列表
	 * @param uid
	 * @return
	 */
	List<Indent> listMyIndent(Integer uid);
	/**
	 * 根据uid和状态获取订单列表
	 * @param uid
	 * @param status
	 * @return
	 */
	List<Indent> listMyPicture(Integer uid,Integer status);
	/**
	 * 查询单个订单详情
	 * @param tid
	 * @return
	 */
	Indent getdetails(Integer tid);
	/**
	 * 新增订单
	 * @param indent
	 * @return
	 */
	Integer addIndent(Indent indent);
	/**
	 * 返回自增的主键id
	 * @return
	 */
	Integer queryPrimary();
	/**
	 * 修改订单
	 * @param indent
	 * @return
	 */
	Integer updateIndent(Indent indent);
	/**
	 * 修改超时订单
	 * @param indent
	 * @return
	 */
	Integer updateIndentStatus(Indent indent);
	/**
	 * 按数量查询最新记录
	 * @param uid
	 * @param limit1
	 * @param limit2
	 * @return
	 */
	List<Indent> newIndentList(Integer uid,Integer limit1,Integer limit2);
	/**
	 * 后台查询订单列表
	 * @param uid
	 * @param status
	 * @return
	 */
	List<Indent> listIndentByStatus(PageClass pageClass,Integer status);
	
	Integer deleteIndent(Integer tid);
	/**
	 * 修改订单状态
	 * @param tid
	 * @param status
	 * @return
	 */
	Integer updateIndentStatus(Integer tid,Integer status);
	
}
