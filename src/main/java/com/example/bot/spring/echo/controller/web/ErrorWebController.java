package com.example.bot.spring.echo.controller.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/web")
public class ErrorWebController {

  @RequestMapping("/error")
  public ModelAndView error(ModelAndView mav) {
    mav.setStatus(HttpStatus.NOT_FOUND);
    mav.setViewName("error");
    return mav;
  }

}
