package com.example.Mymenu.project.service.impl;

import com.example.Mymenu.project.entity.Blog;
import com.example.Mymenu.project.exception.EntityNotFoundException;
import com.example.Mymenu.project.exception.ResourceNotFoundException;
import com.example.Mymenu.project.repository.BlogRepository;
import com.example.Mymenu.project.service.BlogService;
import com.example.Mymenu.project.util.FileStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog saveWithImages(Blog blog, List<MultipartFile> images) throws IOException {
        List<String> imageUrls = FileStorageUtil.saveImages(images);
        blog.setImages(imageUrls);
        System.out.println("Images URLs: " + imageUrls); // Burada loglama əlavə edin
        return blogRepository.save(blog);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with ID: " + id));
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        if (!blogRepository.existsById(id)) {
            throw new ResourceNotFoundException("Blog not found with ID: " + id);
        }
        blogRepository.deleteById(id);
    }

    @Override
    public Blog update(Blog blog, Long id) {
        if (!blogRepository.existsById(id)) {
            throw new EntityNotFoundException("Blog not found with id" + id);
        }
        blog.setId(id);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findByTitleContaining(String title) {
        return blogRepository.findByTitleContaining(title);
    }

    @Override
    public List<Blog> findByDescriptionContaining(String description) {
        return blogRepository.findByDescriptionContaining(description);
    }
}

