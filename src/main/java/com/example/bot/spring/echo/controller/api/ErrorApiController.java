package com.example.bot.spring.echo.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorApiController {
  private static final Logger log = LoggerFactory.getLogger(ErrorApiController.class);

  @RequestMapping({"/api/error", "/other/error"})
  public ResponseEntity<String> error() {
    log.warn("api has not found");
    return new ResponseEntity<>("url has not found", HttpStatus.NOT_FOUND);
  }

}
