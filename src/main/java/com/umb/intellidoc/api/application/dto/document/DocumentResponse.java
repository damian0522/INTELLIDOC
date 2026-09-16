package com.umb.intellidoc.api.application.dto.document;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.model.DocumentStatus;

import java.time.Instant;
import java.util.UUID;

public record DocumentResponse(
        UUID id,
        UUID ownerId,
        String originalFilename,
        String contentType,
        long sizeInBytes,
        DocumentStatus status,
        String storagePath,
        Instant createdAt,
        Instant updatedAt
) {

    public static DocumentResponse from(Document document) {
        return new DocumentResponse(
                document.getId(),
                document.getOwnerId(),
                document.getOriginalFilename(),
                document.getContentType(),
                document.getSizeInBytes(),
                document.getStatus(),
                document.getStoragePath(),
                document.getCreatedAt(),
                document.getUpdatedAt()
        );
    }
}