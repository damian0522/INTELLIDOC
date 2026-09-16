package com.umb.intellidoc.api.infrastructure.repository;

import com.umb.intellidoc.api.domain.ports.DocumentContentPort;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryDocumentContent implements DocumentContentPort {

    private final ConcurrentMap<UUID, byte[]> contents =
            new ConcurrentHashMap<>();

    @Override
    public void save(UUID documentId, byte[] content) {
        contents.put(documentId, content);
    }

    @Override
    public byte[] findByDocumentId(UUID documentId) {
        return contents.get(documentId);
    }
}