package com.credit.creditapplication.repositories;

import com.credit.creditapplication.models.CreditEntity;
import java.util.Optional;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CreditRepository extends CrudRepository<CreditEntity, String> {
    Optional<CreditEntity> findByUserId(String userId);
}
