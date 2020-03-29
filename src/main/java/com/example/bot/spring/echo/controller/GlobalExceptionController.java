package com.example.bot.spring.echo.controller;

import com.example.bot.spring.echo.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalExceptionController implements ErrorController {

  Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

  @Value("${server.error.path:/globalError}")
  private String errorPath;

  @Override
  public String getErrorPath() {
    return errorPath;
  }

  @RequestMapping("${server.error.path:/globalError}")
  public String error(HttpServletRequest httpServletRequest) {
    String requestURI = (String) httpServletRequest.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);
    Site site = Site.of(requestURI);

    log.warn("globalError {} called. requestURI = {}", site.getDescription(), requestURI);

    return "forward:" + site.getBasePath() + "/error";
  }
}
