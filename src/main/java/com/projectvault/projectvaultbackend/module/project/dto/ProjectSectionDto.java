package com.projectvault.projectvaultbackend.module.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjectSectionDto(
        @NotBlank
        String type,

        @NotBlank
        String title,

        @NotNull
        Integer orderIndex,

        List<ProjectSectionFieldDto> fields
) {}
