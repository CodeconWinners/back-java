package dev.desabafa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${calendar.api.base-url}")
    private String baseCalendarUrl;

    @Bean
    public RestClient calendarRestClient(RestClient.Builder builder) {
        return builder
            .baseUrl(baseCalendarUrl)
            .build();
    }
}

