package com.mpicture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mpicture.dao.AdminMapper;
import com.mpicture.entity.Admin;
import com.mpicture.entity.PageClass;
import com.mpicture.service.AdminService;
import com.mpicture.util.SafeUtil;
@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	/** 自动装配admin的dao层*/
	@Autowired
	private AdminMapper adminMapper;
	/**
	 * admin登录操作
	 */
	@Override
	public Admin login(Admin admin) {
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		return adminMapper.selectOne(admin);
	}
	/**
	 * 用户列表
	 */
	@Override
	public List<Admin> adminList(PageClass pageClass) {
		PageHelper.startPage(pageClass.getNowpage(), pageClass.getPagecount());
		pageClass.setCount((int)new PageInfo<Admin>().getTotal());
		return adminMapper.selectAll();
	}
	/**
	 * 新增admin
	 */
	@Override
	public int adminAdd(Admin admin) {
		admin.setPassword(SafeUtil.encode(admin.getPassword()));
		if(admin.getaid()!=null){
			return adminMapper.updateByPrimaryKey(admin);
		}
		return adminMapper.insert(admin);
	}
	/**
	 * 获取单个admin
	 */
	@Override
	public Admin getAdmin(Integer aid) {
		return adminMapper.selectByPrimaryKey(aid);
	}
	/**
	 * 删除admin
	 */
	@Override
	public int deleteAdmin(Integer aid) {
		return adminMapper.deleteByPrimaryKey(aid);
	}
	
	
	
}
