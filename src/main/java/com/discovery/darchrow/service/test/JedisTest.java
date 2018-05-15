/**
 * Creation Date:2018年4月25日-下午7:41:57
 * 
 * Copyright 2008-2018 © 同程网 Inc. All Rights Reserved
 */
package com.discovery.darchrow.service.test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Description Of The Class<br/>
 * 
 * @author 	mdl47196
 * @version 1.0.0, 2018年4月25日-下午7:41:57
 * @since 2018年4月25日-下午7:41:57
 */
public class JedisTest {
    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 6379));
        nodes.add(new HostAndPort("127.0.0.1", 6380));
        nodes.add(new HostAndPort("127.0.0.1", 6381));
        nodes.add(new HostAndPort("127.0.0.1", 6382));
        nodes.add(new HostAndPort("127.0.0.1", 6383));
        nodes.add(new HostAndPort("127.0.0.1", 6384));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        String name = cluster.get("name");
        System.out.println(name);
        cluster.set("age", "18");
        System.out.println(cluster.get("age"));
        try {
            cluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

