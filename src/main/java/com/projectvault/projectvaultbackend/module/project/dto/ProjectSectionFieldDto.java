package com.projectvault.projectvaultbackend.module.project.dto;

import jakarta.validation.constraints.NotBlank;

public record ProjectSectionFieldDto(
        @NotBlank
        String fieldKey,

        @NotBlank
        String fieldValue
) {}
