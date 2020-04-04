package com.example.bot.spring.echo.model;

import java.time.DayOfWeek;
import java.util.Arrays;

public enum GarbageType {
  BURNABLE(
      new DayOfWeek[] {DayOfWeek.TUESDAY, DayOfWeek.FRIDAY},
      "明日は燃えるゴミなのでヨシ！",
      new DayOfWeekInMonth[] {DayOfWeekInMonth.ALL}),
  RESOURCE(
      new DayOfWeek[] {DayOfWeek.WEDNESDAY},
      "明日は資源ゴミなのでヨシ！\n"
          + "ダンボール、ペットボトル、空き缶空き瓶を捨てるヨシ！\n"
          + "詳しくはこちらを確認↓\n"
          + "https://www.city.shinagawa.tokyo.jp/PC/kankyo/kankyo-gomi/kankyo-gomi-dashikata/hpg000005606.html",
      new DayOfWeekInMonth[] {DayOfWeekInMonth.ALL}),
  INCOMBUSTIBLE(
      new DayOfWeek[] {DayOfWeek.THURSDAY},
      "明日は燃えないゴミなのでヨシ！\n"
          + "陶器・ガラス、金属類を捨てるヨシ！\n"
          + "詳しくはこちらを確認↓\n"
          + "https://www.city.shinagawa.tokyo.jp/PC/kankyo/kankyo-gomi/kankyo-gomi-dashikata/hpg000005605.html",
      new DayOfWeekInMonth[] {DayOfWeekInMonth.SECOND, DayOfWeekInMonth.FOURTH}),
  NONE(
      new DayOfWeek[] {DayOfWeek.MONDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY},
      "通常日",
      new DayOfWeekInMonth[] {DayOfWeekInMonth.ALL});

  private DayOfWeek[] weeks;

  private String message;

  private DayOfWeekInMonth[] dayOfWeekInMonths;

  private GarbageType(DayOfWeek[] weeks, String message, DayOfWeekInMonth[] dayOfWeekInMonths) {
    this.weeks = weeks;
    this.message = message;
    this.dayOfWeekInMonths = dayOfWeekInMonths;
  }

  public DayOfWeek[] getWeeks() {
    return weeks;
  }

  public String getMessage() {
    return message;
  }

  public DayOfWeekInMonth[] getDayOfWeekInMonths() {
    return dayOfWeekInMonths;
  }

  public static GarbageType ofType(DayOfWeek dayOfWeek, DayOfWeekInMonth dayOfWeekInMonth) {
    return Arrays.stream(values())
        .filter(g -> Arrays.asList(g.getWeeks()).contains(dayOfWeek))
        .filter(
            g ->
                Arrays.asList(g.getDayOfWeekInMonths()).contains(dayOfWeekInMonth)
                    || Arrays.asList(g.getDayOfWeekInMonths()).contains(DayOfWeekInMonth.ALL))
        .findFirst()
        .orElse(NONE);
  }
}
