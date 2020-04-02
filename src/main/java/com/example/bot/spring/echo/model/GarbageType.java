package com.example.bot.spring.echo.model;

import java.time.DayOfWeek;

public enum GarbageType {

  BURNABLE(new DayOfWeek[]{DayOfWeek.TUESDAY, DayOfWeek.FRIDAY}, "燃えるゴミ"),
  RESOURCE(new DayOfWeek[]{DayOfWeek.WEDNESDAY}, "資源ゴミ"),
  INCOMBUSTIBLE(new DayOfWeek[]{DayOfWeek.THURSDAY}, "燃えないゴミ"),
  NONE(new DayOfWeek[]{DayOfWeek.MONDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY}, "通常日");

  private DayOfWeek[] weeks;

  private String message;

  private GarbageType(DayOfWeek[] weeks, String message) {
    this.weeks = weeks;
    this.message = message;
  }

  public DayOfWeek[] getWeeks() {
    return weeks;
  }

  public String getMessage() {
    return message;
  }
}
