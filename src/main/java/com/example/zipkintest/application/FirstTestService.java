package com.example.zipkintest.application;

import com.example.zipkintest.infrastructure.relation.UserEntity;
import com.example.zipkintest.infrastructure.relation.UserJpaRepository;
import com.example.zipkintest.presentation.UserResource;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Observed
@Service
public class FirstTestService implements TestService {
    private final UserJpaRepository userJpaRepository;
    private final MessagePublisher messagePublisher;
    private final RestTemplate restTemplate;
    private final Logger log;

    public FirstTestService(UserJpaRepository userJpaRepository, MessagePublisher messagePublisher, RestTemplate restTemplate) {
        this.userJpaRepository = userJpaRepository;
        this.messagePublisher = messagePublisher;
        this.restTemplate = restTemplate;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public UserEntity createUser(String name) {
        log.info("[createUser] name={}", name);
        UserEntity saved = userJpaRepository.save(new UserEntity(name));
        log.info("[createUser > savedUser] {}", saved);

        messagePublisher.createdUser(new UserResource(saved.getId(), saved.getName()));

        String hello = restTemplate.getForObject("http://localhost:8081/hello", String.class);
        log.info("[createUser] hello={}", hello);

        return saved;
    }

    @Override
    public void deleteUser(String id) {
        log.info("[deleteUser] id={}", id);
        userJpaRepository.deleteById(id);
        log.info("[deleteUser > deletedUser]");
    }

    @Override
    public void fail() {
        log.info("[fail]");
        restTemplate.getForObject("http://localhost:8081/error", String.class);
    }
}
