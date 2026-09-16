package com.umb.intellidoc.api.infrastructure.persistence.mapper;

import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.infrastructure.persistence.entity.HistoryEntity;

import java.util.UUID;

public final class HistoryPersistenceMapper {

    private HistoryPersistenceMapper() {
    }

    public static HistoryEntity toEntity(
            HistoryEntry entry
    ) {

        return new HistoryEntity(
                entry.getId().toString(),
                entry.getDocumentId().toString(),
                entry.getOwnerId().toString(),
                entry.getDate(),
                entry.getAction()
        );
    }

    public static HistoryEntry toDomain(
            HistoryEntity entity
    ) {

        return new HistoryEntry(
                UUID.fromString(entity.getId()),
                UUID.fromString(entity.getDocumentId()),
                UUID.fromString(entity.getOwnerId()),
                entity.getDate(),
                entity.getAction()
        );
    }
}