package com.example.zipkintest.infrastructure.relation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(columnDefinition = "char(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected UserEntity() {
    }

    public UserEntity(String name) {
        Assert.hasText(name, "name must not be null and must contain at least one non-whitespace character.");
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}