package com.example.zipkintest.application;

import com.example.zipkintest.infrastructure.relation.UserEntity;

public interface TestService {
    UserEntity createUser(String name);

    void deleteUser(String id);

    void fail();
}
