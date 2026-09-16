package com.umb.intellidoc.api.infrastructure.external.azure.functions;

import com.umb.intellidoc.api.domain.ports.ProcessDocumentPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Component
public class AzureFunctionAdapter implements ProcessDocumentPort {

    private final RestClient restClient;
    private final AzureFunctionProperties properties;

    public AzureFunctionAdapter(
            RestClient restClient,
            AzureFunctionProperties properties
    ) {
        this.restClient = restClient;
        this.properties = properties;
    }

    @Override
    public void process(
            UUID documentId,
            String storagePath
    ) {

        ProcessDocumentRequest request =
                new ProcessDocumentRequest(
                        documentId,
                        storagePath
                );

        restClient.post()
                .uri(properties.getUrl())
                .header("x-functions-key", properties.getKey())
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}