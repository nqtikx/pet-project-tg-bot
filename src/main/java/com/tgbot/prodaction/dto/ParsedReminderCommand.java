package com.tgbot.prodaction.dto;

import java.time.LocalDateTime;

public class ParsedReminderCommand {
  private LocalDateTime remindAt;
  private String text;

  public ParsedReminderCommand() {
  }

  public ParsedReminderCommand(LocalDateTime remindAt, String text) {
    this.remindAt = remindAt;
    this.text = text;
  }

  public LocalDateTime getRemindAt() {
    return remindAt;
  }

  public void setRemindAt(LocalDateTime remindAt) {
    this.remindAt = remindAt;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
