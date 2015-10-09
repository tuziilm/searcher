package com.tuziilm.searcher.persistence;


import com.tuziilm.searcher.domain.User;

public interface UserMapper extends BaseMapper<User> {
    User selectByIdentity(String identity);
}
