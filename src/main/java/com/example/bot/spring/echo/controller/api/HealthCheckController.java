package com.example.bot.spring.echo.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
  @GetMapping("/health-check")
  public ResponseEntity<String> status(){
    return ResponseEntity.ok("running");
  }
}
