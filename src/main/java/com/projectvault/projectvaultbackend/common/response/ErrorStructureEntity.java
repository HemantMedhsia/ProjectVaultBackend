package com.projectvault.projectvaultbackend.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorStructureEntity {
    private String status;
    private String message;
    private String details;
    private LocalDateTime timestamp;
}
