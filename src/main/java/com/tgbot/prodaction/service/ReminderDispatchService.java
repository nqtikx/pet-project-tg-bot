package com.tgbot.prodaction.service;

import com.tgbot.prodaction.bot.TelegramReminderBot;
import com.tgbot.prodaction.model.Reminder;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReminderDispatchService {
  private final ReminderService reminderService;
  private final TelegramReminderBot tgReminderBot;
  private static final Logger log = LoggerFactory.getLogger(ReminderDispatchService.class);

  public ReminderDispatchService(ReminderService reminderService, TelegramReminderBot tgReminderBot) {
    this.reminderService = reminderService;
    this.tgReminderBot = tgReminderBot;
  }

  public void processDueReminders() {
    LocalDateTime localDateNow = LocalDateTime.now();
    List<Reminder> reminders = reminderService.findDueReminders(localDateNow);

    for (Reminder el : reminders) {
      if (reminderService.isStale(el, localDateNow)) {
        reminderService.markAsFailed(el);
        log.warn("Stale reminder {}, remind_at={}, marked as FAILED", el.getId(), el.getRemindAt());
        continue;
      }

      reminderService.markAsProcessing(el);
      log.info("Reminder {} mark as PROCESSING", el.getId());
      if (tgReminderBot.sendMessage(el.getChatId(), "Напоминание: " + el.getText())) {
        log.info("Reminder has been sent  with id: {}", el.getId());
        reminderService.markAsSent(el);
        log.info("Reminder {} mark as SENT", el.getId());
      } else {
        reminderService.markAsFailed(el);
        log.warn("Reminder {} mark as FAILED", el.getId());
      }
    }
  }
}
