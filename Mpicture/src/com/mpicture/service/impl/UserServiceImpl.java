package com.mpicture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mpicture.dao.UserMapper;
import com.mpicture.entity.Users;
import com.mpicture.service.UserService;

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

}
