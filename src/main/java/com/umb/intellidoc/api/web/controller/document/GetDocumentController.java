package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.application.usecase.document.GetDocumentUseCase;
import com.umb.intellidoc.api.domain.model.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class GetDocumentController {

    private final GetDocumentUseCase getDocumentUseCase;

    public GetDocumentController(
            GetDocumentUseCase getDocumentUseCase
    ) {
        this.getDocumentUseCase = getDocumentUseCase;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentResponse> getDocument(
            @PathVariable UUID documentId
    ) {

        Document document =
                getDocumentUseCase.execute(documentId);

        return ResponseEntity.ok(
                DocumentResponse.from(document)
        );
    }
}