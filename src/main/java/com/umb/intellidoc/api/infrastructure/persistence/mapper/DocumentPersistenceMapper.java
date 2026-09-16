package com.umb.intellidoc.api.infrastructure.persistence.mapper;

import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.model.DocumentStatus;
import com.umb.intellidoc.api.infrastructure.persistence.entity.DocumentEntity;

import java.util.UUID;

public final class DocumentPersistenceMapper {

    private DocumentPersistenceMapper() {
    }

    public static DocumentEntity toEntity(Document document) {

        return new DocumentEntity(
                document.getId().toString(),
                document.getOwnerId().toString(),
                document.getOriginalFilename(),
                document.getContentType(),
                document.getSizeInBytes(),
                document.getStoragePath(),
                document.getCreatedAt(),
                document.getUpdatedAt(),
                document.getStatus().name()
        );
    }

    public static Document toDomain(DocumentEntity entity) {

        return Document.reconstruct(
                UUID.fromString(entity.getId()),
                UUID.fromString(entity.getOwnerId()),
                entity.getFilename(),
                entity.getContentType(),
                entity.getSizeInBytes(),
                DocumentStatus.valueOf(entity.getStatus()),
                entity.getStoragePath(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}