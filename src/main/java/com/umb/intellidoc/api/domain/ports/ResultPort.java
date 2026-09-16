package com.umb.intellidoc.api.domain.ports;

import java.util.Optional;
import java.util.UUID;

public interface ResultPort {

    void save(UUID documentId, String result);

    Optional<String> findByDocumentId(UUID documentId);
}