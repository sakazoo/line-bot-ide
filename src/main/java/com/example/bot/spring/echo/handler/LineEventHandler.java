package com.example.bot.spring.echo.handler;

import com.example.bot.spring.echo.model.ReplyType;
import com.example.bot.spring.echo.service.ReplyMessageService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * refere {@link com.linecorp.bot.spring.boot.support.LineMessageHandlerSupport}
 */
@LineMessageHandler
public class LineEventHandler {
  private static final Logger log = LoggerFactory.getLogger(LineMessageHandler.class);

  private static final String ERROR_REPLY_MESSAGE = "Botは混乱しているみたいです。。";
  // 現場猫 with ちゅーる 画像
  private static final String ERROR_IMAGE_URL = "https://livedoor.blogimg.jp/atelierelielilie-airsoku/imgs/b/0/b0349e1b.jpg";

  private final ReplyMessageService replyMessageService;

  public LineEventHandler(ReplyMessageService replyMessageService) {
    this.replyMessageService = replyMessageService;
  }

  @EventMapping
  public List<Message> handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    log.info("event: {}, userId: {}", event, event.getSource().getUserId());
    String messageText = event.getMessage().getText();
    try {
      return replayMessages(messageText);
    } catch (Exception e) {
      log.error("something error occurred.", e);
      List<Message> errorReplayMessageList = new ArrayList<>();
      Message echoMessage = new TextMessage("Botは混乱しているみたいです。。");
      URI errorImageUrl = URI.create(ERROR_IMAGE_URL);
      Message imageMessage = new ImageMessage(errorImageUrl, errorImageUrl);
      errorReplayMessageList.add(echoMessage);
      errorReplayMessageList.add(imageMessage);
      return errorReplayMessageList;
    }
  }

  private List<Message> replayMessages(String messageText) {
    List<Message> replayMessageList = new ArrayList<>();
    if (ReplyType.PRAISE.getText().equals(messageText)) {
      String praiseMessage = replyMessageService.getPraiseReplyMessage(ReplyType.PRAISE);
      Message message = new TextMessage(praiseMessage);
      replayMessageList.add(message);
    } else if (ReplyType.CAT_IMAGE.getText().equals(messageText)) {
      URI url = URI.create(replyMessageService.getCatImage());
      Message imageMessage = new ImageMessage(url, url);
      replayMessageList.add(imageMessage);
    } else {
      Message echoMessage = new TextMessage(messageText);
      replayMessageList.add(echoMessage);
    }
    return replayMessageList;
  }

  @EventMapping
  public void handleDefaultMessageEvent(Event event) {
    log.info("event: {}, userId: {}", event, event.getSource().getUserId());
  }
}
