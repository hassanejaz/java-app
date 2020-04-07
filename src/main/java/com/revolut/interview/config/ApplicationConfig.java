package com.revolut.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationConfig {

    @Bean("utcClock")
    public Clock utcClock() {
        return Clock.systemUTC();
    }
}
