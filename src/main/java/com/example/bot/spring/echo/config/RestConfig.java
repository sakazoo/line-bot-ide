package com.example.bot.spring.echo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(5))
            .build();
  }

//  @Bean
//  public RestTemplate restTemplate() {
//    return new RestTemplate(clientHttpRequestFactory());
//  }

//  @Bean
//  public ClientHttpRequestFactory clientHttpRequestFactory() {
//    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
//    connectionManager.setMaxTotal(10);
//    connectionManager.setDefaultMaxPerRoute(10);
//
//    RequestConfig config = RequestConfig.custom()
//            .setSocketTimeout(3000)
//            .setConnectTimeout(5000)
//            .setConnectionRequestTimeout(5000)
//            .build();
//
//    CloseableHttpClient client = HttpClientBuilder.create()
//            .setDefaultRequestConfig(config)
//            .setConnectionManager(connectionManager)
//            .build();
//    return new HttpComponentsClientHttpRequestFactory(client);
//  }
}
