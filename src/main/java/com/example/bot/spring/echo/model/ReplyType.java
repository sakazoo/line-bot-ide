package com.example.bot.spring.echo.model;

public enum ReplyType {
  PRAISE("褒めて");

  private String text;

  ReplyType(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}
