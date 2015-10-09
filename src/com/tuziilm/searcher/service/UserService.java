package com.tuziilm.searcher.service;

import com.tuziilm.searcher.domain.User;
import com.tuziilm.searcher.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    private UserMapper userMapper;
	@Autowired
	public void setUserMapper(UserMapper userMapper){
		this.mapper=userMapper;
        this.userMapper = userMapper;
	}

    public User getByIdentity(String identity){
        return userMapper.selectByIdentity(identity);
    }
}
