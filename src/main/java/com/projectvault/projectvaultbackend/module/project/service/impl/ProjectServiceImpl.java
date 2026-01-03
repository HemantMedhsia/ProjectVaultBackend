package com.projectvault.projectvaultbackend.module.project.service.impl;

import com.projectvault.projectvaultbackend.module.project.dto.CloudinaryUploadResultDto;
import com.projectvault.projectvaultbackend.module.project.dto.ProjectCreateRequestDto;
import com.projectvault.projectvaultbackend.module.project.dto.ProjectSectionDto;
import com.projectvault.projectvaultbackend.module.project.dto.ProjectSectionFieldDto;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectFileEntity;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectSectionEntity;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectSectionFieldEntity;
import com.projectvault.projectvaultbackend.module.project.repository.ProjectRepository;
import com.projectvault.projectvaultbackend.module.project.service.CloudinaryService;
import com.projectvault.projectvaultbackend.module.project.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final CloudinaryService cloudinaryService;

    public ProjectServiceImpl(ProjectRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public ProjectEntity create(ProjectCreateRequestDto projectDto, List<MultipartFile> images, List<MultipartFile> documents) {
        ProjectEntity project = new ProjectEntity();
        project.setSlug(projectDto.slug());
        project.setName(projectDto.name());
        project.setStatus(projectDto.status());

        if(projectDto.sections() != null) {
            for(ProjectSectionDto sectionDto : projectDto.sections()) {
                ProjectSectionEntity section = new ProjectSectionEntity();
                section.setProject(project);
                section.setType(sectionDto.type());
                section.setTitle(sectionDto.title());
                section.setOrderIndex(sectionDto.orderIndex());
                if(sectionDto.fields() != null) {
                    for(ProjectSectionFieldDto sectionFieldDto : sectionDto.fields()) {
                        ProjectSectionFieldEntity field = new ProjectSectionFieldEntity();
                        field.setSection(section);
                        field.setFieldKey(sectionFieldDto.fieldKey());
                        field.setFieldValue(sectionFieldDto.fieldValue());
                        section.getFields().add(field);
                    }
                }
                project.getSections().add(section);
            }
        }

        if(images != null) {
            saveFiles(project, images, "IMAGE");
        }

        if(documents != null) {
            saveFiles(project, documents, "DOCUMENT");
        }

        return repository.save(project);
    }

    @Override
    public ProjectEntity getBySlug(String slug) {
        return repository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Override
    public List<ProjectEntity> getAll() {
        return repository.findAll();
    }

    private void saveFiles(ProjectEntity project, List<MultipartFile> files, String category) {
        for (MultipartFile file : files) {

            CloudinaryUploadResultDto uploadResult =cloudinaryService.uploadImage(file);
            ProjectFileEntity entity = new ProjectFileEntity();

            entity.setOriginalName(file.getOriginalFilename());
            entity.setContentType(file.getContentType());
            entity.setSize(file.getSize());
            entity.setCategory(category);

            entity.setStoragePathUrl(uploadResult.getUrl());
            entity.setPublicID(uploadResult.getPublicId());

            entity.setProject(project);
            project.getFiles().add(entity);
        }
    }


}