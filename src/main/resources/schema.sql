-- H2 DB
DROP TABLE IF EXISTS course;
CREATE TABLE IF NOT EXISTS course
(
    course_id  BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    title      varchar(255) not null,
    creator_id bigint       not null COMMENT '과정을 진행하는 크리에이터 고유번호',
    created_at timestamp    not null,
    updated_at timestamp    not null,
    primary key (course_id)
);

DROP TABLE IF EXISTS ns_user;
CREATE TABLE IF NOT EXISTS ns_user
(
    user_code  varchar(20) NOT NULL COMMENT 'ID',
    password   varchar(20) not null,
    name       varchar(20) not null,
    email      varchar(50),
    created_at timestamp   not null,
    updated_at timestamp   not null default now(),
    primary key (user_code)
);

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question
(
    question_id      BIGINT       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    writer_user_code varchar(20)  NOT NULL COMMENT 'Question 을 생성한 USER_CODE',
    title            varchar(100) NOT NULL,
    contents         clob,
    deleted          boolean      NOT NULL,
    updated_at       timestamp    NOT NULL,
    created_at       timestamp    NOT NULL,
    primary key (question_id)
);


DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer
(
    answer_id        BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    writer_user_code varchar(20) NOT NULL COMMENT 'Answer 을 생성한 USER_CODE',
    question_id      bigint,
    contents         clob,
    deleted          boolean     not null,
    created_at       timestamp   not null,
    updated_at       timestamp,
    primary key (answer_id)
);

DROP TABLE IF EXISTS delete_history;
CREATE TABLE IF NOT EXISTS delete_history
(
    delete_history_id    BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    content_type         varchar(20),
    content_id           bigint,
    created_At           timestamp,
    deleted_by_user_code varchar(20) COMMENT '글을 삭제한 USER_CODE',
    primary key (delete_history_id)
);

DROP TABLE IF EXISTS image;
CREATE TABLE IF NOT EXISTS image
(
    image_id  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    image_url varchar(255),
    primary key (image_id)
);

DROP TABLE IF EXISTS session;
CREATE TABLE IF NOT EXISTS session
(
    session_id        BIGINT    NOT NULL AUTO_INCREMENT COMMENT 'ID',
    image_id          BIGINT    NULL,
    term              BIGINT    NULL,
    price             BIGINT    NULL,
    session_status    VARCHAR(20),
    max_student_count BIGINT    NOT NULL,
    start_date        TIMESTAMP NOT NULL,
    end_date          TIMESTAMP NOT NULL,
    primary key (session_id)
);

DROP TABLE IF EXISTS enroll;
CREATE TABLE IF NOT EXISTS enroll
(
    enroll_id     BIGINT      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    session_id    BIGINT      NOT NULL,
    user_code     varchar(20) NOT NULL,
    enroll_status varchar(20) NOT NULL,
    primary key (enroll_id)
);
