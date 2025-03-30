package com.example.Mymenu.project.repository;

import com.example.Mymenu.project.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitleContaining(String title);

    List<Blog> findByDescriptionContaining(String description);

}
