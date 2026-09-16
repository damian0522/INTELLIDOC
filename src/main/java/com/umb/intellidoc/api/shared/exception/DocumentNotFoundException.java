package com.umb.intellidoc.api.shared.exception;

import java.util.UUID;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(UUID documentId) {
        super("No se encontró el documento con id: " + documentId);
    }
}