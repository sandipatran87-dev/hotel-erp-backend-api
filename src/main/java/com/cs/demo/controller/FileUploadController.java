package com.cs.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@CrossOrigin("*")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping(
    	    value = "/menu",
    	    consumes = "multipart/form-data"
    	)
    public ResponseEntity<String> uploadMenuImage(
    		 @RequestPart("file") MultipartFile file)
            throws IOException {

        String fileName =
                UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {

            Files.createDirectories(uploadPath);

        }

        Files.copy(
                file.getInputStream(),
                uploadPath.resolve(fileName),
                StandardCopyOption.REPLACE_EXISTING
        );

        return ResponseEntity.ok(fileName);

    }

}