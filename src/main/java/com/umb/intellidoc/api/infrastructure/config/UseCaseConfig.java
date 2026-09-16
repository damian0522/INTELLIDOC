package com.umb.intellidoc.api.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umb.intellidoc.api.application.usecase.document.*;
import com.umb.intellidoc.api.domain.ports.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateDocumentUseCase createDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            DocumentContentPort documentContent
    ) {
        return new CreateDocumentUseCase(
                documentRepository,
                documentContent
        );
    }

    @Bean
    public ValidateDocumentUseCase validateDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            HistoryRepositoryPort historyRepositoryPort
    ) {
        return new ValidateDocumentUseCase(
                documentRepository,
                historyRepositoryPort
        );
    }

    @Bean
    public StoreDocumentUseCase storeDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            DocumentContentPort documentContent,
            BlobStoragePort blobStorage,
            HistoryRepositoryPort historyRepository
    ) {
        return new StoreDocumentUseCase(
                documentRepository,
                documentContent,
                blobStorage,
                historyRepository
        );
    }

    @Bean
    public ProcessDocumentUseCase processDocumentUseCase(
            DocumentRepositoryPort documentRepository,
            BlobStoragePort blobStorage,
            DocumentContentExtractorPort contentExtractor,
            AiAnalysisPort aiAnalysisPort,
            ResultRepositoryPort resultRepositoryPort,
            HistoryRepositoryPort historyRepositoryPort
    ) {
        return new ProcessDocumentUseCase(
                documentRepository,
                blobStorage,
                contentExtractor,
                aiAnalysisPort,
                resultRepositoryPort,
                historyRepositoryPort

        );
    }

    @Bean
    public GetDocumentUseCase getDocumentUseCase(
            DocumentRepositoryPort documentRepository
    ) {
        return new GetDocumentUseCase(documentRepository);
    }

    @Bean
    public GetDocumentResultUseCase getDocumentResultUseCase(
            ResultRepositoryPort resultRepository
    ) {
        return new GetDocumentResultUseCase(resultRepository);
    }

    @Bean
    public ListDocumentsByOwnerUseCase listDocumentsByOwnerUseCase(
            DocumentRepositoryPort documentRepository
    ) {
        return new ListDocumentsByOwnerUseCase(
                documentRepository
        );
    }

    @Bean
    public GetDocumentHistoryUseCase getDocumentHistoryUseCase(
            HistoryRepositoryPort historyRepository
    ) {
        return new GetDocumentHistoryUseCase(
                historyRepository
        );
    }

    @Bean
    public DownloadDocumentResultUseCase downloadDocumentResultUseCase(
            ResultRepositoryPort resultRepository,
            ObjectMapper objectMapper
    ) {
        return new DownloadDocumentResultUseCase(
                resultRepository,
                objectMapper
        );
    }
}