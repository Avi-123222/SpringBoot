package com.jt.jt_blogs.service;

//import com.jt.jt_blogs.controller.BlogController;
import com.jt.jt_blogs.model.Blog;
import com.jt.jt_blogs.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    // private final JdbcTemplate jdbcTemplate;
    private final BlogRepository repository;
    // private static final String BLOGS_TABLE = "blogs";

    public List<Blog> getBlogs() {
        // String query = "SELECT * FROM %s".formatted(BLOGS_TABLE);
        // List<Blog> existingBlogs = jdbcTemplate.query(query, (resultSet, rowNum) -> {
        // int id = resultSet.getInt("id");
        // String heading = resultSet.getString("heading");

        // String description = resultSet.getString("description");

        // return new Blog(id, heading, description);
        // });

        // return jdbcTempblogControllerlate.query(query, new
        // BeanPropertyRowMapper<>(Blog.class));
        List<Blog> existingBlogs = repository.findAll();
        return existingBlogs;
    }

    public void createBlog(String heading, String description) {
        // String query = "INSERT INTO %s (heading, description) VALUES(?,
        // ?)".formatted(BLOGS_TABLE);
        // jdbcTemplate.update(query, heading, description);
        // Blog newBlog = new Blog();
        // newBlog.setHeading(heading);
        // newBlog.setDescription(description);
        Blog newBlog = Blog.builder().heading(heading).description(description).build();

        repository.save(newBlog);
    }

    public void deleteBlogById(int id) {
        // String query = "DELETE FROM %s WHERE id=?".formatted(BLOGS_TABLE);
        // jdbcTemplate.update(query, id);
        Blog toDeleteBlog = getBlogById(id);
        repository.delete(toDeleteBlog);
    }

    public Blog getBlogById(int id) {
        // String query = "SELECT * FROM %s WHERE id=?".formatted(BLOGS_TABLE);
        // return jdbcTemplate.queryForObject(query,
        // new BeanPropertyRowMapper<>(Blog.class), id);
        Blog blog = repository.findById(id).orElseThrow();
        return blog;
    }

    public void updateBlog(Blog blog) {
        // int id = blog.getId();
        // String heading = blog.getHeading();
        // String description = blog.getDescription();

        // String query = "UPDATE %s SET heading = ?, description = ? WHERE id =
        // ?".formatted(BLOGS_TABLE);
        // jdbcTemplate.update(query, heading, description, id);
        repository.save(blog);
    }
}