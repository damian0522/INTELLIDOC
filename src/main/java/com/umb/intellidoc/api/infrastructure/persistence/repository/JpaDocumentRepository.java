package com.umb.intellidoc.api.infrastructure.persistence.repository;

import com.umb.intellidoc.api.infrastructure.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaDocumentRepository
        extends JpaRepository<DocumentEntity, String> {

    List<DocumentEntity> findByOwnerId(String ownerId);
}