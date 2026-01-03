package com.projectvault.projectvaultbackend.module.project.service;

import com.projectvault.projectvaultbackend.module.project.dto.ProjectCreateRequestDto;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    ProjectEntity create(ProjectCreateRequestDto project, List<MultipartFile> images, List<MultipartFile> documents);
    ProjectEntity getBySlug(String slug);
    List<ProjectEntity> getAll();
}
