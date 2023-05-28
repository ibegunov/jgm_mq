package com.credit.creditapplication.aws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SqsProducer {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Value("${credit.queue.name}")
    private String creditQueue;

    @Autowired
    public SqsProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    public <T> void send(T message) {
        if (message == null) {
            log.warn("Can't produce null message");
            return;
        }

        log.debug(" Messgae {} " + message);
        log.debug(" Queue name {} " + creditQueue);
        queueMessagingTemplate.convertAndSend(creditQueue, message);
    }

}
