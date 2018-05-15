/**
 * Copyright (c) 2016 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.discovery.darchrow.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.discovery.darchrow.mapper.UserMapper;
import com.discovery.darchrow.model.User;
import com.discovery.darchrow.service.api.DemoUserService;
import com.discovery.darchrow.tools.jsonlib.JsonUtil;

@Service
public class DemoUserServiceImpl implements DemoUserService{
    
    private static final Logger log = LoggerFactory.getLogger(DemoUserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	@Cacheable(value="common",key="'id_'+#id")
	public User findById(int id) {
		// TODO Auto-generated method stub
	    User user = userMapper.selectUserByID(id);
	    log.debug("DemoUserServiceImpl-user:{}", JsonUtil.format(user));
		return user;
	}

}
