package com.umb.intellidoc.api.application.dto.document;

import java.util.UUID;

public record UploadDocumentRequest(
        UUID ownerId,
        String originalFilename,
        String contentType,
        long sizeInBytes,
        byte[] content
) {
}