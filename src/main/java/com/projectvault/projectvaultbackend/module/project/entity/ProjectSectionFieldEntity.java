package com.projectvault.projectvaultbackend.module.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_section_fields")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectSectionFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnore
    private ProjectSectionEntity section;

    @Column(nullable = false)
    private String fieldKey;
    // url, username, password, login

    @Column(nullable = false, columnDefinition = "TEXT")
    private String fieldValue;
}
