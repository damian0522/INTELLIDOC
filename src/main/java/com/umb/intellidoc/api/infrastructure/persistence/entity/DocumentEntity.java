package com.umb.intellidoc.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "documento")
public class DocumentEntity {

    @Id
    @Column(name = "id_documento", length = 36, nullable = false)
    private String id;

    @Column(name = "id_usuario", length = 36, nullable = false)
    private String ownerId;

    @Column(name = "nombre", length = 200, nullable = false)
    private String filename;

    @Column(name = "tipo", length = 50, nullable = false)
    private String contentType;

    @Column(name = "tamano_bytes", nullable = false)
    private long sizeInBytes;

    @Column(name = "ruta_blob", length = 500)
    private String storagePath;

    @Column(name = "fecha_carga", nullable = false)
    private Instant createdAt;

    @Column(name = "fecha_actualizacion", nullable = false)
    private Instant updatedAt;

    @Column(name = "estado", length = 30, nullable = false)
    private String status;

    protected DocumentEntity() {
    }

    public DocumentEntity(
            String id,
            String ownerId,
            String filename,
            String contentType,
            long sizeInBytes,
            String storagePath,
            Instant createdAt,
            Instant updatedAt,
            String status
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.filename = filename;
        this.contentType = contentType;
        this.sizeInBytes = sizeInBytes;
        this.storagePath = storagePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getFilename() {
        return filename;
    }

    public String getContentType() {
        return contentType;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getStatus() {
        return status;
    }
}