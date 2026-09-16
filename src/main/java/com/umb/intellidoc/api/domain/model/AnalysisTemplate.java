package com.umb.intellidoc.api.domain.model;

import java.util.List;

public record AnalysisTemplate(
        String name,
        DocumentType documentType,
        String objective,
        List<AnalysisField> fields
) {
}