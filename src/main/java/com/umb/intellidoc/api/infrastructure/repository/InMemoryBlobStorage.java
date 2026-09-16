package com.umb.intellidoc.api.infrastructure.repository;

import com.umb.intellidoc.api.domain.ports.BlobStoragePort;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class InMemoryBlobStorage implements BlobStoragePort {

    private final ConcurrentMap<String, byte[]> storage = new ConcurrentHashMap<>();

    @Override
    public String upload(
            UUID documentId,
            String filename,
            String contentType,
            byte[] content
    ) {
        String storagePath = "documents/" + documentId + "/" + filename;

        storage.put(storagePath, content);

        return storagePath;
    }

    @Override
    public byte[] download(String storagePath) {
        return storage.get(storagePath);
    }
}