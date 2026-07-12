package com.tgbot.prodaction.dto;

import com.tgbot.prodaction.model.ReminderStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReminderResponse {
  private UUID id;
  private Long chatId;
  private String text;
  private LocalDateTime remindAt;
  private ReminderStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime sentAt;

  public ReminderResponse() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
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

  public ReminderStatus getStatus() {
    return status;
  }

  public void setStatus(ReminderStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(LocalDateTime sentAt) {
    this.sentAt = sentAt;
  }
}
