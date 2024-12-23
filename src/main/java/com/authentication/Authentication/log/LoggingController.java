package com.authentication.Authentication.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

  Logger log = LoggerFactory.getLogger(LoggingController.class);

  @GetMapping("/getLogging")
  public String getLogging() {
    log.info("This is the info message");
    log.debug("This is the debug message");
    log.warn("This is the warn message");
    log.error("This is the error message");
    log.trace("This is the trace message");
    return "check the logs for various log levels";
  }
}
