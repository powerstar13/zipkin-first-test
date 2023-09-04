package com.example.zipkintest.application;

import com.example.zipkintest.presentation.UserResource;

public interface MessagePublisher {

    void createdUser(UserResource user);
}
