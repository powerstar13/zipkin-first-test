package com.example.zipkintest.infrastructure.relation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
}
