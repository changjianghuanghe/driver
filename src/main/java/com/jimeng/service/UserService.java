package com.jimeng.service;

import com.jimeng.entity.PageClass;
import com.jimeng.entity.User;

import tk.mybatis.mapper.entity.Example;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xingbake on 2017/9/13.
 */
public interface UserService {
    /**
     * Add new User
     * @param user
     */
    Integer addOrUpdate(User user);
    
    /**
     * del User
     * @param id
     * @return
     */
    Integer del(Integer id);

    /**
     * Find entity by id
     * @param id
     * @return
     */
    User findOne(Integer id);
    
    /**
     * Find By Example
     * @param ex
     * @return
     */
    List<User> SearchByExample(Example ex);

    /**
     * Find all entities
     * @return
     */
    List<User> findAll();

    /**
     * Find all entities by paging
     * @param
     * @param
     * @return
     */
    List<User> findList(PageClass pageClass);

}
