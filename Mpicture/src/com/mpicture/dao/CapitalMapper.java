package com.mpicture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.mpicture.entity.Capital;

public interface CapitalMapper {
	
	@Select("select time,quota,sketch,cid from capital,users where capital.users_id = users.uid and users.uid=#{uid}")
	@ResultMap("CapitalList")
	public List<Capital> selectAll(Integer uid);
	
	@Insert("insert into capital(time,users_id,quota,sketch) VALUES (#{time},#{users_id.uid},#{quota},#{sketch})")	
	public Integer addCapital(Capital capital);
}
