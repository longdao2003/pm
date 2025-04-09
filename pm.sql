-- Bảng user
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Bảng password
CREATE TABLE password (
    id INT AUTO_INCREMENT PRIMARY KEY,
    is_letters BOOLEAN,
    is_digits BOOLEAN,
    is_symbols BOOLEAN,
    is_similar_char BOOLEAN,
    password_length INT,
    password VARCHAR(255) NOT NULL 
);

-- Bảng logins
CREATE TABLE logins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id INT,
    website VARCHAR(255),
    note TEXT,
    password_id INT,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (password_id) REFERENCES password(id)
);
