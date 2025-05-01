package com.schoolManagementDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadConfig {

    @Primary
    @Bean("dbExecutor")
    public Executor dbExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);      // number of core threads
        executor.setMaxPoolSize(8);       // max threads in peak
        executor.setQueueCapacity(100);   // queue for extra tasks
        executor.setThreadNamePrefix("DBExecutor-");
        executor.initialize();
        return executor;
    }
}
