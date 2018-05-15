package com.discovery.darchrow.service.mapper;

import java.util.List;

import com.discovery.darchrow.service.model.User;

public interface UserMapper {
	
	public User selectUserByID(int id);
	
	public List<User> selectUsers(String userName);
}