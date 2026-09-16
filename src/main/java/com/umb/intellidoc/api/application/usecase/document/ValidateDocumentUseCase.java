package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import com.umb.intellidoc.api.domain.ports.HistoryRepositoryPort;
import com.umb.intellidoc.api.shared.exception.DocumentNotFoundException;
import com.umb.intellidoc.api.shared.exception.DocumentValidationException;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class ValidateDocumentUseCase {

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "application/pdf",
            "text/plain",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
    );
    private final DocumentRepositoryPort documentRepository;
    private final HistoryRepositoryPort historyRepository;

    public ValidateDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            HistoryRepositoryPort historyRepository
    ) {
        this.documentRepository = documentRepository;
        this.historyRepository = historyRepository;
    }

    public DocumentResponse execute(UUID documentId) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException(documentId));

        document.startValidation();

        validateMetadata(document);

        document.markAsValid();

        documentRepository.save(document);

        HistoryEntry historyEntry =
                new HistoryEntry(
                        UUID.randomUUID(),
                        document.getId(),
                        document.getOwnerId(),
                        Instant.now(),
                        "DOCUMENT_VALIDATED"
                );

        historyRepository.save(historyEntry);

        return DocumentResponse.from(document);
    }

    private void validateMetadata(Document document) {

        if (document.getOriginalFilename() == null
                || document.getOriginalFilename().isBlank()) {

            document.markAsInvalid();

            throw new DocumentValidationException(
                    "El nombre del archivo es obligatorio"
            );
        }

        if (document.getContentType() == null
                || document.getContentType().isBlank()) {

            document.markAsInvalid();

            throw new DocumentValidationException(
                    "El tipo de contenido del archivo es obligatorio"
            );
        }

        if (!ALLOWED_CONTENT_TYPES.contains(document.getContentType())) {

            document.markAsInvalid();

            HistoryEntry historyEntry =
                    new HistoryEntry(
                            UUID.randomUUID(),
                            document.getId(),
                            document.getOwnerId(),
                            Instant.now(),
                            "DOCUMENT_INVALID"
                    );

            historyRepository.save(historyEntry);

            throw new DocumentValidationException(
                    "Tipo de archivo no permitido: "
                            + document.getContentType()
            );
        }

        if (document.getSizeInBytes() <= 0) {

            document.markAsInvalid();

            throw new DocumentValidationException(
                    "El archivo no puede estar vacío"
            );
        }

        if (document.getSizeInBytes() > MAX_FILE_SIZE) {

            document.markAsInvalid();

            throw new DocumentValidationException(
                    "El archivo supera el tamaño máximo permitido de 10 MB"
            );
        }
    }
}