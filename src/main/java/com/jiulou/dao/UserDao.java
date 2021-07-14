package com.jiulou.dao;

import com.jiulou.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    // 根据用户名查找用户
    public User findUserByUsername(String username);

}
