package com.projectvault.projectvaultbackend.module.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_files")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;
    private String storedName;
    private String contentType;
    private Long size;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String storagePathUrl;

    @Column(nullable = false)
    private String publicID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private ProjectEntity project;
}
