package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.model.DocumentStatus;
import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.domain.ports.BlobStoragePort;
import com.umb.intellidoc.api.domain.ports.DocumentContentPort;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import com.umb.intellidoc.api.domain.ports.HistoryRepositoryPort;
import com.umb.intellidoc.api.shared.exception.DocumentNotFoundException;
import com.umb.intellidoc.api.shared.exception.DocumentStorageException;

import java.time.Instant;
import java.util.UUID;

public class StoreDocumentUseCase {

    private final DocumentRepositoryPort documentRepository;
    private final DocumentContentPort documentContent;
    private final BlobStoragePort blobStorage;
    private final HistoryRepositoryPort historyRepository;

    public StoreDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            DocumentContentPort documentContent,
            BlobStoragePort blobStorage,
            HistoryRepositoryPort historyRepositoryPort
    ) {
        this.documentRepository = documentRepository;
        this.documentContent = documentContent;
        this.blobStorage = blobStorage;
        this.historyRepository = historyRepositoryPort;
    }

    public DocumentResponse execute(UUID documentId) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));

        if (document.getStatus() != DocumentStatus.VALID) {
            throw new DocumentStorageException(
                    "El documento debe estar validado antes de almacenarse"
            );
        }

        byte[] content = documentContent.findByDocumentId(documentId);

        if (content == null || content.length == 0) {
            throw new DocumentStorageException(
                    "No se encontró el contenido del documento"
            );
        }

        String storagePath = blobStorage.upload(
                document.getId(),
                document.getOriginalFilename(),
                document.getContentType(),
                content
        );

        document.assignStoragePath(storagePath);

        documentRepository.save(document);

        HistoryEntry historyEntry =
                new HistoryEntry(
                        UUID.randomUUID(),
                        document.getId(),
                        document.getOwnerId(),
                        Instant.now(),
                        "DOCUMENT_STORED"
                );

        historyRepository.save(historyEntry);

        return DocumentResponse.from(document);
    }
}