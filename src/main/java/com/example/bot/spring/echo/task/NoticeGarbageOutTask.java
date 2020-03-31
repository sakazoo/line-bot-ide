package com.example.bot.spring.echo.task;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.Broadcast;
import com.linecorp.bot.model.PushMessage;
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

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/task")
public class NoticeGarbageOutTask {
  private static final Logger log = LoggerFactory.getLogger(NoticeGarbageOutTask.class);

  private final LineMessagingClient lineMessagingClient;

  public NoticeGarbageOutTask(LineMessagingClient lineMessagingClient) {
    this.lineMessagingClient = lineMessagingClient;
  }

  // wednesday at 00:00
  @Scheduled(cron = "0 0 0 * * WED", zone = "Asia/Tokyo")
  public void notify3() {
    log.info("exec garbage notify");
    BotApiResponse response = null;
    try {
      response =
          lineMessagingClient
              .broadcast(
                  new Broadcast(
                      Collections.singletonList(new TextMessage("明日は水曜日です。\n資源ごみをお忘れなく！")), false))
              .get();
      log.info("sent messages: {}", response);
    } catch (InterruptedException | ExecutionException e) {
      log.error("failed to send message: {}", "明日は資源ごみの日です。", e);
    }
  }

  @Scheduled(cron = "0 * * * * MON-FRI", zone = "Asia/Tokyo")
  public void test() {

    TextMessage textMessage = new TextMessage("hello");
    PushMessage pushMessage = new PushMessage("Uc0f3fdd7c6a10db727dbd3c21b68c181", textMessage);

    final BotApiResponse botApiResponse;
    try {
      botApiResponse = lineMessagingClient.pushMessage(pushMessage).get();
      log.info(String.valueOf(botApiResponse));
    } catch (InterruptedException | ExecutionException e) {
      log.error("failed to send message: {}", "hello", e);
    }
  }

  @GetMapping("/call/wed")
  @ResponseStatus(HttpStatus.OK)
  public String callNotification() {
    notify3();
    return "ok";
  }
}
