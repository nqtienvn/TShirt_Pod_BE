package com.shirt.pod.controller;

import com.shirt.pod.dto.UploadResponse;
import com.shirt.pod.exception.InvalidFileException;
import com.shirt.pod.service.S3StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UploadController {
    
    private final S3StorageService s3StorageService;
    
    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new InvalidFileException("No file provided or file is empty");
        }
        String fileUrl = s3StorageService.uploadFile(file);
        UploadResponse response = new UploadResponse(fileUrl);
        return ResponseEntity.ok(response);
    }
}
