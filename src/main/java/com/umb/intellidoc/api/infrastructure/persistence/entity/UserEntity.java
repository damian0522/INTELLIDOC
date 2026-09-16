package com.umb.intellidoc.api.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UserEntity {

    @Id
    @Column(name = "id_usuario", length = 36, nullable = false)
    private String id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "correo", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "rol", length = 30, nullable = false)
    private String role;

    protected UserEntity() {
    }

    public UserEntity(
            String id,
            String name,
            String email,
            String password,
            String role
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}