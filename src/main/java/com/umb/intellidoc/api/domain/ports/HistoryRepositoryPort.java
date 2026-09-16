package com.umb.intellidoc.api.domain.ports;

import com.umb.intellidoc.api.domain.model.HistoryEntry;

import java.util.List;
import java.util.UUID;

public interface HistoryRepositoryPort {

    HistoryEntry save(HistoryEntry entry);

    List<HistoryEntry> findByDocumentId(UUID documentId);

    List<HistoryEntry> findByOwnerId(UUID ownerId);
}