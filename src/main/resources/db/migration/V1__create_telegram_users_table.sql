CREATE TABLE telegram_users (
    id UUID NOT NULL PRIMARY KEY,
    telegram_user_id BIGINT NOT NULL UNIQUE,
    chat_id BIGINT NOT NULL,
    username VARCHAR(50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);
