package com.example.bot.spring.echo.model;

import java.time.DayOfWeek;

public enum GarbageType {
  BURNABLE(new DayOfWeek[] {DayOfWeek.TUESDAY, DayOfWeek.FRIDAY}, "明日は燃えるゴミなのでヨシ！"),
  RESOURCE(
      new DayOfWeek[] {DayOfWeek.WEDNESDAY},
      "明日は資源ゴミなのでヨシ！\n"
          + "ダンボール、ペットボトル、空き缶空き瓶を捨てるヨシ！\n"
          + "詳しくはこちらを確認↓\n"
          + "https://www.city.shinagawa.tokyo.jp/PC/kankyo/kankyo-gomi/kankyo-gomi-dashikata/hpg000005606.html"),
  INCOMBUSTIBLE(new DayOfWeek[] {DayOfWeek.THURSDAY}, "明日は燃えないゴミなのでヨシ！\n" +
          "陶器・ガラス、金属類を捨てるヨシ！\n" +
          "詳しくはこちらを確認↓\n" +
          "https://www.city.shinagawa.tokyo.jp/PC/kankyo/kankyo-gomi/kankyo-gomi-dashikata/hpg000005605.html"),
  NONE(new DayOfWeek[] {DayOfWeek.MONDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY}, "通常日");

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
