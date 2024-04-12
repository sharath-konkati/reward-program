INSERT INTO users (id, name, email) VALUES (1, 'John Doe', 'john@example.com');
INSERT INTO users (id, name, email) VALUES (2, 'Jane Smith', 'jane@example.com');
INSERT INTO users (id, name, email) VALUES (3, 'Alice Johnson', 'alice@example.com');
INSERT INTO users (id, name, email) VALUES (4, 'Bob Williams', 'bob@example.com');



INSERT INTO transactions (amount, timestamp, user_id) SELECT ROUND(RAND() * 200 + 50, 2) as amount, DATEADD(DAY, -FLOOR(RAND() * 90), CURRENT_DATE()) as timestamp, 2 as user_id FROM users LIMIT 10;
INSERT INTO transactions (amount, timestamp, user_id) SELECT ROUND(RAND() * 200 + 50, 2) as amount, DATEADD(DAY, -FLOOR(RAND() * 90), CURRENT_DATE()) as timestamp, 1 as user_id FROM users LIMIT 10;
