package com.projectvault.projectvaultbackend.module.project.service;

import com.projectvault.projectvaultbackend.module.project.dto.CloudinaryUploadResultDto;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    CloudinaryUploadResultDto uploadImage(MultipartFile file);
    void deleteImage(String publicId);
}
