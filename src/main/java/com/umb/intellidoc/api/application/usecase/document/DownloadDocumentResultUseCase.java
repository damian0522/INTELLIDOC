package com.umb.intellidoc.api.application.usecase.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.domain.ports.ResultRepositoryPort;

import java.util.UUID;

public class DownloadDocumentResultUseCase {

    private final ResultRepositoryPort resultRepository;
    private final ObjectMapper objectMapper;

    public DownloadDocumentResultUseCase(
            ResultRepositoryPort resultRepository,
            ObjectMapper objectMapper
    ) {
        this.resultRepository = resultRepository;
        this.objectMapper = objectMapper;
    }

    public byte[] execute(UUID documentId) {

        ProcessingResult result =
                resultRepository.findByDocumentId(documentId)
                        .orElseThrow(() ->
                                new IllegalStateException(
                                        "No existe un resultado para el documento: "
                                                + documentId
                                )
                        );

        try {
            return objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsBytes(result);

        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "No fue posible generar el archivo de resultado",
                    exception
            );
        }
    }
}