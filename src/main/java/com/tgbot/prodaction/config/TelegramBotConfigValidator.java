package com.tgbot.prodaction.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotConfigValidator implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(TelegramBotConfigValidator.class);
  private final TelegramBotProperties tgBotProperties;

  @Autowired
  public TelegramBotConfigValidator(TelegramBotProperties tgBotProperties) {
    this.tgBotProperties = tgBotProperties;
  }

  //TODO - надо в дальнейшем переписать чтобы приложение не стартовало при неверных данных бота (удалить try-catch)
  @Override
  public void run(ApplicationArguments args) {
    try {
      validate();
    } catch (IllegalStateException e) {
      log.error("Telegram Bot configuration ERROR: {}", e.getMessage());
    }
  }

  public void validate() {
    if (tgBotProperties.getUsername() == null || tgBotProperties.getUsername().isBlank()) {
      throw new IllegalStateException("Telegram bot user must not be empty");
    }
    if (tgBotProperties.getToken() == null || tgBotProperties.getToken().isBlank()) {
      throw new IllegalStateException("Telegram bot token must not be empty");
    }
    log.info("BOT IS READY to work!");
  }
}
