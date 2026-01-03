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
public class ResponseStructureEntity<T> {
    private String status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}
