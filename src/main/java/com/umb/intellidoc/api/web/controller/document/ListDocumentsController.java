package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.usecase.document.ListDocumentsByOwnerUseCase;
import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class ListDocumentsController {

    private final ListDocumentsByOwnerUseCase listDocumentsByOwnerUseCase;

    public ListDocumentsController(
            ListDocumentsByOwnerUseCase listDocumentsByOwnerUseCase
    ) {
        this.listDocumentsByOwnerUseCase =
                listDocumentsByOwnerUseCase;
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> listDocuments(
            @RequestParam UUID ownerId
    ) {

        List<Document> documents =
                listDocumentsByOwnerUseCase.execute(ownerId);

        List<DocumentResponse> response =
                documents.stream()
                        .map(DocumentResponse::from)
                        .toList();

        return ResponseEntity.ok(response);
    }
}