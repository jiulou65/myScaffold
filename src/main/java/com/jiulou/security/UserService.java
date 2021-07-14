package com.jiulou.security;

import com.jiulou.dao.UserDao;
import com.jiulou.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService<T extends User> implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        //用户权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.isNotBlank(user.getRoles())) {
            String[] roles = user.getRoles().split(",");
            for (String role : roles) {
                if (StringUtils.isNotBlank(role)) {
                    authorities.add(new SimpleGrantedAuthority(role.trim()));
                }
            }
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }
}
