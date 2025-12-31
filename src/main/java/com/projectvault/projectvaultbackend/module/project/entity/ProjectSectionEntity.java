package com.projectvault.projectvaultbackend.module.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_sections")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private ProjectEntity project;

    @Column(nullable = false)
    private String type;
    // ENV, VPN, SERVER, CUSTOM

    @Column(nullable = false)
    private String title;
    // Development, UAT, VPN Access

    private Integer orderIndex;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProjectSectionFieldEntity> fields = new ArrayList<>();
}
