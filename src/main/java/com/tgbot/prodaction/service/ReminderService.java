package com.tgbot.prodaction.service;

import com.tgbot.prodaction.bot.TelegramReminderBot;
import com.tgbot.prodaction.model.Reminder;
import com.tgbot.prodaction.model.ReminderStatus;
import com.tgbot.prodaction.model.TelegramUser;
import com.tgbot.prodaction.repository.ReminderRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

  private static final Logger log = LoggerFactory.getLogger(TelegramReminderBot.class);
  private final ReminderRepository reminderRepository;
  private final TelegramReminderBot tgReminderBot;

  @Autowired
  public ReminderService(ReminderRepository reminderRepository, TelegramReminderBot tgReminderBot) {
    this.reminderRepository = reminderRepository;
    this.tgReminderBot = tgReminderBot;
  }

  public Reminder createReminder(TelegramUser telegramUser, Long chatId, String text,
      LocalDateTime remindAt) {
    LocalDateTime now = LocalDateTime.now();

    try {
      if (telegramUser == null) {
        throw new IllegalArgumentException("Telegram user must not be null");
      }

      if (chatId == null) {
        throw new IllegalArgumentException("Chat id must not be null");
      }

      if (text == null || text.isBlank()) {
        throw new IllegalArgumentException("Reminder text must not be empty");
      }

      if (remindAt == null) {
        throw new IllegalArgumentException("Reminder date must not be null");
      }

      if (!remindAt.isAfter(now)) {
        throw new IllegalArgumentException("Reminder date must be in the future");
      }
    } catch (IllegalArgumentException e) {
      log.warn("Validation error: {}", e.getMessage());
      tgReminderBot.sendMessage(chatId, "Error: " + e.getMessage());
    }

    Reminder reminder = new Reminder();
    reminder.setId(UUID.randomUUID());
    reminder.setTelegramUser(telegramUser);
    reminder.setChatId(chatId);
    reminder.setText(text);
    reminder.setRemindAt(remindAt);
    reminder.setStatus(ReminderStatus.ACTIVE);
    reminder.setCreatedAt(now);
    reminder.setUpdatedAt(now);

    return reminderRepository.save(reminder);
  }
}
