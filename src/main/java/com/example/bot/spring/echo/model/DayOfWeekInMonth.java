package com.example.bot.spring.echo.model;

import java.util.Arrays;

public enum DayOfWeekInMonth {
  FIRST(1, 7),
  SECOND(8, 14),
  THIRD(15, 21),
  FOURTH(22, 28),
  FIFTH(29, 31),
  ALL(1,31);

  private int startDay;
  private int endDay;

  private DayOfWeekInMonth(int startDay, int endDay) {
    this.startDay = startDay;
    this.endDay = endDay;
  }

  public static DayOfWeekInMonth getByDay(int dayOfMonth) throws Exception {
    return Arrays.stream(values())
            .filter(d -> between(d.startDay, dayOfMonth, d.endDay))
            .findFirst()
            .orElseThrow(Exception::new);
  }

  private static boolean between(int startDay, int dayOfMonth, int endDay){
    return startDay <= dayOfMonth && dayOfMonth <= endDay;
  }

  public boolean isSecondOrForth(){
    return SECOND == this || FOURTH == this;
  }
}
