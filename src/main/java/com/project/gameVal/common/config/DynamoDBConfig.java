package com.project.gameVal.common.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.project.gameVal.web.probability.repository.ProbabilityTableRepository;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = ProbabilityTableRepository.class)
public class DynamoDBConfig {
    @Value("${cloud.aws.dynamodb.endpoint}")
    private String endPoint;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.dynamodb.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.dynamodb.credentials.secretKey}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration (endPoint, region) )
                .withCredentials(new AWSStaticCredentialsProvider( new BasicAWSCredentials( accessKey, secretKey)))
                .build();
    }
}
