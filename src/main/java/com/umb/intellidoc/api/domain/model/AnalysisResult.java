package com.umb.intellidoc.api.domain.model;

import java.util.Map;

public record AnalysisResult(
        DocumentType documentType,
        Map<String, ExtractedField> requestedFields,
        Map<String, ExtractedField> additionalFindings
) {
}