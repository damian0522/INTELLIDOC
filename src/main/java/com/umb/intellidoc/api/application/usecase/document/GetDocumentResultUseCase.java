package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.domain.ports.ResultRepositoryPort;

import java.util.UUID;

public class GetDocumentResultUseCase {

    private final ResultRepositoryPort resultRepository;

    public GetDocumentResultUseCase(
            ResultRepositoryPort resultRepository
    ) {
        this.resultRepository = resultRepository;
    }

    public ProcessingResult execute(UUID documentId) {

        return resultRepository.findByDocumentId(documentId)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No existe un resultado para el documento: "
                                        + documentId
                        )
                );
    }
}