package com.umb.intellidoc.api.web.controller.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.application.dto.document.UploadDocumentRequest;
import com.umb.intellidoc.api.application.usecase.document.CreateDocumentUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class CreateDocumentController {

    private final CreateDocumentUseCase createDocumentUseCase;

    public CreateDocumentController(CreateDocumentUseCase createDocumentUseCase) {
        this.createDocumentUseCase = createDocumentUseCase;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hola Mundo desde IntelliDoc";
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponse> uploadDocument(
            @RequestParam("ownerId") UUID ownerId,
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        UploadDocumentRequest request = new UploadDocumentRequest(
                ownerId,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                file.getBytes()
        );

        DocumentResponse response = createDocumentUseCase.execute(request);

        return ResponseEntity.ok(response);
    }
}