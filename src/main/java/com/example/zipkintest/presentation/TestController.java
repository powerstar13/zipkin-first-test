package com.example.zipkintest.presentation;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Observed
@RestController
public class TestController {
    private final Logger log;

    public TestController() {
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping("/foo")
    public String success() {
        log.info("success message test");
        return "success";
    }

    @GetMapping("/bar")
    public String fail() {
        log.info("fail-message-test");
        throw new RuntimeException();
    }

}
