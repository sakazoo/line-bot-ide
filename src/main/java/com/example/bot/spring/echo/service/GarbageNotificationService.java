package com.example.bot.spring.echo.service;

import com.example.bot.spring.echo.model.GarbageType;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Service
public class GarbageNotificationService {

  public GarbageType getGarbageType(ZonedDateTime dateTime) throws Exception {
    DayOfWeek week = dateTime.getDayOfWeek();
    switch (week) {
      case TUESDAY:
      case FRIDAY:
        return GarbageType.BURNABLE;
      case WEDNESDAY:
        return GarbageType.RESOURCE;
      case THURSDAY:
        int day = dateTime.getDayOfMonth();
        if((8 <= day && day <= 14) || (22 <= day && day <= 28)){
          // second and forth thursday
          return GarbageType.INCOMBUSTIBLE;
        }else{
          return GarbageType.NONE;
        }
      default:
        throw new Exception("failed to get garbageType");
    }
  }
}
