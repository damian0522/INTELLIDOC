package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.application.usecase.document.StoreDocumentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class StoreDocumentController {

    private final StoreDocumentUseCase storeDocumentUseCase;

    public StoreDocumentController(
            StoreDocumentUseCase storeDocumentUseCase
    ) {
        this.storeDocumentUseCase = storeDocumentUseCase;
    }

    @PostMapping("/{documentId}/storage")
    public ResponseEntity<DocumentResponse> storeDocument(
            @PathVariable UUID documentId
    ) {

        DocumentResponse response =
                storeDocumentUseCase.execute(documentId);

        return ResponseEntity.ok(response);
    }
}