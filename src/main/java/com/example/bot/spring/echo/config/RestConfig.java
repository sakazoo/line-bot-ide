package com.example.bot.spring.echo.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(clientHttpRequestFactory());
  }


  @Bean
  public ClientHttpRequestFactory clientHttpRequestFactory() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(10);
    connectionManager.setDefaultMaxPerRoute(10);

    RequestConfig config = RequestConfig.custom()
            .setSocketTimeout(3000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();

    CloseableHttpClient client = HttpClientBuilder.create()
            .setDefaultRequestConfig(config)
            .setConnectionManager(connectionManager)
            .build();
    return new HttpComponentsClientHttpRequestFactory(client);
  }
}
