-- NsUser 도메인
INSERT INTO ns_user (user_id, password, name, email, created_at)
values ('javajigi-sql', 'test', '자바지기 SQL', 'javajigi@slipp.net', CURRENT_TIMESTAMP()),
       ('sanjigi-sql', 'test', '산지기 SQL', 'sanjigi@slipp.net', CURRENT_TIMESTAMP());

-- Question 도메인
INSERT INTO question (question_id, writer_id, title, contents, created_at, deleted)
VALUES (55, 1, 'Ruby on Rails와 Play가 활성화되기 힘든 이유', 'Ruby on Rails', CURRENT_TIMESTAMP(), false),
       (66, 2, 'reflect 발동 주체', '설계를 희한하게 하는 바람', CURRENT_TIMESTAMP(), false);

-- Answer 도메인
INSERT INTO answer (writer_id, contents, created_at, question_id, deleted)
VALUES (1, '자바스크립트의 언어 기본 API 보완', CURRENT_TIMESTAMP(), 1, false),
       (2, '최신 버전을 공부', CURRENT_TIMESTAMP(), 1, false);

-- Image
INSERT INTO IMAGE (IMAGE_URL)
VALUES ('www'),
       ('sdsdf'),
       ('asdfsdf');
