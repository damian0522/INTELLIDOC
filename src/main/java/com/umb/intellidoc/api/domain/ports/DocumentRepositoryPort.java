package com.umb.intellidoc.api.domain.ports;

import com.umb.intellidoc.api.domain.model.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepositoryPort {

    Document save(Document document);

    Optional<Document> findById(UUID documentId);

    List<Document> findByOwnerId(UUID ownerId);
}