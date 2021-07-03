package com.example.bot.spring.echo.handler;

import com.example.bot.spring.echo.model.ReplyType;
import com.example.bot.spring.echo.service.ReplyMessageService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@LineMessageHandler
public class LineEventHandler {
  private static final Logger log = LoggerFactory.getLogger(LineMessageHandler.class);

  private final ReplyMessageService replyMessageService;

  public LineEventHandler(ReplyMessageService replyMessageService) {
    this.replyMessageService = replyMessageService;
  }

  @EventMapping
  public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
    log.info("event: {}, userId: {}", event, event.getSource().getUserId());
    String messageText = event.getMessage().getText();
    if (ReplyType.PRAISE.getText().equals(messageText)) {
      messageText = replyMessageService.getReplyMessage(ReplyType.PRAISE);
    }
    return new TextMessage(messageText);
  }

  @EventMapping
  public void handleDefaultMessageEvent(Event event) {
    log.info("event: {}, userId: {}", event, event.getSource().getUserId());
  }
}
