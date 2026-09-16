package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.usecase.document.GetDocumentResultUseCase;
import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.application.dto.document.DocumentResultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class GetDocumentResultController {

    private final GetDocumentResultUseCase getDocumentResultUseCase;

    public GetDocumentResultController(
            GetDocumentResultUseCase getDocumentResultUseCase
    ) {
        this.getDocumentResultUseCase = getDocumentResultUseCase;
    }

    @GetMapping("/{documentId}/result")
    public ResponseEntity<DocumentResultResponse> getResult(
            @PathVariable UUID documentId
    ) {

        ProcessingResult result =
                getDocumentResultUseCase.execute(documentId);

        return ResponseEntity.ok(
                DocumentResultResponse.from(result)
        );
    }
}