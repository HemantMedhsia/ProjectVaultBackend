package com.projectvault.projectvaultbackend.module.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ProjectCreateRequestDto(
        @NotBlank
        String slug,

        @NotBlank
        String name,

        @NotBlank
        String status,

        @NotEmpty
        List<ProjectSectionDto> sections
) {
}
