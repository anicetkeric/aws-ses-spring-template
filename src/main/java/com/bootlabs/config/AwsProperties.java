package com.bootlabs.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to aws client.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */
@Data
@Component
@ConfigurationProperties(prefix = "aws", ignoreUnknownFields = false)
public class AwsProperties {

    /**
     *  Aws access key ID
     */
    private String accessKey;


    /**
     *  Aws secret access key
     */
    private String secretKey;

    /**
     *  Aws region
     */
    private String region;
}
