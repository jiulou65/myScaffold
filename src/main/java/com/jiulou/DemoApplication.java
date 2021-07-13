package com.jiulou;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiulou.dao.BlogDao;
import com.jiulou.model.Blog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication
@MapperScan("com.jiulou.dao")
@Controller
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private BlogDao blogDao;

    @RequestMapping("/thread")
    @ResponseBody
    @Async("taskExecutor")
    public String my() throws JsonProcessingException {
        List<Blog> allBlog = blogDao.findAllBlog();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(allBlog);
        return s;
    }

}
