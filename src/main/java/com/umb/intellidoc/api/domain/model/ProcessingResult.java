package com.umb.intellidoc.api.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class ProcessingResult {

    private final UUID id;
    private final UUID documentId;
    private final String extractedContent;
    private final AnalysisResult analysis;
    private final Instant processedAt;
    private final String status;

    public ProcessingResult(
            UUID id,
            UUID documentId,
            String extractedContent,
            AnalysisResult analysis,
            Instant processedAt,
            String status
    ) {
        this.id = Objects.requireNonNull(id);
        this.documentId = Objects.requireNonNull(documentId);
        this.extractedContent = extractedContent;
        this.analysis = Objects.requireNonNull(analysis);
        this.processedAt = Objects.requireNonNull(processedAt);
        this.status = Objects.requireNonNull(status);
    }

    public UUID getId() {
        return id;
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public String getExtractedContent() {
        return extractedContent;
    }

    public AnalysisResult getAnalysis() {
        return analysis;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public String getStatus() {
        return status;
    }
}