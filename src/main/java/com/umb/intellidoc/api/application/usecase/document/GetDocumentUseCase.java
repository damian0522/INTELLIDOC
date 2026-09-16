package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import com.umb.intellidoc.api.shared.exception.DocumentNotFoundException;

import java.util.UUID;

public class GetDocumentUseCase {

    private final DocumentRepositoryPort documentRepository;

    public GetDocumentUseCase(
            DocumentRepositoryPort documentRepository
    ) {
        this.documentRepository = documentRepository;
    }

    public Document execute(UUID documentId) {

        return documentRepository.findById(documentId)
                .orElseThrow(() ->
                        new DocumentNotFoundException(documentId)
                );
    }
}