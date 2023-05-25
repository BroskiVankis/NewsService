INSERT INTO user_roles (id, user_role)
values
    (1, 'ADMIN'),
    (2, 'PUBLISHER'),
    (3, 'READER');

INSERT INTO users (id, email, first_name, last_name, is_active, password)
VALUES
    (1, 'admin@example.com', 'Ivan', 'Ivanov', 1, '12345'),
    (2, 'publisher@example.com', 'Gosho', 'Goshev', 1, '12345'),
    (3, 'reader@example.com', 'Petar', 'Petrov', 1, '12345');

INSERT INTO users_user_roles (users_id, user_roles_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO news (id, creation_date, photo_link, state, text, title, valid_from, valid_to, user_id)
VALUES
    ('5ebdd23e-7bf3-4166-ab67-98242b871f6a', '2023-05-24', 'https://basketnews.com/news-189648-sasha-vezenkov-dedicates-mvp-award-to-special-person-explains-bartzokas-role-in-his-growth.html#lg=1&slide=0', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('5ebdd23e-7bf3-4166-ab67-98242b871f6b', '2023-05-24', 'https://basketnews.com/news-189648-sasha-vezenkov-dedicates-mvp-award-to-special-person-explains-bartzokas-role-in-his-growth.html#lg=1&slide=0', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('5ebdd23e-7bf3-4166-ab67-98242b871f6c', '2023-05-24', 'https://basketnews.com/news-189648-sasha-vezenkov-dedicates-mvp-award-to-special-person-explains-bartzokas-role-in-his-growth.html#lg=1&slide=0', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('5ebdd23e-7bf3-4166-ab67-98242b871f6d', '2023-05-24', 'https://basketnews.com/news-189648-sasha-vezenkov-dedicates-mvp-award-to-special-person-explains-bartzokas-role-in-his-growth.html#lg=1&slide=0', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1);