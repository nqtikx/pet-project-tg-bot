package com.tgbot.prodaction.controller;

import com.tgbot.prodaction.dto.CreateReminderRequest;
import com.tgbot.prodaction.dto.ReminderResponse;
import com.tgbot.prodaction.model.Reminder;
import com.tgbot.prodaction.model.TelegramUser;
import com.tgbot.prodaction.service.ReminderService;
import com.tgbot.prodaction.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ReminderTestController {
  private final TelegramUserService telegramUserService;
  private final ReminderService reminderService;

  @Autowired
  public ReminderTestController(TelegramUserService telegramUserService,
      ReminderService reminderService) {
    this.telegramUserService = telegramUserService;
    this.reminderService = reminderService;
  }

  @PostMapping("/test/reminders")
  public ReminderResponse createReminder(@RequestBody CreateReminderRequest request) {
    TelegramUser tgUser = telegramUserService.findOrCreateUser(request.getTelegramUserId(),
        request.getChatId(), request.getUsername(), request.getFirstName(),
        request.getLastName());
    Reminder remind = reminderService.createReminder(tgUser, request.getChatId(),
        request.getText(), request.getRemindAt());

    return toReminderResponse(remind);
  }

  private ReminderResponse toReminderResponse(Reminder remind) {
    ReminderResponse reminderResponse = new ReminderResponse();

    reminderResponse.setId(remind.getId());
    reminderResponse.setChatId(remind.getChatId());
    reminderResponse.setText(remind.getText());
    reminderResponse.setRemindAt(remind.getRemindAt());
    reminderResponse.setStatus(remind.getStatus());
    reminderResponse.setCreatedAt(remind.getCreatedAt());
    reminderResponse.setSentAt(remind.getSentAt());

    return reminderResponse;
  }
}
