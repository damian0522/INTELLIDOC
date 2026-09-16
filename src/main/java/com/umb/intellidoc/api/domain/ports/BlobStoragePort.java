package com.umb.intellidoc.api.domain.ports;

import java.util.UUID;

public interface BlobStoragePort {

    String upload(
            UUID documentId,
            String filename,
            String contentType,
            byte[] content
    );

    byte[] download(String storagePath);
}