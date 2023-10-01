package com.bootlabs.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;

@RequiredArgsConstructor
@Configuration
public class AwsConfig {

    private final AwsProperties awsProperties;

    @Bean
    public SesV2Client sesV2Client() {
        return SesV2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(awsProperties.getAccessKey(), awsProperties.getSecretKey())))
                .region(Region.of(awsProperties.getRegion()))
                .build();
    }
}
