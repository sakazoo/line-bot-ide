package com.example.bot.spring.echo.service;

import com.example.bot.spring.echo.model.GarbageType;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GarbageNotificationServiceTest {

  private GarbageNotificationService garbageNotificationService = new GarbageNotificationService();

  @Test
  void getGarbageType_burnable() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.parse("2020-04-03T00:00:00.00Z"), ZoneId.of("Asia/Tokyo"));
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime);
    assertEquals(GarbageType.BURNABLE, garbageType);
  }

  @Test
  void getGarbageType_resource() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.parse("2020-04-01T00:00:00.00Z"), ZoneId.of("Asia/Tokyo"));
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime);
    assertEquals(GarbageType.RESOURCE, garbageType);
  }

  @Test
  void getGarbageType_incombustible() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.parse("2020-04-09T00:00:00.00Z"), ZoneId.of("Asia/Tokyo"));
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime);
    assertEquals(GarbageType.INCOMBUSTIBLE, garbageType);
  }

  @Test
  void getGarbageType_1st_thu_none() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.parse("2020-04-02T00:00:00.00Z"), ZoneId.of("Asia/Tokyo"));
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime);
    assertEquals(GarbageType.NONE, garbageType);
  }

  @Test
  void getGarbageType_none() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.parse("2020-04-02T00:00:00.00Z"), ZoneId.of("Asia/Tokyo"));
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime);
    assertEquals(GarbageType.NONE, garbageType);
  }
}