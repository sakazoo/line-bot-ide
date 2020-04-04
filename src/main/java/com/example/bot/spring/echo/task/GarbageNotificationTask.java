package com.example.bot.spring.echo.task;

import com.example.bot.spring.echo.model.GarbageType;
import com.example.bot.spring.echo.service.GarbageNotificationService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/task")
public class GarbageNotificationTask {
  private static final Logger log = LoggerFactory.getLogger(GarbageNotificationTask.class);

  private final LineMessagingClient lineMessagingClient;

  private final GarbageNotificationService garbageNotificationService;

  public GarbageNotificationTask(
      LineMessagingClient lineMessagingClient,
      GarbageNotificationService garbageNotificationService) {
    this.lineMessagingClient = lineMessagingClient;
    this.garbageNotificationService = garbageNotificationService;
  }

  @Scheduled(cron = "0 0 23 * * MON-FRI", zone = "Asia/Tokyo")
  public void notifyGarbage() throws Exception {
    ZonedDateTime dateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Tokyo"));
    //  need to know garbageType of tomorrow's dayOfWeek
    GarbageType garbageType = garbageNotificationService.getGarbageType(dateTime.plusDays(1));
    if(GarbageType.NONE == garbageType){
      return;
    }
    log.info("exec {} garbage notify", garbageType.name().toLowerCase());
    try {
      BotApiResponse response =
          lineMessagingClient
              .broadcast(
                  new Broadcast(
                      Collections.singletonList(new TextMessage(garbageType.getMessage())), false))
              .get();
      log.info("notified garbage messages: {}", response);
    } catch (InterruptedException | ExecutionException e) {
      log.error("failed to send garbage notify", e);
    }
  }

  @GetMapping("/call/garbage")
  @ResponseStatus(HttpStatus.OK)
  public String callNotification() throws Exception {
    notifyGarbage();
    return "ok";
  }
}
