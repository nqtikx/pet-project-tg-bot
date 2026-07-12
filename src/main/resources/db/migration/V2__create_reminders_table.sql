CREATE TABLE reminders (
    id UUID NOT NULL PRIMARY KEY,
    telegram_user_id UUID NOT NULL REFERENCES telegram_users(id),
    chat_id BIGINT NOT NULL UNIQUE,
    text TEXT NOT NULL,
    remind_at TIMESTAMP NOT NULL,
    status VARCHAR(25) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    sent_at TIMESTAMP
);

CREATE INDEX idx_reminders_status_remind_at ON reminders(status, remind_at);
CREATE INDEX idx_reminders_chat_id_status_remind_at ON reminders(chat_id, status, remind_at);
