package com.credit.creditapplication.service;

import com.credit.creditapplication.aws.SqsProducer;
import com.credit.creditapplication.models.CreditEntity;
import com.credit.creditapplication.models.dto.CreditRequestDto;
import com.credit.creditapplication.repositories.CreditRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreditService {
    private final SqsProducer producer;
    private final CreditRepository creditRepository;

    public void processCreditRequest(CreditRequestDto creditDto) {
        log.debug("Process credit request from queue: {} ", creditDto);

        if (creditDto == null || ObjectUtils.isEmpty(creditDto.getUserId())) {
            log.debug("Credit for null user is not allowed");
            return;
        }

        Optional<CreditEntity> creditEntity = creditRepository.findByUserId(creditDto.getUserId());

        if (creditEntity.isPresent()) {
            log.debug("Credit already exists for this user");
        } else {
            CreditEntity newCredit = new CreditEntity();
            newCredit.setUserId(creditDto.getUserId());
            newCredit.setPeriodInMonths(creditDto.getPeriodInMonths());
            newCredit.setAmount(creditDto.getAmount());
            creditRepository.save(newCredit);

            log.debug("Credit approved for {}", creditDto.getUserId());
        }
    }

    public CreditRequestDto submitCreditRequest(CreditRequestDto creditDto) {
        log.debug("Submit credit request");
        producer.send(creditDto);
        return creditDto;
    }
}
