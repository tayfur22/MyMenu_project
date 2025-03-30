package com.example.Mymenu.project.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileStorageUtil {

    private static final String UPLOAD_DIR = "src/main/resources/static/uploads";

    public static List<String> saveImages(List<MultipartFile> images) throws IOException {
        List<String> imageUrls = new ArrayList<>();

        if (images == null || images.isEmpty()) {
            return imageUrls;
        }

        for (MultipartFile image : images) {
            if (image != null && !image.isEmpty()) {
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);

                Files.createDirectories(filePath.getParent());
                Files.write(filePath, image.getBytes());

                imageUrls.add("/uploads/" + fileName);
            }
        }
        return imageUrls;
    }
}
