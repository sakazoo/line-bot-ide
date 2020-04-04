package com.example.bot.spring.echo;

import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@LineMessageHandler
@EnableScheduling
public class EchoApplication {
  public static void main(String[] args) {
    SpringApplication.run(EchoApplication.class, args);
  }
}
