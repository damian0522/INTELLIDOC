package com.umb.intellidoc.api.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class HistoryEntry {

    private final UUID id;
    private final UUID documentId;
    private final UUID ownerId;
    private final Instant date;
    private final String action;

    public HistoryEntry(
            UUID id,
            UUID documentId,
            UUID ownerId,
            Instant date,
            String action
    ) {
        this.id = Objects.requireNonNull(id);
        this.documentId = Objects.requireNonNull(documentId);
        this.ownerId = Objects.requireNonNull(ownerId);
        this.date = Objects.requireNonNull(date);
        this.action = Objects.requireNonNull(action);
    }

    public UUID getId() {
        return id;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public Instant getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }
}