package com.umb.intellidoc.api.domain.ports;

import com.umb.intellidoc.api.domain.model.ProcessingResult;

import java.util.Optional;
import java.util.UUID;

public interface ResultRepositoryPort {

    ProcessingResult save(ProcessingResult result);

    Optional<ProcessingResult> findByDocumentId(UUID documentId);
}