package com.example.Mymenu.project.service;

import com.example.Mymenu.project.entity.Blog;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BlogService extends BaseService<Blog,Long>{

    List<Blog> findByTitleContaining(String title);

    List<Blog> findByDescriptionContaining(String description);

    Blog saveWithImages(Blog blog, List<MultipartFile> images) throws IOException;
}
