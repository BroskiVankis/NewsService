INSERT INTO user_roles (id, user_role)
values
    (1, 'ADMIN'),
    (2, 'PUBLISHER'),
    (3, 'READER');

INSERT INTO users (id, email, first_name, last_name, is_active, password)
VALUES
    (1, 'admin@example.com', 'Ivan', 'Ivanov', 1, '68e5768b3269a128ff63bb2855e6619ed4dac65deef1b6e78390b62244f83631b0e9c3907fdadeb57586f2719b95ec3f84c0530b1eaee96297642f677192304ff8553115940417d36d600b5a7a1d3b78'),
    (2, 'publisher@example.com', 'Gosho', 'Goshev', 1, '68e5768b3269a128ff63bb2855e6619ed4dac65deef1b6e78390b62244f83631b0e9c3907fdadeb57586f2719b95ec3f84c0530b1eaee96297642f677192304ff8553115940417d36d600b5a7a1d3b78'),
    (3, 'reader@example.com', 'Petar', 'Petrov', 1, '68e5768b3269a128ff63bb2855e6619ed4dac65deef1b6e78390b62244f83631b0e9c3907fdadeb57586f2719b95ec3f84c0530b1eaee96297642f677192304ff8553115940417d36d600b5a7a1d3b78');

INSERT INTO users_user_roles (users_id, user_roles_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO news (id, creation_date, photo_link, state, text, title, valid_from, valid_to, user_id)
VALUES
    ('a751577e-131c-4ebc-a5f6-c6918c58e271', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e272', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e273', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e274', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e276', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e277', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1),
('a751577e-131c-4ebc-a5f6-c6918c58e278', '2023-05-24', 'https://www.bulgariantimes.co.uk/wp-content/uploads/2023/05/adac8bc2-sashavezenkov-olympiacos-euroleaguemvp-byeurohoops.jpg', 'NEW', 'Vezenkov winning the European MVP award', 'Vezenkov MVP', '2023-05-24', '2023-05-30', 1);