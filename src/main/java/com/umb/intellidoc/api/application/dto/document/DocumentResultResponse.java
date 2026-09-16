package com.umb.intellidoc.api.application.dto.document;

import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.ProcessingResult;

import java.time.Instant;
import java.util.UUID;

public record DocumentResultResponse(
        UUID id,
        UUID documentId,
        String extractedContent,
        AnalysisResult analysis,
        Instant processedAt,
        String status
) {

    public static DocumentResultResponse from(
            ProcessingResult result
    ) {
        return new DocumentResultResponse(
                result.getId(),
                result.getDocumentId(),
                result.getExtractedContent(),
                result.getAnalysis(),
                result.getProcessedAt(),
                result.getStatus()
        );
    }
}