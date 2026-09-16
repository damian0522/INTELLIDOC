package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.domain.ports.HistoryRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetDocumentHistoryUseCase {

    private final HistoryRepositoryPort historyRepository;

    public GetDocumentHistoryUseCase(
            HistoryRepositoryPort historyRepository
    ) {
        this.historyRepository = historyRepository;
    }

    public List<HistoryEntry> execute(UUID documentId) {

        return historyRepository.findByDocumentId(documentId);
    }
}