package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.application.usecase.document.ValidateDocumentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class ValidateDocumentController {

    private final ValidateDocumentUseCase validateDocumentUseCase;

    public ValidateDocumentController(
            ValidateDocumentUseCase validateDocumentUseCase
    ) {
        this.validateDocumentUseCase = validateDocumentUseCase;
    }

    @PostMapping("/{documentId}/validation")
    public ResponseEntity<DocumentResponse> validateDocument(
            @PathVariable UUID documentId
    ) {

        DocumentResponse response =
                validateDocumentUseCase.execute(documentId);

        return ResponseEntity.ok(response);
    }
}