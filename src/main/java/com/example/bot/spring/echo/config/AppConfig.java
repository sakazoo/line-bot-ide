package com.example.bot.spring.echo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class AppConfig {

  private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public ThreadPoolTaskScheduler  taskScheduler(){
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(3);
    taskScheduler.setRejectedExecutionHandler((r, executor) -> {
      log.error("task scheduler error occurred.", r);
    });
    return  taskScheduler;
  }
}