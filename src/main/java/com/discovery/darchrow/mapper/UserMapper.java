package com.discovery.darchrow.mapper;

import java.util.List;

import com.discovery.darchrow.model.User;

public interface UserMapper {
	
	public User selectUserByID(int id);
	
	public List<User> selectUsers(String userName);
}