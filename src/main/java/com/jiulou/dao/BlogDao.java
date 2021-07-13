package com.jiulou.dao;

import com.jiulou.model.Blog;
import org.springframework.stereotype.Component;

import java.util.List;

public interface BlogDao {

    public List<Blog> findAllBlog();

}
