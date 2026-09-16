package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.usecase.document.DownloadDocumentResultUseCase;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class DownloadDocumentResultController {

    private final DownloadDocumentResultUseCase downloadDocumentResultUseCase;

    public DownloadDocumentResultController(
            DownloadDocumentResultUseCase downloadDocumentResultUseCase
    ) {
        this.downloadDocumentResultUseCase =
                downloadDocumentResultUseCase;
    }

    @GetMapping("/{documentId}/result/download")
    public ResponseEntity<byte[]> downloadResult(
            @PathVariable UUID documentId
    ) {

        byte[] content =
                downloadDocumentResultUseCase.execute(documentId);

        String filename =
                "resultado-" + documentId + ".json";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(
                MediaType.APPLICATION_JSON
        );

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename(filename)
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(content);
    }
}