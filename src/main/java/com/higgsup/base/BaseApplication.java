package com.higgsup.base;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BaseApplication extends SpringBootServletInitializer {

  @Bean
  public ApplicationRunner runner() {
    return app -> {
      if (app.containsOption("fail")) {
        throw new RuntimeException("Planned!");
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(BaseApplication.class, args);
  }

}

