package com.tgbot.prodaction.service;

import com.tgbot.prodaction.model.Reminder;
import com.tgbot.prodaction.model.ReminderStatus;
import com.tgbot.prodaction.model.TelegramUser;
import com.tgbot.prodaction.repository.ReminderRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {
  private final ReminderRepository reminderRepository;

  @Autowired
  public ReminderService(ReminderRepository reminderRepository) {
    this.reminderRepository = reminderRepository;
  }

  public Reminder createReminder(TelegramUser telegramUser, Long chatId, String text, LocalDateTime remindAt){
    LocalDateTime now = LocalDateTime.now();

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
