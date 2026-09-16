package com.umb.intellidoc.api.infrastructure.persistence.adapter;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import com.umb.intellidoc.api.infrastructure.persistence.entity.DocumentEntity;
import com.umb.intellidoc.api.infrastructure.persistence.mapper.DocumentPersistenceMapper;
import com.umb.intellidoc.api.infrastructure.persistence.repository.JpaDocumentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DocumentRepositoryAdapter
        implements DocumentRepositoryPort {

    private final JpaDocumentRepository repository;

    public DocumentRepositoryAdapter(
            JpaDocumentRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Document save(Document document) {

        DocumentEntity entity =
                DocumentPersistenceMapper.toEntity(document);

        DocumentEntity saved =
                repository.save(entity);

        return DocumentPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Document> findById(UUID documentId) {

        return repository.findById(documentId.toString())
                .map(DocumentPersistenceMapper::toDomain);
    }

    @Override
    public List<Document> findByOwnerId(UUID ownerId) {

        return repository.findByOwnerId(ownerId.toString())
                .stream()
                .map(DocumentPersistenceMapper::toDomain)
                .toList();
    }
}