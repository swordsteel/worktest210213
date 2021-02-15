INSERT INTO articles (uuid, published_at, title, author, content)
values (random_uuid(), DATEADD('DAY', -8, current_timeStamp), 'title a1', 'author 1', 'content for title a1'),
       (random_uuid(), DATEADD('DAY', -8, current_timeStamp), 'title b1', 'author 2', 'content for title b1'),
       (random_uuid(), DATEADD('DAY', -7, current_timeStamp), 'title a2', 'author 1', 'content for title a2'),
       (random_uuid(), DATEADD('DAY', -6, current_timeStamp), 'title a3', 'author 1', 'content for title a3'),
       (random_uuid(), DATEADD('DAY', -6, current_timeStamp), 'title b2', 'author 2', 'content for title b2'),
       (random_uuid(), DATEADD('DAY', -5, current_timeStamp), 'title a4', 'author 1', 'content for title a4'),
       (random_uuid(), DATEADD('DAY', -3, current_timeStamp), 'title a5', 'author 1', 'content for title a5'),
       (random_uuid(), DATEADD('DAY', -2, current_timeStamp), 'title a6', 'author 1', 'content for title a6'),
       (random_uuid(), DATEADD('DAY', -2, current_timeStamp), 'title b3', 'author 2', 'content for title b3'),
       (random_uuid(), DATEADD('DAY', -1, current_timeStamp), 'title a7', 'author 1', 'content for title a7'),
       (random_uuid(), DATEADD('DAY', -1, current_timeStamp), 'title a8', 'author 1', 'content for title a8'),
       (random_uuid(), DATEADD('DAY', -1, current_timeStamp), 'title b4', 'author 2', 'content for title b4'),
       (random_uuid(), current_timeStamp, 'title a9', 'author 1', 'content for title a9'),
       (random_uuid(), current_timeStamp, 'title b5', 'author 2', 'content for title b5'),
       (random_uuid(), current_timeStamp, 'title b6', 'author 2', 'content for title b6');

-- accounts
INSERT INTO users (username, password, enabled)
values ('admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 1),
       ('user', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 1);

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN'),
       ('user', 'ROLE_USER');