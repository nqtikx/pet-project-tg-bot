package com.tgbot.prodaction.dto;


public class CreateReminderFromCommandRequest {
  private Long telegramUserId;
  private Long chatId;
  private String username;
  private String firstName;
  private String lastName;
  private String messageText;

  public CreateReminderFromCommandRequest() {
  }

  public Long getTelegramUserId() {
    return telegramUserId;
  }

  public void setTelegramUserId(Long telegramUserId) {
    this.telegramUserId = telegramUserId;
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

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }
}
