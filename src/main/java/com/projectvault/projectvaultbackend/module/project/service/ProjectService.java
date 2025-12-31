package com.projectvault.projectvaultbackend.module.project.service;

import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;

import java.util.List;

public interface ProjectService {

    ProjectEntity create(ProjectEntity project);
    ProjectEntity getBySlug(String slug);
    List<ProjectEntity> getAll();
}
