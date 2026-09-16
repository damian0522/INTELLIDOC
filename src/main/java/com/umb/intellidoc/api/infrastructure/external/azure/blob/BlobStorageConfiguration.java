package com.umb.intellidoc.api.infrastructure.external.azure.blob;

import com.azure.identity.DefaultAzureCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BlobStorageProperties.class)
public class BlobStorageConfiguration {

    @Bean
    public BlobServiceClient blobServiceClient(
            BlobStorageProperties properties
    ) {

        DefaultAzureCredential credential =
                new DefaultAzureCredentialBuilder()
                        .build();

        String endpoint =
                "https://" + properties.getAccountName()
                        + ".blob.core.windows.net";

        return new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(credential)
                .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient(
            BlobServiceClient blobServiceClient,
            BlobStorageProperties properties
    ) {

        return blobServiceClient
                .getBlobContainerClient(
                        properties.getContainerName()
                );
    }
}