package com.umb.intellidoc.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "resultado")
public class ResultEntity {

    @Id
    @Column(name = "id_resultado", length = 36, nullable = false)
    private String id;

    @Column(name = "id_documento", length = 36, nullable = false, unique = true)
    private String documentId;

    @Column(name = "contenido", columnDefinition = "LONGTEXT")
    private String content;

    @Column(name = "analysis_json", columnDefinition = "JSON")
    private String analysisJson;

    @Column(name = "fecha_procesamiento", nullable = false)
    private Instant processedAt;

    @Column(name = "estado", length = 30, nullable = false)
    private String status;

    protected ResultEntity() {
    }

    public ResultEntity(
            String id,
            String documentId,
            String content,
            String analysisJson,
            Instant processedAt,
            String status
    ) {
        this.id = id;
        this.documentId = documentId;
        this.content = content;
        this.analysisJson = analysisJson;
        this.processedAt = processedAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getContent() {
        return content;
    }

    public String getAnalysisJson() {
        return analysisJson;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }

    public String getStatus() {
        return status;
    }
}