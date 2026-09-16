package com.umb.intellidoc.api.application.usecase.document;

import com.umb.intellidoc.api.application.dto.document.ProcessDocumentResponse;
import com.umb.intellidoc.api.domain.model.Document;
import com.umb.intellidoc.api.domain.model.DocumentStatus;
import com.umb.intellidoc.api.domain.ports.AiAnalysisPort;
import com.umb.intellidoc.api.domain.ports.BlobStoragePort;
import com.umb.intellidoc.api.domain.ports.DocumentContentExtractorPort;
import com.umb.intellidoc.api.domain.ports.DocumentRepositoryPort;
import com.umb.intellidoc.api.shared.exception.DocumentNotFoundException;
import com.umb.intellidoc.api.domain.model.AnalysisResult;
import com.umb.intellidoc.api.domain.model.AnalysisTemplate;
import com.umb.intellidoc.api.domain.model.HistoryEntry;
import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.domain.ports.HistoryRepositoryPort;
import com.umb.intellidoc.api.domain.ports.ResultRepositoryPort;

import java.time.Instant;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ProcessDocumentUseCase {

    private final DocumentRepositoryPort documentRepository;
    private final BlobStoragePort blobStorage;
    private final DocumentContentExtractorPort contentExtractor;
    private final AiAnalysisPort aiAnalysisPort;
    private final ResultRepositoryPort resultRepository;
    private final HistoryRepositoryPort historyRepository;

    public ProcessDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            BlobStoragePort blobStorage,
            DocumentContentExtractorPort contentExtractor,
            AiAnalysisPort aiAnalysisPort,
            ResultRepositoryPort resultRepository,
            HistoryRepositoryPort historyRepository
    ) {
        this.documentRepository = documentRepository;
        this.blobStorage = blobStorage;
        this.contentExtractor = contentExtractor;
        this.aiAnalysisPort = aiAnalysisPort;
        this.resultRepository = resultRepository;
        this.historyRepository = historyRepository;
    }

    public ProcessDocumentResponse execute(UUID documentId) {

        Document document = documentRepository.findById(documentId)
                .orElseThrow(
                        () -> new DocumentNotFoundException(documentId)
                );

        if (document.getStatus() != DocumentStatus.VALID) {
            throw new IllegalStateException(
                    "El documento debe estar validado antes de procesarse"
            );
        }

        if (document.getStoragePath() == null
                || document.getStoragePath().isBlank()) {

            throw new IllegalStateException(
                    "El documento debe estar almacenado antes de procesarse"
            );
        }

        document.startProcessing();
        documentRepository.save(document);

        try {

            byte[] content =
                    blobStorage.download(
                            document.getStoragePath()
                    );

            String extractedText =
                    contentExtractor.extract(
                            content,
                            document.getContentType(),
                            document.getOriginalFilename()
                    );

            AnalysisTemplate template =
                    InvoiceAnalysisTemplate.create();

            AnalysisResult analysisResult = aiAnalysisPort.analyze(
                    extractedText,
                    template
            );

            ProcessingResult processingResult =
                    new ProcessingResult(
                            UUID.randomUUID(),
                            document.getId(),
                            extractedText,
                            analysisResult,
                            Instant.now(),
                            "PROCESSED"
                    );

            resultRepository.save(processingResult);

            document.markAsProcessed();
            documentRepository.save(document);

            HistoryEntry historyEntry =
                    new HistoryEntry(
                            UUID.randomUUID(),
                            document.getId(),
                            document.getOwnerId(),
                            Instant.now(),
                            "DOCUMENT_PROCESSED"
                    );

            historyRepository.save(historyEntry);

            return ProcessDocumentResponse.from(
                    document,
                    extractedText,
                    analysisResult
            );

        } catch (Exception exception) {

            document.markAsFailed();
            documentRepository.save(document);

            throw new IllegalStateException(
                    "Error procesando el documento: "
                            + exception.getMessage(),
                    exception
            );
        }
    }
}