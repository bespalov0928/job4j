CREATE DATABASE IF NOT EXISTS user_requests;

USE user_requests;

CREATE TABLE IF NOT EXISTS user_requests.user_role (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  role varchar(255) NOT NULL
) ENGINE=InnoDB;

INSERT INTO user_requests.user_role (id, role) VALUES
(1, 'Guest'),
(2, 'Administrator');

CREATE TABLE IF NOT EXISTS user_requests.user_role_rule (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  rule varchar(255) NOT NULL
) ENGINE=InnoDB;

INSERT INTO user_requests.user_role_rule (id, rule) VALUES
(1, 'Add'),
(2, 'Edit'),
(3, 'Delete');

CREATE TABLE IF NOT EXISTS user_requests.user_role_to_rule (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  role_id int(11) NOT NULL,
  rule_id int(11) NOT NULL,
  FOREIGN KEY (role_id)
        REFERENCES user_role (id)
        ON DELETE CASCADE,
  FOREIGN KEY (rule_id)
        REFERENCES user_role_rule (id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO user_requests.user_role_to_rule (id, role_id, rule_id) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2),
(4, 2, 3);

CREATE TABLE IF NOT EXISTS user_requests.user (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  role_id int(11) NOT NULL,
  FOREIGN KEY (role_id)
        REFERENCES user_role (id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO user_requests.user (id, name, role_id) VALUES
(1, 'Guest', 1),
(2, 'Admin', 2);

CREATE TABLE IF NOT EXISTS user_requests.request_status (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  status varchar(255) NOT NULL
) ENGINE=InnoDB;

INSERT INTO user_requests.request_status (id, status) VALUES
(1, 'New'),
(2, 'Checked');

CREATE TABLE IF NOT EXISTS user_requests.request_category (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  category varchar(255) NOT NULL
) ENGINE=InnoDB;

INSERT INTO user_requests.request_category (id, category) VALUES
(1, 'Category 1'),
(2, 'Category 2');

CREATE TABLE IF NOT EXISTS user_requests.request_attachment (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  path text NOT NULL
) ENGINE=InnoDB;

INSERT INTO user_requests.request_attachment (id, path) VALUES
(1, '/data/1.jpg'),
(2, '/data/2.jpg');

CREATE TABLE IF NOT EXISTS user_requests.request (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  status_id int(11) NOT NULL,
  category_id int(11) NOT NULL,
  date_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE CASCADE,
  FOREIGN KEY (status_id)
        REFERENCES request_status (id),
  FOREIGN KEY (category_id)
        REFERENCES request_category (id)
) ENGINE=InnoDB;

INSERT INTO user_requests.request (id, user_id, status_id, category_id, date_time) VALUES
(1, 1, 1, 1, '2020-03-02 19:32:33');

CREATE TABLE IF NOT EXISTS user_requests.request_to_attachment (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  request_id int(11) NOT NULL,
  attachment_id int(11) NOT NULL,
  FOREIGN KEY (request_id)
        REFERENCES request (id)
        ON DELETE CASCADE,
  FOREIGN KEY (attachment_id)
        REFERENCES request_attachment (id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO user_requests.request_to_attachment (id, request_id, attachment_id) VALUES
(1, 1, 1),
(2, 1, 2);

CREATE TABLE IF NOT EXISTS user_requests.request_comment (
  id int(11) PRIMARY KEY AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  request_id int(11) NOT NULL,
  comment text NOT NULL,
  FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE CASCADE,
  FOREIGN KEY (request_id)
        REFERENCES request (id)
        ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO user_requests.request_comment (id, user_id, request_id, comment) VALUES
(1, 1, 1, 'Test');