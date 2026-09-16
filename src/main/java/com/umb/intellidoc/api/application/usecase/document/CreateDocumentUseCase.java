package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.application.dto.document.DocumentResponse;
import com.umb.intellidoc.api.application.dto.document.UploadDocumentRequest;
import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.ports.DocumentContentPort;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;

import java.util.UUID;

public class CreateDocumentUseCase {

    private final DocumentRepositoryPort documentRepository;
    private final DocumentContentPort documentContent;

    public CreateDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            DocumentContentPort documentContent
    ) {
        this.documentRepository = documentRepository;
        this.documentContent = documentContent;
    }

    public DocumentResponse execute(UploadDocumentRequest request) {

        Document document = new Document(
                UUID.randomUUID(),
                request.ownerId(),
                request.originalFilename(),
                request.contentType(),
                request.sizeInBytes()
        );

        documentRepository.save(document);

        documentContent.save(
                document.getId(),
                request.content()
        );

        return DocumentResponse.from(document);
    }
}