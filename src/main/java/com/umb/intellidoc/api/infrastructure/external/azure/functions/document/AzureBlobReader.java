package com.umb.intellidoc.api.infrastructure.external.azure.functions.document;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class AzureBlobReader {

    private final BlobContainerClient containerClient;

    public AzureBlobReader() {

        String accountName =
                System.getenv("AZURE_STORAGE_ACCOUNT_NAME");

        String containerName =
                System.getenv("AZURE_STORAGE_CONTAINER_NAME");

        DefaultAzureCredential credential =
                new DefaultAzureCredentialBuilder()
                        .build();

        BlobServiceClient blobServiceClient =
                new BlobServiceClientBuilder()
                        .endpoint(
                                "https://"
                                        + accountName
                                        + ".blob.core.windows.net"
                        )
                        .credential(credential)
                        .buildClient();

        this.containerClient =
                blobServiceClient.getBlobContainerClient(containerName);
    }

    public BlobReadResult read(String storagePath) {

        BlobClient blobClient =
                containerClient.getBlobClient(storagePath);

        if (!blobClient.exists()) {
            throw new IllegalArgumentException(
                    "Blob no encontrado: " + storagePath
            );
        }

        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        blobClient.downloadStream(output);

        String content =
                output.toString(StandardCharsets.UTF_8);

        var properties =
                blobClient.getProperties();

        return new BlobReadResult(
                content,
                properties.getContentType(),
                properties.getBlobSize()
        );
    }

    public record BlobReadResult(
            String content,
            String contentType,
            long sizeInBytes
    ) {
    }
}