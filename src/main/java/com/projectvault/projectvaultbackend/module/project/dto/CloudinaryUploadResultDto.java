package com.projectvault.projectvaultbackend.module.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudinaryUploadResultDto {
    private String url;
    private String publicId;
}
