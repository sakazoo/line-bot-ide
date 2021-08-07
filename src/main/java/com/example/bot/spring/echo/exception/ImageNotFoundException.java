package com.example.bot.spring.echo.exception;

public class ImageNotFoundException extends RuntimeException {
  public ImageNotFoundException() {
  }

  public ImageNotFoundException(String message) {
    super(message);
  }
}
