package com.umb.intellidoc.api.infrastructure.persistence.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.infrastructure.persistence.entity.ResultEntity;

import java.time.Instant;
import java.util.UUID;

public final class ResultPersistenceMapper {

    private ResultPersistenceMapper() {
    }

    public static ResultEntity toEntity(
            ProcessingResult result,
            ObjectMapper objectMapper
    ) {

        try {
            String analysisJson =
                    objectMapper.writeValueAsString(
                            result.getAnalysis()
                    );

            return new ResultEntity(
                    result.getId().toString(),
                    result.getDocumentId().toString(),
                    result.getExtractedContent(),
                    analysisJson,
                    result.getProcessedAt(),
                    result.getStatus()
            );

        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "No fue posible convertir el análisis a JSON",
                    exception
            );
        }
    }

    public static ProcessingResult toDomain(
            ResultEntity entity,
            ObjectMapper objectMapper
    ) {

        try {
            AnalysisResult analysis =
                    objectMapper.readValue(
                            entity.getAnalysisJson(),
                            AnalysisResult.class
                    );

            return new ProcessingResult(
                    UUID.fromString(entity.getId()),
                    UUID.fromString(entity.getDocumentId()),
                    entity.getContent(),
                    analysis,
                    entity.getProcessedAt(),
                    entity.getStatus()
            );

        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "No fue posible convertir el JSON del resultado",
                    exception
            );
        }
    }
}