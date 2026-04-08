package com.fondoseguro.config; // Ajusta esto a tu paquete real

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class Awsconfig {

    @Bean
    public TextractClient textractClient() {
        return TextractClient.builder()
                .region(Region.US_EAST_2) 
                .build();
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_2)
                .build();
    }
}