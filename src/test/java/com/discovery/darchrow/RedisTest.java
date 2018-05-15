/**
 * Creation Date:2018年4月17日-下午8:44:57
 * 
 * Copyright 2008-2018 © 同程网 Inc. All Rights Reserved
 */
package com.discovery.darchrow;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Description Of The Class<br/>
 * 
 * @author 	mdl47196
 * @version 1.0.0, 2018年4月17日-下午8:44:57
 * @since 2018年4月17日-下午8:44:57
 */
public class RedisTest extends BaseTest{
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Test
    public void test(){
        
        redisTemplate.execute(new RedisCallback<Integer>() {
            //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("key:" + i).getBytes();
                    byte[] value = ("value:" + i).getBytes();
                    connection.set(key, value);
                }
                //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                return i;
                
            }
        });

    }
    
}

