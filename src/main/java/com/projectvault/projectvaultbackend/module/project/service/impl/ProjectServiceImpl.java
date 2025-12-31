package com.projectvault.projectvaultbackend.module.project.service.impl;

import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import com.projectvault.projectvaultbackend.module.project.repository.ProjectRepository;
import com.projectvault.projectvaultbackend.module.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectEntity create(ProjectEntity project) {

        if (project.getSections() != null) {
            project.getSections().forEach(section -> {

                // 🔗 link section -> project
                section.setProject(project);

                if (section.getFields() != null) {
                    section.getFields().forEach(field -> {
                        // 🔗 link field -> section
                        field.setSection(section);
                    });
                }
            });
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
}