package com.credit.creditapplication.config;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories
        (basePackages = "com.credit.creditapplication.repositories")
public class DynamoDbConfiguration {

    @Value("${amazon.aws.region:eu-west-1}")
    private String awsRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider credentialsProvider) {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(awsRegion)
                .build();
    }

}


