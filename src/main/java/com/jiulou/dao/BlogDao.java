package com.jiulou.dao;

import com.jiulou.model.Blog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    public List<Blog> findAllBlog();

}
