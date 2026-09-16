package com.umb.intellidoc.api.domain.model;

public record AnalysisField(
        String name,
        String description,
        String expectedType,
        boolean required,
        String pattern
) {
}