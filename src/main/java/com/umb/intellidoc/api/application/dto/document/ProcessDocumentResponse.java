package com.umb.intellidoc.api.application.dto.document;

import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.Document;

import java.util.UUID;

public record ProcessDocumentResponse(
        UUID documentId,
        String status,
        String extractedContent,
        AnalysisResult analysis
) {

    public static ProcessDocumentResponse from(
            Document document,
            String extractedContent,
            AnalysisResult analysis
    ) {
        return new ProcessDocumentResponse(
                document.getId(),
                document.getStatus().name(),
                extractedContent,
                analysis
        );
    }
}