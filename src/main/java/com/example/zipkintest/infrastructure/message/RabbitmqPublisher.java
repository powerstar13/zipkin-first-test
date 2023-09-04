package com.example.zipkintest.infrastructure.message;

import com.example.zipkintest.application.MessagePublisher;
import com.example.zipkintest.presentation.UserResource;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Observed
@Component
class RabbitmqPublisher implements MessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final Logger log;

    public RabbitmqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public void createdUser(UserResource user) {
        log.info("[createdUser] {}", user);
        rabbitTemplate.convertAndSend(
                "zipkin.user",
                user,
                message -> {
                    message.getMessageProperties().setHeader("Command", "DELETE");
                    return message;
                }
        );
    }
}
