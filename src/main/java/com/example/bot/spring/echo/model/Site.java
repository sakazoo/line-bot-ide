package com.example.bot.spring.echo.model;

import java.util.Arrays;

public enum Site {
  API("/api", "for api"),
  WEB("/web", "for web"),
  OTHER("/other", "unknown url");

  private String basePath;
  private String description;

  private Site(String basePath, String description) {
    this.basePath = basePath;
    this.description = description;
  }

  public String getBasePath() {
    return basePath;
  }

  public String getDescription() {
    return description;
  }

  public static Site of(String path) {
    if (path == null) {
      return OTHER;
    }

    return Arrays.stream(values())
        .filter(s -> path.startsWith(s.basePath))
        .filter(s -> s != OTHER)
        .findFirst()
        .orElse(Site.OTHER);
  }
}
