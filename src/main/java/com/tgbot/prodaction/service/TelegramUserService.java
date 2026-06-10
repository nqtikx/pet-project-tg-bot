package com.tgbot.prodaction.service;

import com.tgbot.prodaction.model.TelegramUser;
import com.tgbot.prodaction.repository.TelegramUserRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TelegramUserService {
  private final TelegramUserRepository telegramUserRepository;

  @Autowired
  public TelegramUserService(TelegramUserRepository telegramUserRepository) {
    this.telegramUserRepository = telegramUserRepository;
  }

  public Optional<TelegramUser> findByChatId(Long chatId) {
    return telegramUserRepository.findByChatId(chatId);
  }

  public TelegramUser findOrCreateUser(Long telegramUserId, Long chatId, String username,
      String firstName, String lastName) {
    LocalDateTime now = LocalDateTime.now();
    Optional<TelegramUser> existingTgUser = telegramUserRepository.findByTelegramUserId(telegramUserId);
    if (existingTgUser.isPresent()) {
      TelegramUser tgUser = existingTgUser.get();
      tgUser.setChatId(chatId);
      tgUser.setUsername(username);
      tgUser.setFirstName(firstName);
      tgUser.setLastName(lastName);
      tgUser.setUpdatedAt(now);

      return telegramUserRepository.save(tgUser);

    } else {
      TelegramUser newTgUser = new TelegramUser();
      newTgUser.setId(UUID.randomUUID());
      newTgUser.setTelegramUserId(telegramUserId);
      newTgUser.setChatId(chatId);
      newTgUser.setUsername(username);
      newTgUser.setFirstName(firstName);
      newTgUser.setLastName(lastName);
      newTgUser.setCreatedAt(now);
      newTgUser.setUpdatedAt(now);

      return telegramUserRepository.save(newTgUser);
    }
  }
}
