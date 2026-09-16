package com.umb.intellidoc.api.infrastructure.persistence.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umb.intellidoc.api.domain.model.ProcessingResult;
import com.umb.intellidoc.api.domain.ports.ResultRepositoryPort;
import com.umb.intellidoc.api.infrastructure.persistence.entity.ResultEntity;
import com.umb.intellidoc.api.infrastructure.persistence.mapper.ResultPersistenceMapper;
import com.umb.intellidoc.api.infrastructure.persistence.repository.JpaResultRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ResultRepositoryAdapter
        implements ResultRepositoryPort {

    private final JpaResultRepository repository;
    private final ObjectMapper objectMapper;

    public ResultRepositoryAdapter(
            JpaResultRepository repository,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ProcessingResult save(
            ProcessingResult result
    ) {

        ResultEntity entity =
                ResultPersistenceMapper.toEntity(
                        result,
                        objectMapper
                );

        ResultEntity saved =
                repository.save(entity);

        return ResultPersistenceMapper.toDomain(
                saved,
                objectMapper
        );
    }

    @Override
    public Optional<ProcessingResult> findByDocumentId(
            UUID documentId
    ) {

        return repository
                .findByDocumentId(documentId.toString())
                .map(entity ->
                        ResultPersistenceMapper.toDomain(
                                entity,
                                objectMapper
                        )
                );
    }
}