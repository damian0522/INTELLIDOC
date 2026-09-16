package com.umb.intellidoc.api.infrastructure.repository;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryDocumentRepository implements DocumentRepositoryPort {

    private final ConcurrentMap<UUID, Document> documents = new ConcurrentHashMap<>();

    @Override
    public Document save(Document document) {
        documents.put(document.getId(), document);
        return document;
    }

    @Override
    public Optional<Document> findById(UUID documentId) {
        return Optional.ofNullable(documents.get(documentId));
    }

    @Override
    public List<Document> findByOwnerId(UUID ownerId) {
        return documents.values()
                .stream()
                .filter(document -> document.getOwnerId().equals(ownerId))
                .toList();
    }
}