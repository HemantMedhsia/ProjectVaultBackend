package com.projectvault.projectvaultbackend.module.project.controller;

import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import com.projectvault.projectvaultbackend.module.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectEntity create(@RequestBody ProjectEntity project) {
        return service.create(project);
    }

    @GetMapping
    public List<ProjectEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{slug}")
    public ProjectEntity getBySlug(@PathVariable String slug) {
        return service.getBySlug(slug);
    }
}
