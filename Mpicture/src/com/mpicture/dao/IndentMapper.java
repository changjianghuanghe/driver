package com.mpicture.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mpicture.entity.Indent;
import com.mpicture.entity.PageClass;

public interface IndentMapper{
	@Select("select tid,systime,userphoto,`status`,pid,price,cover,name,picture,statusintr,updatetotal "
			+ "from indent i,product p where i.product_id=p.pid and i.users_id=#{uid}")
	@ResultMap("IndentList")
	public List<Indent> listMyIndent(Integer uid);
	
	@Select("select tid,systime,userphoto,`status`,pid,price,cover,name,picture,statusintr,updatetotal "
			+ "from indent i,product p where i.product_id=p.pid and i.users_id=#{0} and status=#{1}")
	@ResultMap("IndentList")
	public List<Indent> listMyPicture(Integer uid,Integer status);
	
	@Select("select tid,systime,userphoto,`status`,pid,price,cover,name,picture,statusintr,updatetotal "
			+ "from indent i,product p where i.product_id=p.pid and i.tid=#{tid}")
	@ResultMap("IndentList")
	public Indent selectOneByPrimaryKey(Integer tid);
	
	@Insert("insert into indent(systime,users_id,product_id,status,picture,statusintr,updatetotal) "
			+ "VALUES(#{systime},#{users_id.uid},#{product_id.pid},#{status},#{picture},#{statusintr},#{updatetotal});")
	public Integer insert(Indent indent);
	@Select("SELECT @@IDENTITY;")
	public Integer selectPrimary();
	
	@Update("update indent set userphoto=#{userphoto},require_text=#{require},e_mail=#{e_mail},status=#{status},statusintr=#{statusintr} where tid=#{tid}")
	public Integer updateIndent(Indent indent);
	@Update("update indent set status=#{status},statusintr=#{statusintr},updatetotal=#{updatetotal} where tid=#{tid}")
	public Integer updateIndentStatus(Indent indent);
	
	@Select("select tid,picture from indent where users_id=#{0}  ORDER BY tid desc LIMIT #{1},#{2}")
	@ResultMap("newIndents")
	public List<Indent> selectnewIndentList(Integer uid, Integer limit1, Integer limit2);
	
	public List<Indent> listIndentByStatus(@Param("pageClass")PageClass pageClass,@Param("status") Integer status);
	
	public Integer listIndentByStatusCount(@Param("status") Integer status);
	
	@Select("delete from indent where tid=#{tid}")
	@ResultType(value=java.lang.Integer.class)
	public Integer deleteIndent(@Param("tid") Integer tid);
	
	@Update("update indent set status=#{status},statusintr=#{statusintr} where tid=#{tid}")
	@ResultType(value=java.lang.Integer.class)
	public Integer updateIndentStatusByAdmin(@Param("tid") Integer tid,
			@Param("status") Integer status,@Param("statusintr") String statusintr);
}
