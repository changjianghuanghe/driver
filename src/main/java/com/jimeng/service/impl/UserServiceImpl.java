package com.jimeng.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jimeng.dao.UserMapper;
import com.jimeng.entity.PageClass;
import com.jimeng.entity.User;
import com.jimeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by xingbake on 2017/9/14.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    //删除缓存
    @CacheEvict(value="mycache",key="#user.uid")
    public Integer addOrUpdate(User user) {
        if(user.getUid()!=null){
            return userMapper.updateByPrimaryKey(user);
        }
        return userMapper.insert(user);
    }
    @CacheEvict(value="mycache",key="#id")
    public Integer del(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}
    //添加缓存
    @Cacheable(value="mycache",key="#id")
    public User findOne(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
    
    public List<User> SearchByExample(Example ex) {
		return userMapper.selectByExample(ex);
	}
    
    public List<User> findAll(){
        return userMapper.selectAll();
    }
    
    public List<User> findList(PageClass pageClass) {
        Page<User> page=PageHelper.startPage(pageClass.getNowpage(),pageClass.getPagecount(),true);
        List<User> users=userMapper.selectAll();
        pageClass.setCount((int)page.getTotal());
        return users;
    }

	
}
