package com.umb.intellidoc.api.domain.ports;

import java.util.UUID;

public interface DocumentContentPort {

    void save(UUID documentId, byte[] content);

    byte[] findByDocumentId(UUID documentId);
}