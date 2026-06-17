package com.tgbot.prodaction.bot;

import com.tgbot.prodaction.config.TelegramBotProperties;
import com.tgbot.prodaction.service.TelegramUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;

@Component
public class TelegramReminderBot implements SpringLongPollingBot,
    LongPollingSingleThreadUpdateConsumer {
  private static final Logger log = LoggerFactory.getLogger(TelegramReminderBot.class);
  private final static String START_COMMAND = "/start";
  private static final String START_MESSAGE = """
      Привет! Я бот для напоминаний.

      Чтобы создать напоминание, напиши:
      /remind 15.06.2026 19:30 текст напоминания
      """;
  private final static String UNKNOWN_START_COMMAND = "Пока что я понимаю только текстовые сообщения";


  private final TelegramBotProperties telegramBotProperties;
  private final TelegramUserService telegramUserService;
  private final TelegramClient tgClient;

  public TelegramReminderBot(TelegramBotProperties telegramBotProperties,
      TelegramUserService telegramUserService){
    this.telegramBotProperties = telegramBotProperties;
    this.telegramUserService = telegramUserService;
    this.tgClient = new OkHttpTelegramClient(telegramBotProperties.getToken());
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
      return;
    }
    Long chatId = update.getMessage().getChatId();
    String messageText = update.getMessage().getText();

    if (START_COMMAND.equals(messageText)) {
      handleStartCommand(update);
      log.info("Message has been send to the user with username: {}", update.getMessage().getFrom().getUserName());
      return;
    }
    sendMessage(chatId, UNKNOWN_START_COMMAND);
  }

  public void handleStartCommand(Update update) {
    Long chatId = update.getMessage().getChatId();

    telegramUserService.findOrCreateUser(update.getMessage().getFrom().getId(), chatId, update.getMessage().getFrom().getUserName(),
        update.getMessage().getFrom().getFirstName(), update.getMessage().getFrom().getLastName());

    sendMessage(chatId, START_MESSAGE);
  }

  private void sendMessage(Long chatId, String text) {
    SendMessage message = SendMessage
        .builder()
        .chatId(chatId)
        .text(text)
        .build();

    try {
      tgClient.execute(message);
    } catch (TelegramApiException e) {
      log.error("Failed to send message to chat id: {}", chatId, e);
    }
  }

  private String getMessageType(Update update){
    if (update.getMessage().hasPhoto()) {
      return "фото";
    }
    if (update.getMessage().hasVideo()) {
      return "видео";
    }
    if (update.getMessage().hasSticker()) {
      return "стикер";
    }
    if (update.getMessage().hasAudio()) {
      return "аудио";
    }
    if (update.getMessage().hasVoice()) {
      return "голосовое";
    }
    if (update.getMessage().hasDocument()) {
      return "документ";
    }
    if (update.getMessage().hasLocation()) {
      return "локация";
    }
    if (update.getMessage().hasContact()) {
      return "контакт";
    }
    return "неизвестный тип";
  }
}
