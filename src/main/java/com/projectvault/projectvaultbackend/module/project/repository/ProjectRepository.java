package com.projectvault.projectvaultbackend.module.project.repository;

import com.projectvault.projectvaultbackend.module.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<ProjectEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
