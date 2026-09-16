package com.umb.intellidoc.api.infrastructure.external.azure.functions.document;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Map;

public class ReadDocumentFunction {

    private final AzureBlobReader blobReader =
            new AzureBlobReader();

    @FunctionName("readDocument")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "request",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.FUNCTION,
                    route = "documents/{documentId}"
            )
            HttpRequestMessage<Void> request,

            @BindingName("documentId")
            String documentId,

            ExecutionContext context
    ) {

        String storagePath =
                request.getQueryParameters()
                        .get("storagePath");

        if (storagePath == null || storagePath.isBlank()) {

            return request
                    .createResponseBuilder(
                            HttpStatus.BAD_REQUEST
                    )
                    .body(Map.of(
                            "status", "error",
                            "message",
                            "storagePath es obligatorio"
                    ))
                    .build();
        }

        try {

            AzureBlobReader.BlobReadResult result = blobReader.read(storagePath);

            context.getLogger().info(
                    "Documento leído desde Blob: "
                            + storagePath
            );

            return request
                    .createResponseBuilder(
                            HttpStatus.OK
                    )
                    .header(
                            "Content-Type",
                            "application/json"
                    )
                    .body(Map.of(
                            "status", "ok",
                            "documentId", documentId,
                            "storagePath", storagePath,
                            "contentType",
                            result.contentType(),
                            "sizeInBytes",
                            result.sizeInBytes(),
                            "content",
                            result.content()
                    ))
                    .build();

        } catch (Exception exception) {

            context.getLogger().severe(
                    "Error leyendo documento: "
                            + exception.getMessage()
            );

            return request
                    .createResponseBuilder(
                            HttpStatus.INTERNAL_SERVER_ERROR
                    )
                    .body(Map.of(
                            "status", "error",
                            "message",
                            exception.getMessage()
                    ))
                    .build();
        }
    }
}