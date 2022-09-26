package com.rubok.app.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

  @PostMapping("/login")
  public String restControllerTest(@RequestBody Object param) {

    log.info("Rest Controller Post Test {}", String.valueOf(param));
    return param.toString();
  }

  @PostMapping("/register")
  public String axiosControllerTest(@RequestBody Object param) {
    log.info("axios test {}", String.valueOf(param));
    return param.toString();
  }
}
