package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;

import java.util.List;
import java.util.UUID;

public class ListDocumentsByOwnerUseCase {

    private final DocumentRepositoryPort documentRepository;

    public ListDocumentsByOwnerUseCase(
            DocumentRepositoryPort documentRepository
    ) {
        this.documentRepository = documentRepository;
    }

    public List<Document> execute(UUID ownerId) {

        return documentRepository.findByOwnerId(ownerId);
    }
}