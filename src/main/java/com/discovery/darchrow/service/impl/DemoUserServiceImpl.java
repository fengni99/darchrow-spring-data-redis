/**
 * Copyright (c) 2016 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun. You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 */
package com.discovery.darchrow.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.discovery.darchrow.mapper.UserMapper;
import com.discovery.darchrow.model.User;
import com.discovery.darchrow.service.api.DemoUserService;
import com.discovery.darchrow.tools.jsonlib.JsonUtil;

@Service
public class DemoUserServiceImpl implements DemoUserService {
    
    private static final Logger log = LoggerFactory.getLogger(DemoUserServiceImpl.class);
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 存缓存(name)
     * 
     * 存之前查询，如果有，则不进入方法
     */
    @Override
    @Cacheable(value = "common", key = "'id_'+#id")
    public User findById(int id) {
        // TODO Auto-generated method stub
        User user = userMapper.selectUserByID(id);
        log.debug("DemoUserServiceImpl-user :{}", JsonUtil.format(user));
        return user;
    }
    
    /**
     * 存缓存(name)
     * 
     * 
     * 
     */
    @Override
    @Cacheable(value = "common", key = "#name")
    public User findByName(String name) {
        // TODO Auto-generated method stub
        User user = userMapper.selectUserByName(name);
        log.debug("DemoUserServiceImpl-findByName:{}", JsonUtil.format(user));
        return user;
    }
    
    /**
     * 存缓存
     * 
     * 存之前不检查缓存是否有，每次都进入方法，将结果存入缓存，相当于刷新缓存
     * 
     */
    @Override
    @CachePut(value = "common", key = "#user.getUserName()")
    public void insertUser(User user) {
        // TODO Auto-generated method stub
        int result = userMapper.insertUser(user);
        log.debug("DemoUserServiceImpl-insertUser:{}", result);
    }
    
    
    /**
     * 清除缓存
     * 根据key： id_xx清除
     * 
     * @param id
     */
    @CacheEvict(value = "common", key = "'id_'+#id")
    public void deleteByPrimaryKey(Integer id) {
        int result = userMapper.deleteByPrimaryKey(id);
        log.debug("DemoUserServiceImpl-deleteByPrimaryKey:{}", result);
    }
    
    /**
     * 清除缓存
     * p0代表第一个参数
     * 
     * @param name
     */
    @CacheEvict(value = "common", key = "#p0")
    public void deleteByName(String name) {
        int result = userMapper.deleteByName(name);
        log.debug("DemoUserServiceImpl-deleteByName:{}", result);
    }
    
    /**
     * 条件缓存
     * 
     * 示例: 只有当id为偶数时才会进行缓存
     */
    @Override
    @Cacheable(value = "common", key = "'id_'+#id", condition = "#id%2==0")
    public User findByConditionalId(int id) {
        // TODO Auto-generated method stub
        User user = userMapper.selectUserByID(id);
        log.debug("DemoUserServiceImpl-user:{}", JsonUtil.format(user));
        return user;
    }
    
}
