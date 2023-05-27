-- H2 DB
DROP TABLE IF EXISTS course;
CREATE TABLE IF NOT EXISTS course
(
    id         BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    title      varchar(255) not null,
    creator_id bigint       not null,
    created_at timestamp    not null,
    updated_at timestamp,
    primary key (id)
);

DROP TABLE IF EXISTS ns_user;
CREATE TABLE IF NOT EXISTS ns_user
(
    id         BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    user_id    varchar(20) not null,
    password   varchar(20) not null,
    name       varchar(20) not null,
    email      varchar(50),
    created_at timestamp   not null,
    updated_at timestamp,
    primary key (id)
);

DROP TABLE IF EXISTS question;
CREATE TABLE IF NOT EXISTS question
(
    id         BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    created_at timestamp    not null,
    updated_at timestamp,
    contents   clob,
    deleted    boolean      not null,
    title      varchar(100) not null,
    writer_id  bigint,
    primary key (id)
);

DROP TABLE IF EXISTS answer;
CREATE TABLE IF NOT EXISTS answer
(
    id          BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    created_at  timestamp not null,
    updated_at  timestamp,
    contents    clob,
    deleted     boolean   not null,
    question_id bigint,
    writer_id   bigint,
    primary key (id)
);

DROP TABLE IF EXISTS delete_history;
CREATE TABLE IF NOT EXISTS delete_history
(
    id            BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    content_id    bigint,
    content_type  varchar(255),
    created_date  timestamp,
    deleted_by_id bigint,
    primary key (id)
);

DROP TABLE IF EXISTS image;
CREATE TABLE IF NOT EXISTS image
(
    image_id      BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
    image_url      varchar(255),
    primary key (image_id)
);