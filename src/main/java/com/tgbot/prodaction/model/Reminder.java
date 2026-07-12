package com.tgbot.prodaction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "reminders")
public class Reminder {
  @Id
  @Column(updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "telegram_user_id", nullable = false)
  private TelegramUser telegramUser;

  @Column(nullable = false, name = "chat_id")
  private Long chatId;

  @Column(name = "text", nullable = false)
  private String text;

  @Column(name = "remind_at", nullable = false)
  private LocalDateTime remindAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private ReminderStatus status;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @Column(name = "sent_at")
  private LocalDateTime sentAt;

  public Reminder() {}

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public TelegramUser getTelegramUser() {
    return telegramUser;
  }

  public void setTelegramUser(TelegramUser telegramUser) {
    this.telegramUser = telegramUser;
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

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public LocalDateTime getSentAt() {
    return sentAt;
  }

  public void setSentAt(LocalDateTime sentAt) {
    this.sentAt = sentAt;
  }
}
