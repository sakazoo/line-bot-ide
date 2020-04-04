package com.example.bot.spring.echo.service;

import com.example.bot.spring.echo.model.DayOfWeekInMonth;
import com.example.bot.spring.echo.model.GarbageType;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.ZonedDateTime;

@Service
public class GarbageNotificationService {

  public GarbageType getGarbageType(ZonedDateTime dateTime) throws Exception {
    // ex) "2020-04-09T00:00:00.00Z"
    DayOfWeek dayOfWeek = dateTime.getDayOfWeek(); // ex) "THURSDAY"
    int dayOfMonth = dateTime.getDayOfMonth(); // ex) "9"
    DayOfWeekInMonth dayOfWeekInMonth = DayOfWeekInMonth.getByDay(dayOfMonth);  // ex) "2"
    GarbageType type = GarbageType.ofType(dayOfWeek, dayOfWeekInMonth);
    return type;
  }
}
