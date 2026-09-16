package com.umb.intellidoc.api.infrastructure.persistence.adapter;

import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.domain.ports.HistoryRepositoryPort;
import com.umb.intellidoc.api.infrastructure.persistence.entity.HistoryEntity;
import com.umb.intellidoc.api.infrastructure.persistence.mapper.HistoryPersistenceMapper;
import com.umb.intellidoc.api.infrastructure.persistence.repository.JpaHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class HistoryRepositoryAdapter
        implements HistoryRepositoryPort {

    private final JpaHistoryRepository repository;

    public HistoryRepositoryAdapter(
            JpaHistoryRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public HistoryEntry save(
            HistoryEntry entry
    ) {

        HistoryEntity entity =
                HistoryPersistenceMapper.toEntity(entry);

        HistoryEntity saved =
                repository.save(entity);

        return HistoryPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<HistoryEntry> findByDocumentId(
            UUID documentId
    ) {

        return repository
                .findByDocumentIdOrderByDateAsc(
                        documentId.toString()
                )
                .stream()
                .map(HistoryPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<HistoryEntry> findByOwnerId(
            UUID ownerId
    ) {

        return repository
                .findByOwnerIdOrderByDateDesc(
                        ownerId.toString()
                )
                .stream()
                .map(HistoryPersistenceMapper::toDomain)
                .toList();
    }
}