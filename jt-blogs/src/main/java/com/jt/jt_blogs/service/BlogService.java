package com.jt.jt_blogs.service;

import com.jt.jt_blogs.model.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    public List<Blog> getBlogs(){
        Blog blog1 = new Blog(1,"Heading1","Description 111");
        Blog blog2 = new Blog(2,"Heading2","Description 112");
        Blog blog3 = new Blog(3,"Heading3","Description 113");
        return List.of(blog1,blog2,blog3);

    }
}
