package com.umb.intellidoc.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuracion")
public class ConfigurationEntity {

    @Id
    @Column(name = "id_configuracion", length = 36, nullable = false)
    private String id;

    @Column(name = "parametro", length = 100, nullable = false, unique = true)
    private String parameter;

    @Column(name = "valor", length = 500, nullable = false)
    private String value;

    protected ConfigurationEntity() {
    }

    public ConfigurationEntity(
            String id,
            String parameter,
            String value
    ) {
        this.id = id;
        this.parameter = parameter;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getParameter() {
        return parameter;
    }

    public String getValue() {
        return value;
    }
}