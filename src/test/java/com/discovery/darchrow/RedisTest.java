/**
 * Creation Date:2018年4月17日-下午8:44:57
 * 
 * Copyright 2008-2018 © 同程网 Inc. All Rights Reserved
 */
package com.discovery.darchrow;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.discovery.darchrow.model.User;
import com.discovery.darchrow.service.impl.DemoUserServiceImpl;
import com.discovery.darchrow.tools.jsonlib.JsonUtil;

/**
 * Description Of The Class<br/>
 * 
 * @author mdl47196
 * @version 1.0.0, 2018年4月17日-下午8:44:57
 * @since 2018年4月17日-下午8:44:57
 */
public class RedisTest extends BaseTest {
    
    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    private DemoUserServiceImpl demoUserServiceImpl;
    
    @Test
    public void test() {
        
        redisTemplate.execute(new RedisCallback<Integer>() {
            
            // 这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("key:" + i).getBytes();
                    byte[] value = ("value:" + i).getBytes();
                    connection.set(key, value);
                }
                // 这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                return i;
                
            }
        });
        
    }
    
    @Test
    public void test1() {
        User user = demoUserServiceImpl.findById(3);
        log.debug("RedisTest--------------findById:{}", JsonUtil.format(user));
        User user2 = demoUserServiceImpl.findById(3);
        log.debug("RedisTest--------------findById:{}", JsonUtil.format(user2));
        demoUserServiceImpl.deleteByPrimaryKey(3);
        log.debug("RedisTest--------------del id 3");
        User user3 = demoUserServiceImpl.findById(3);
        log.debug("RedisTest--------------findById:{}", JsonUtil.format(user3));
    }
    
    @Test
    public void test2() {
        User user = new User();
        user.setUserName("jack");
        user.setPassword("1234");
        demoUserServiceImpl.insertUser(user);
    }
    
    @Test
    public void test3() {
        User user = demoUserServiceImpl.findByName("jack");
        log.debug("RedisTest-findByName:{}", JsonUtil.format(user));
    }
    
    @Test
    public void test4() {
        demoUserServiceImpl.deleteByName("jack");
        test3();
    }
    
    @Test
    public void test5() {
        int len = 4;
        for (int i = 1; i < len; i++) {
            User user = demoUserServiceImpl.findByConditionalId(i);
            log.debug("RedisTest--------------" + i + "-----------------findByConditionalId:{}", JsonUtil.format(user));
        }
        log.debug("RedisTest--------------------分隔分隔-----------------------------");
        for (int i = 1; i < len; i++) {
            User user = demoUserServiceImpl.findByConditionalId(i);
            log.debug("RedisTest--------------" + i + "-----------------findByConditionalId:{}", JsonUtil.format(user));
        }
    }
    
}
