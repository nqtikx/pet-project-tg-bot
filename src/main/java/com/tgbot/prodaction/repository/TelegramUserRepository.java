package com.tgbot.prodaction.repository;

import com.tgbot.prodaction.model.TelegramUser;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, UUID> {

  Optional<TelegramUser> findByChatId(Long chatId);

  Optional<TelegramUser> findByTelegramUserId(Long telegramUserId);
}

