package com.umb.intellidoc.api.application.dto.document;

import com.umb.intellidoc.api.domain.model.HistoryEntry;

import java.time.Instant;
import java.util.UUID;

public record HistoryEntryResponse(
        UUID id,
        UUID documentId,
        UUID ownerId,
        Instant date,
        String action
) {

    public static HistoryEntryResponse from(
            HistoryEntry entry
    ) {
        return new HistoryEntryResponse(
                entry.getId(),
                entry.getDocumentId(),
                entry.getOwnerId(),
                entry.getDate(),
                entry.getAction()
        );
    }
}