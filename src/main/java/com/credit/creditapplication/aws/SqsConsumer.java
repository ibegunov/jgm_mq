package com.credit.creditapplication.aws;

import com.credit.creditapplication.models.dto.CreditRequestDto;
import com.credit.creditapplication.service.CreditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SqsConsumer {

    private final ObjectMapper objectMapper;
    private final CreditService creditService;

    @SqsListener(value = "${credit.queue.name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        try {
            log.debug("Received new SQS message: {}", message);
            CreditRequestDto creditDto = objectMapper.readValue(message, CreditRequestDto.class);
            creditService.processCreditRequest(creditDto);
        } catch (Exception e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }
    }

}
