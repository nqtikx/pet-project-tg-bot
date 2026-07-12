package com.tgbot.prodaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProdactionApplication {

  /*
  TEST YOLO
   */
  public static void main(String[] args) {
    SpringApplication.run(ProdactionApplication.class, args);
  }

}
