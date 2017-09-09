package com.mpicture.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mpicture.dao.UserMapper;
import com.mpicture.entity.Admin;
import com.mpicture.entity.PageClass;
import com.mpicture.entity.Product;
import com.mpicture.entity.Users;
import com.mpicture.service.UserService;

import tk.mybatis.mapper.entity.Example;

/**
 * 用户serverimple类
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
	/** 自动装配用户dao层*/
	@Autowired
	private UserMapper userMapper;
	/**
	 * 获取用户
	 */
	@Override
	public Users getUserById(Integer uid) {
		return userMapper.selectByPrimaryKey(uid);//传入uid查找用户对象
	}
	/**
	 * 获取用户头像和邮箱地址
	 * 
	 * @return 用户对象
	 */
	@Override
	public Users userHeadOne(Integer uid) {
		return userMapper.selectUserHead(uid);
	}
	/**
	 * 修改用户邮箱
	 */
	@Override
	public Users modifyEmail(Integer uid, String email) {
		return userMapper.modifiyEmail(uid,email);
	}
	@Override
	public Users queryOne(String openid) {
		Users user=new Users();
		user.setOpenid(openid);
		return userMapper.selectOne(user);
	}
	@Override
	public Integer addUser(Users user) {
		return userMapper.insert(user);
	}
	@Override
	public Integer updateUser(Users user) {
		return userMapper.updateByPrimaryKey(user);
	}
	/**
	 * 客户列表
	 */
	@Override
	public List<Users> userList(PageClass pageClass) {
		PageHelper.startPage(pageClass.getNowpage(), pageClass.getPagecount());
		pageClass.setCount((int)new PageInfo<Users>().getTotal());
		return userMapper.selectAll();
	}
	/**
	 * 删除客户
	 */
	@Override
	public void deruser(Users user) {
		userMapper.delete(user);
	}
	/**
	 * 根据客户姓名查询客户
	 */
	@Override
	public List<Users> userSearch(String username) {
		Example ex=new Example(Users.class);
		ex.createCriteria().andEqualTo("username", username);
		return userMapper.selectByExample(ex);
	}

}
