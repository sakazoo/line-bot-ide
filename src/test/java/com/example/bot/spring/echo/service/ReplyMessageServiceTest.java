package com.example.bot.spring.echo.service;

import com.example.bot.spring.echo.model.CatImage;
import com.example.bot.spring.echo.model.ReplyType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// need to enable spring retry
@SpringBootTest
class ReplyMessageServiceTest {
  private static final String CAT_API_URL = "https://aws.random.cat/meow";

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

  @MockBean
  private RestTemplate restTemplate;

  @Autowired
  private ReplyMessageService replyMessageService;

  @Test
  void getPraiseReplyMessage_success() {
    String result = replyMessageService.getPraiseReplyMessage(ReplyType.PRAISE);
    assertTrue(PRAISE_MESSAGES.contains(result));
  }

  @Test
  void getCatImage_success() {
    String url = "http://localhost:8080/image";
    CatImage expectedResult = new CatImage();
    expectedResult.setUrl(url);
    Mockito.when(restTemplate.getForObject(CAT_API_URL, CatImage.class))
            .thenReturn(expectedResult);

    String result = replyMessageService.getCatImage();
    assertEquals(expectedResult.getUrl(), result);
  }

  @Test
  void getCatImage_503_error() {
    Mockito.when(restTemplate.getForObject(CAT_API_URL, CatImage.class))
            .thenThrow(HttpServerErrorException.class);

    assertThrows(
            HttpServerErrorException.class,
            () -> replyMessageService.getCatImage()
    );
    verify(restTemplate, times(3)).getForObject(CAT_API_URL, CatImage.class);
  }
}