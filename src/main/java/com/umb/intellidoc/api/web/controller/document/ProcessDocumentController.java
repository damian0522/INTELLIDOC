package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.dto.document.ProcessDocumentResponse;
import com.umb.intellidoc.api.application.usecase.document.ProcessDocumentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class ProcessDocumentController {

    private final ProcessDocumentUseCase processDocumentUseCase;

    public ProcessDocumentController(
            ProcessDocumentUseCase processDocumentUseCase
    ) {
        this.processDocumentUseCase = processDocumentUseCase;
    }

    @PostMapping("/{documentId}/process")
    public ResponseEntity<ProcessDocumentResponse> processDocument(
            @PathVariable UUID documentId
    ) {

        ProcessDocumentResponse response =
                processDocumentUseCase.execute(documentId);

        return ResponseEntity.ok(response);
    }
}