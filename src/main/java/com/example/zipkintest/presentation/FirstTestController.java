package com.example.zipkintest.presentation;

import com.example.zipkintest.application.TestService;
import com.example.zipkintest.infrastructure.relation.UserEntity;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Observed
@RestController
public class FirstTestController {
    private final TestService testService;
    private final Logger log;

    public FirstTestController(TestService testService) {
        this.testService = testService;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @PostMapping("/user")
    public UserEntity createUser(@RequestBody UserResource user) {
        log.info("[createUser] {}", user);
        return testService.createUser(user.name());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        log.info("[deleteUser] id={}", id);
        testService.deleteUser(id);
    }

    @GetMapping("/fail")
    public void fail() {
        log.info("[fail]");
        testService.fail();
    }
}
