package com.jiulou.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiulou.config.BusinessException;
import com.jiulou.dao.BlogDao;
import com.jiulou.dao.UserDao;
import com.jiulou.model.Blog;
import com.jiulou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class IndexController {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/thread")
    public String my() throws JsonProcessingException {
        List<Blog> allBlog = blogDao.findAllBlog();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(allBlog);
        if (true){
            throw new BusinessException("运行异常啊");
        }
        return s;
    }

    @RequestMapping("/sayHi")
    public String sayHi(){
        User admin = userDao.findUserByUsername("admin");
        return admin.toString();
    }
}
