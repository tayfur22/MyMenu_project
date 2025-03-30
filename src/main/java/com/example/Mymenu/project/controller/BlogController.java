package com.example.Mymenu.project.controller;

import com.example.Mymenu.project.entity.Blog;
import com.example.Mymenu.project.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Blog> createBlog(
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setDescription(description);

        return ResponseEntity.ok(blogService.saveWithImages(blog, images));
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@Valid @RequestBody Blog blog, @PathVariable Long id) {
        return ResponseEntity.ok(blogService.update(blog, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
