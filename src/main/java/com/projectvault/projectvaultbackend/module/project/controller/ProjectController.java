package com.projectvault.projectvaultbackend.module.project.controller;

import com.projectvault.projectvaultbackend.module.project.dto.ProjectCreateRequestDto;
import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import com.projectvault.projectvaultbackend.module.project.service.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjectEntity create(
            @RequestPart("project") ProjectCreateRequestDto projectJson,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "documents", required = false) List<MultipartFile> documents
    ) {
        for(MultipartFile image: images) {
            System.out.println(image.getOriginalFilename());
        }
        for(MultipartFile document: documents) {
            System.out.println(document.getOriginalFilename());
        }
        return service.create(projectJson, images, documents);
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
