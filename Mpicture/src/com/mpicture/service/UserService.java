package com.mpicture.service;

import com.mpicture.entity.Users;

/**
 * service - users
 * @author Administrator
 *
 */

public interface UserService {
	
	Users getUserById(Integer uid);

	/**
	 * 获取用户头像和邮箱地址
	 * @param uids
	 * @return 用户对象
	 */
	Users userHeadOne(Integer uid);

	/**
	 * 修改当前用户的邮箱
	 * @param email
	 */
	Users modifyEmail(Integer uid,String email);
	/**
	 * 根据openid查询单个用户
	 * @param openid
	 * @return
	 */
	Users queryOne(String openid);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	Integer addUser(Users user);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	Integer updateUser(Users user);
}
