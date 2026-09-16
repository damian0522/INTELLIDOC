package com.umb.intellidoc.api.infrastructure.external.azure.functions;

import java.util.UUID;

public record ProcessDocumentRequest(
        UUID documentId,
        String storagePath
) {
}