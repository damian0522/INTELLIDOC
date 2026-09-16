package com.umb.intellidoc.api.domain.model;

public record ExtractedField(
        String value,
        Double confidence,
        String sourceText
) {
}