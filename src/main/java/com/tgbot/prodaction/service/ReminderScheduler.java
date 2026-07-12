package com.tgbot.prodaction.service;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReminderScheduler {
  private final ReminderDispatchService reminderDispatchService;
  private static final Logger log = LoggerFactory.getLogger(ReminderScheduler.class);

  public ReminderScheduler(ReminderDispatchService reminderDispatchService) {
    this.reminderDispatchService = reminderDispatchService;
  }

  @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
  public void dispatchDueReminders() {
    log.info("Scheduling start...");
    reminderDispatchService.processDueReminders();
  }
}
