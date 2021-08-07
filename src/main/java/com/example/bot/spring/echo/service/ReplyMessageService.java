package com.example.bot.spring.echo.service;

import com.example.bot.spring.echo.exception.ImageNotFoundException;
import com.example.bot.spring.echo.model.CatImage;
import com.example.bot.spring.echo.model.ReplyType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ReplyMessageService {

  private RestTemplate restTemplate;

  public ReplyMessageService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  private static final Map<ReplyType, Set<String>> REPLY_MESSAGE = new HashMap<>();
  private static final Set<String> PRAISE_MESSAGES = new HashSet<>(Arrays.asList(
          "テールチョジャンがあるから早く帰っておいで",
          "今日もかわいいね",
          "お尻マッサージしましょうね",
          "ランバンの靴を買いに行こう",
          "パンツはいててエライ！",
          "仕事してるだけでエライ！",
          "スタバのコーヒー飲みに行こう",
          "お風呂入って早く寝よう",
          "君は才能のかたまりだね",
          "何コレ？すっごーい！",
          "君は女神(アプロディーテー)だね",
          "今日も存在していてエライね",
          "すごい！こんなにすごいと銅像立っちゃうよ！！！",
          "君のまわりには笑顔が集まってくるね",
          "石原さとみに似てるって言われない？",
          "肌が綺麗だね。まるで赤ちゃんみたい。",
          "そのままでも十分かわいいよ"
  ));

  static {
    REPLY_MESSAGE.put(ReplyType.PRAISE, PRAISE_MESSAGES);
  }

  public String getPraiseReplyMessage(ReplyType replyType) {
    Set<String> messageSet = REPLY_MESSAGE.get(replyType);
    return getRandomMessage(messageSet);
  }

  @Retryable(value = HttpServerErrorException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
  public String getCatImage() {
    CatImage catImage = restTemplate.getForObject("https://aws.random.cat/meow", CatImage.class);
    if(catImage == null){
      throw new ImageNotFoundException("CatImage API has no image url.");
    }
    return catImage.getFile();
  }

  private String getRandomMessage(Set<String> set) {
    return set.stream().skip(new Random().nextInt(set.size()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Reply Messages is empty."));
  }
}
