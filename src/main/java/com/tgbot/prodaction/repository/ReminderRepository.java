package com.tgbot.prodaction.repository;

import com.tgbot.prodaction.model.Reminder;
import com.tgbot.prodaction.model.ReminderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

  List<Reminder> findByChatIdAndStatusOrderByRemindAtAsc(Long chatId, ReminderStatus status);

  List<Reminder> findByStatusAndRemindAtLessThanEqualOrderByRemindAtAsc(ReminderStatus status, LocalDateTime remindAt);
}
