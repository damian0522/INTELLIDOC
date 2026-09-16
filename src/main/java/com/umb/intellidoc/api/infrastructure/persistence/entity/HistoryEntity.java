package com.umb.intellidoc.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "historial")
public class HistoryEntity {

    @Id
    @Column(name = "id_historial", length = 36, nullable = false)
    private String id;

    @Column(name = "id_documento", length = 36, nullable = false)
    private String documentId;

    @Column(name = "id_usuario", length = 36, nullable = false)
    private String ownerId;

    @Column(name = "fecha", nullable = false)
    private Instant date;

    @Column(name = "accion", length = 100, nullable = false)
    private String action;

    protected HistoryEntity() {
    }

    public HistoryEntity(
            String id,
            String documentId,
            String ownerId,
            Instant date,
            String action
    ) {
        this.id = id;
        this.documentId = documentId;
        this.ownerId = ownerId;
        this.date = date;
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Instant getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }
}