package com.tgbot.prodaction.dto;

import java.time.LocalDateTime;

public class CreateReminderRequest {
  private Long telegramUserId;
  private Long chatId;
  private String username;
  private String firstName;
  private String lastName;
  private String text;
  private LocalDateTime remindAt;

  public CreateReminderRequest() {
  }

  public Long getTelegramUserId() {
    return telegramUserId;
  }

  public void setTelegramUserId(Long telegramUser) {
    this.telegramUserId = telegramUser;
  }

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public LocalDateTime getRemindAt() {
    return remindAt;
  }

  public void setRemindAt(LocalDateTime remindAt) {
    this.remindAt = remindAt;
  }
}
