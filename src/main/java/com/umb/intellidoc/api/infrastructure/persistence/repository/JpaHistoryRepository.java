package com.umb.intellidoc.api.infrastructure.persistence.repository;

import com.umb.intellidoc.api.infrastructure.persistence.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaHistoryRepository
        extends JpaRepository<HistoryEntity, String> {

    List<HistoryEntity> findByDocumentIdOrderByDateAsc(
            String documentId
    );

    List<HistoryEntity> findByOwnerIdOrderByDateDesc(
            String ownerId
    );
}