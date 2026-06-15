package com.tgbot.prodaction.bot;

import com.tgbot.prodaction.config.TelegramBotProperties;
import com.tgbot.prodaction.service.TelegramUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramReminderBot implements SpringLongPollingBot,
    LongPollingSingleThreadUpdateConsumer {
  private static final Logger log = LoggerFactory.getLogger(TelegramReminderBot.class);
  private final TelegramBotProperties telegramBotProperties;
  private final TelegramUserService telegramUserService;

  public TelegramReminderBot(TelegramBotProperties telegramBotProperties,
      TelegramUserService telegramUserService){
    this.telegramBotProperties = telegramBotProperties;
    this.telegramUserService = telegramUserService;
  }

  @Override
  public String getBotToken() {
    return telegramBotProperties.getToken();
  }

  @Override
  public LongPollingUpdateConsumer getUpdatesConsumer() {
    return this;
  }

  @Override
  public void consume(Update update) {
    if (!update.hasMessage()) {
      log.debug("Update without message!");
      return;
    }
    if (!update.getMessage().hasText()) {
      log.debug("Message without text from {} with type: {}", update.getMessage().getFrom().getFirstName(), getMessageType(update));
    }
    //sendMessage
  }

  private void SendMessage(Long chatId, String text) {

  }

  private String getMessageType(Update update){
    if (update.getMessage().hasPhoto()) return "фото";
    if (update.getMessage().hasVideo()) return "видео";
    if (update.getMessage().hasSticker()) return "стикер";
    if (update.getMessage().hasAudio()) return "аудио";
    if (update.getMessage().hasVoice()) return "голосовое";
    if (update.getMessage().hasDocument()) return "документ";
    if (update.getMessage().hasLocation()) return "локация";
    if (update.getMessage().hasContact()) return "контакт";
    return "неизвестный тип";
  }
}
