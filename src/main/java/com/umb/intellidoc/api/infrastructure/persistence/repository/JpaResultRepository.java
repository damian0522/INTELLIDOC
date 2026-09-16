package com.umb.intellidoc.api.infrastructure.persistence.repository;

import com.umb.intellidoc.api.infrastructure.persistence.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaResultRepository
        extends JpaRepository<ResultEntity, String> {

    Optional<ResultEntity> findByDocumentId(String documentId);
}