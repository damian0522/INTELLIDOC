package com.umb.intellidoc.api.infrastructure.persistence.repository;

import com.umb.intellidoc.api.infrastructure.persistence.entity.ConfigurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaConfigurationRepository
        extends JpaRepository<ConfigurationEntity, String> {

    Optional<ConfigurationEntity> findByParameter(
            String parameter
    );
}