package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.usecase.document.GetDocumentHistoryUseCase;
import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.application.dto.document.HistoryEntryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class GetDocumentHistoryController {

    private final GetDocumentHistoryUseCase getDocumentHistoryUseCase;

    public GetDocumentHistoryController(
            GetDocumentHistoryUseCase getDocumentHistoryUseCase
    ) {
        this.getDocumentHistoryUseCase =
                getDocumentHistoryUseCase;
    }

    @GetMapping("/{documentId}/history")
    public ResponseEntity<List<HistoryEntryResponse>> getHistory(
            @PathVariable UUID documentId
    ) {

        List<HistoryEntry> history =
                getDocumentHistoryUseCase.execute(documentId);

        return ResponseEntity.ok(
                history.stream()
                        .map(HistoryEntryResponse::from)
                        .toList()
        );
    }
}