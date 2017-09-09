package com.mpicture.dao;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.mpicture.entity.Users;

import tk.mybatis.mapper.common.Mapper;

/**
 * Dao - users
 * @author Administrator
 *
 */

public interface UserMapper extends Mapper<Users>{

	/**
	 * 查询用户头像和邮箱地址
	 * @param uid
	 * @return 用户对象
	 */
	@Select("select headportrait,email from users where users.uid=#{uid}")
	@ResultMap("UserHead")
	public Users selectUserHead(Integer uid);

	/**
	 * 修改当前用户的邮箱
	 * @param uid   0：下标，两个参数第一个下标为0，，第二个下标参数为1
	 * @param email
	 */
	@Select("UPDATE users SET users.email=#{1} where users.uid=#{0}")
	@ResultMap("UserHead")
	public Users modifiyEmail(Integer uid, String email);

}
