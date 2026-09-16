package com.umb.intellidoc.api.infrastructure.persistence.repository;

import com.umb.intellidoc.api.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository
        extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}