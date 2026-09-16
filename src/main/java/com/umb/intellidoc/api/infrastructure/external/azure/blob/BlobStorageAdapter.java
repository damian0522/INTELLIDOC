package com.umb.intellidoc.api.infrastructure.external.azure.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobHttpHeaders;
import com.umb.intellidoc.api.domain.ports.BlobStoragePort;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@Component
@Primary
public class BlobStorageAdapter implements BlobStoragePort {

    private final BlobContainerClient containerClient;

    public BlobStorageAdapter(
            BlobContainerClient containerClient
    ) {
        this.containerClient = containerClient;
    }

    @Override
    public String upload(
            UUID documentId,
            String filename,
            String contentType,
            byte[] content
    ) {

        String blobName =
                "documents/" + documentId + "/" + filename;

        BlobClient blobClient = containerClient.getBlobClient(blobName);

        BlobHttpHeaders headers =
                new BlobHttpHeaders()
                        .setContentType(contentType);

        blobClient.upload(
                new ByteArrayInputStream(content),
                content.length,
                true
        );

        blobClient.setHttpHeaders(headers);

        return blobName;
    }

    @Override
    public byte[] download(String storagePath) {

        BlobClient blobClient = containerClient.getBlobClient(storagePath);

        return blobClient
                .downloadContent()
                .toBytes();
    }
}