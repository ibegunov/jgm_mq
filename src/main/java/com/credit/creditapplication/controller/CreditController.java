package com.credit.creditapplication.controller;

import com.credit.creditapplication.models.dto.CreditRequestDto;
import com.credit.creditapplication.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credits")
public class CreditController {

    private final CreditService creditService;

    @PostMapping
    public ResponseEntity<CreditRequestDto> placeOrder(@RequestBody CreditRequestDto creditDto) {
        return new ResponseEntity<>(creditService.submitCreditRequest(creditDto), HttpStatus.OK);
    }

}
