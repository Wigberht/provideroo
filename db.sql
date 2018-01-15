CREATE TABLE account
(
  id                 INT AUTO_INCREMENT
    PRIMARY KEY,
  balance            DECIMAL(15, 2) DEFAULT '0.00' NOT NULL,
  currency_shortname CHAR(3) DEFAULT 'UAH'         NOT NULL,
  currency_symbol    CHAR                          NULL
);

CREATE TABLE chat
(
  id    INT AUTO_INCREMENT
    PRIMARY KEY,
  title VARCHAR(50) NULL,
  CONSTRAINT chat_id_uindex
  UNIQUE (id)
);

CREATE TABLE chat_user
(
  user_id INT NULL,
  chat_id INT NULL,
  CONSTRAINT chat_user_chat_id_fk
  FOREIGN KEY (chat_id) REFERENCES chat (id)
);

CREATE INDEX chat_user_chat_id_fk
  ON chat_user (chat_id);

CREATE INDEX chat_user_user_id_fk
  ON chat_user (user_id);

CREATE TABLE message
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  message    TEXT                               NOT NULL,
  was_read   TINYINT(1) DEFAULT '0'             NULL,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
  user_id    INT                                NOT NULL,
  CONSTRAINT message_id_uindex
  UNIQUE (id)
);

CREATE INDEX message_user_id_fk
  ON message (user_id);

CREATE TABLE message_chat
(
  message_id INT NOT NULL,
  chat_id    INT NOT NULL,
  CONSTRAINT message_chat_message_id_fk
  FOREIGN KEY (message_id) REFERENCES message (id)
    ON DELETE CASCADE,
  CONSTRAINT message_chat_chat_id_fk
  FOREIGN KEY (chat_id) REFERENCES chat (id)
    ON DELETE CASCADE
);

CREATE INDEX message_chat_message_id_fk
  ON message_chat (message_id);

CREATE INDEX message_chat_chat_id_fk
  ON message_chat (chat_id);

CREATE TABLE role
(
  id   INT AUTO_INCREMENT
    PRIMARY KEY,
  name VARCHAR(50) NULL,
  CONSTRAINT role_id_uindex
  UNIQUE (id)
);

CREATE TABLE service
(
  id    INT AUTO_INCREMENT
    PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  CONSTRAINT service_id_uindex
  UNIQUE (id)
);

CREATE TABLE subscriber
(
  id         INT AUTO_INCREMENT
    PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL,
  birth_date DATE         NOT NULL,
  user_id    INT          NULL,
  account_id INT          NULL,
  CONSTRAINT subscriber_id_uindex
  UNIQUE (id),
  CONSTRAINT subscriber_account_id_fk
  FOREIGN KEY (account_id) REFERENCES account (id)
    ON UPDATE SET NULL
    ON DELETE SET NULL
);

CREATE INDEX subscriber_account_id_fk
  ON subscriber (account_id);

CREATE INDEX subscriber_user_id_fk
  ON subscriber (user_id);

CREATE TABLE tariff
(
  id                 INT AUTO_INCREMENT
    PRIMARY KEY,
  title              VARCHAR(50)                   NULL,
  description        TEXT                          NULL,
  number_of_days     INT DEFAULT '0'               NULL,
  cost               DECIMAL(15, 2) DEFAULT '0.00' NULL,
  currency_shortname CHAR(3) DEFAULT 'UAH'         NULL,
  service_id         INT                           NULL,
  CONSTRAINT tariff_id_uindex
  UNIQUE (id),
  CONSTRAINT tariff_service_id_fk
  FOREIGN KEY (service_id) REFERENCES service (id)
);

CREATE INDEX tariff_service_id_fk
  ON tariff (service_id);

CREATE TABLE tariff_subscriber
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  start         DATE DEFAULT '1000-01-01' NULL,
  end           DATE DEFAULT '1000-01-01' NULL,
  tariff_id     INT                       NULL,
  subscriber_id INT                       NULL,
  CONSTRAINT tariff_subscriber_id_uindex
  UNIQUE (id),
  CONSTRAINT tariff_subscriber_tariff_id_fk
  FOREIGN KEY (tariff_id) REFERENCES tariff (id),
  CONSTRAINT tariff_subscriber_subscriber_id_fk
  FOREIGN KEY (subscriber_id) REFERENCES subscriber (id)
);

CREATE INDEX tariff_subscriber_subscriber_id_fk
  ON tariff_subscriber (subscriber_id);

CREATE INDEX tariff_subscriber_tariff_id_fk
  ON tariff_subscriber (tariff_id);

CREATE TABLE user
(
  id       INT AUTO_INCREMENT
    PRIMARY KEY,
  login    VARCHAR(50)            NOT NULL,
  password CHAR(64)               NOT NULL,
  banned   TINYINT(1) DEFAULT '0' NULL,
  role_id  INT                    NULL,
  CONSTRAINT user_id_uindex
  UNIQUE (id),
  CONSTRAINT user_role_id_fk
  FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE INDEX user_role_id_fk
  ON user (role_id);

ALTER TABLE chat_user
  ADD CONSTRAINT chat_user_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE message
  ADD CONSTRAINT message_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;

ALTER TABLE subscriber
  ADD CONSTRAINT subscriber_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (id);

CREATE TABLE user_tariff
(
  user_id   INT NULL,
  tariff_id INT NULL,
  CONSTRAINT user_tariff_user_id_fk
  FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT user_tariff_tariff_id_fk
  FOREIGN KEY (tariff_id) REFERENCES tariff (id)
);

CREATE INDEX user_tariff_user_id_fk
  ON user_tariff (user_id);

CREATE INDEX user_tariff_tariff_id_fk
  ON user_tariff (tariff_id);

