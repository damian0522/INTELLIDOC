package com.umb.intellidoc.api.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Document {

    private final UUID id;
    private final UUID ownerId;
    private final String originalFilename;
    private final String contentType;
    private final long sizeInBytes;

    private DocumentStatus status;

    private String storagePath;

    private Instant createdAt;
    private Instant updatedAt;

    public Document(
            UUID id,
            UUID ownerId,
            String originalFilename,
            String contentType,
            long sizeInBytes
    ) {
        this.id = Objects.requireNonNull(id, "Document id is required");
        this.ownerId = Objects.requireNonNull(ownerId, "Owner id is required");
        this.originalFilename = Objects.requireNonNull(
                originalFilename,
                "Original filename is required"
        );
        this.contentType = Objects.requireNonNull(
                contentType,
                "Content type is required"
        );

        if (sizeInBytes <= 0) {
            throw new IllegalArgumentException("Document size must be greater than zero");
        }

        this.sizeInBytes = sizeInBytes;
        this.status = DocumentStatus.RECEIVED;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public static Document reconstruct(
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
        Document document = new Document(
                id,
                ownerId,
                originalFilename,
                contentType,
                sizeInBytes
        );

        document.status = status;
        document.storagePath = storagePath;
        document.createdAt = createdAt;
        document.updatedAt = updatedAt;

        return document;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void markAsValid() {
        this.status = DocumentStatus.VALID;
        touch();
    }

    public void markAsInvalid() {
        this.status = DocumentStatus.INVALID;
        touch();
    }

    public void startValidation() {
        this.status = DocumentStatus.VALIDATING;
        touch();
    }

    public void startProcessing() {
        this.status = DocumentStatus.PROCESSING;
        touch();
    }

    public void markAsProcessed() {
        this.status = DocumentStatus.PROCESSED;
        touch();
    }

    public void markAsFailed() {
        this.status = DocumentStatus.FAILED;
        touch();
    }

    public void assignStoragePath(String storagePath) {
        this.storagePath = Objects.requireNonNull(
                storagePath,
                "Storage path is required"
        );
        touch();
    }

    private void touch() {
        this.updatedAt = Instant.now();
    }
}