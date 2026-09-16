package com.umb.intellidoc.api.domain.ports;

import java.util.UUID;

public interface ProcessDocumentPort {

    void process(
            UUID documentId,
            String storagePath
    );
}