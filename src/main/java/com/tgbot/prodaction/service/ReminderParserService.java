package com.tgbot.prodaction.service;

import com.tgbot.prodaction.dto.ParsedReminderCommand;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReminderParserService {
  private static final Logger log = LoggerFactory.getLogger(ReminderParserService.class);

  private static final String REMIND_COMMAND = "/remind";
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

  public ParsedReminderCommand parseReminderCommand(String messageText) {
    log.info("Starting parse Reminder Command...");
    if (messageText == null || messageText.isBlank()) {
      throw new IllegalArgumentException("Reminder text must not be empty");
    }
    String[] textArr = messageText.trim().split("\\s+");
    if (textArr.length < 4) {
      throw new IllegalArgumentException("Invalid message format");
    }
    if (!textArr[0].equals(REMIND_COMMAND)) {
      throw new IllegalArgumentException("Invalid reminder command");
    }

    String dateTimeText = textArr[1] + " " + textArr[2];
    LocalDateTime remindAt = parseRemindAt(dateTimeText);

    String text = String.join(" ", Arrays.copyOfRange(textArr, 3, textArr.length));

    log.info("parse Reminder Command completed successfully");
    return new ParsedReminderCommand(remindAt, text);
  }

  private LocalDateTime parseRemindAt(String timeStr) {
    log.info("Starting parse date format...");
    try {
      return LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid date format");
    }
  }
}
