create table session (
    id         bigint generated by default as identity,
    paid_type  varchar(10) not null,
    fee        bigint,
    capacity   bigint,
    state      varchar(10) not null,
    start_date timestamp   not null,
    end_date   timestamp   not null,
    primary key (id)
);

create table new_session (
    id               bigint generated by default as identity,
    paid_type        varchar(10) not null,
    fee              bigint,
    capacity         bigint,
    running_state    varchar(10) not null,
    recruiting_state varchar(10) not null,
    start_date       timestamp   not null,
    end_date         timestamp   not null,
    primary key (id)
);

create table cover_image (
    id         bigint generated by default as identity,
    session_id bigint,
    type       varchar(10) not null,
    file_size  bigint      not null,
    width      bigint      not null,
    height     bigint      not null,
    primary key (id)
);

create table registration (
    id         bigint generated by default as identity,
    session_id bigint,
    user_id    bigint,
    payment_id bigint,
    primary key (id)
);

create table new_registration (
    id             bigint generated by default as identity,
    session_id     bigint,
    user_id        bigint,
    payment_id     bigint,
    selection_type varchar(1),
    approval_type  varchar(1),
    primary key (id)
);

create table course (
    id         bigint generated by default as identity,
    title      varchar(255) not null,
    creator_id bigint       not null,
    created_at timestamp    not null,
    updated_at timestamp,
    primary key (id)
);

create table ns_user (
    id         bigint generated by default as identity,
    user_id    varchar(20) not null,
    password   varchar(20) not null,
    name       varchar(20) not null,
    email      varchar(50),
    created_at timestamp   not null,
    updated_at timestamp,
    primary key (id)
);

create table question (
    id         bigint generated by default as identity,
    created_at timestamp    not null,
    updated_at timestamp,
    contents   clob,
    deleted    boolean      not null,
    title      varchar(100) not null,
    writer_id  bigint,
    primary key (id)
);

create table answer (
    id          bigint generated by default as identity,
    created_at  timestamp not null,
    updated_at  timestamp,
    contents    clob,
    deleted     boolean   not null,
    question_id bigint,
    writer_id   bigint,
    primary key (id)
);

create table delete_history (
    id            bigint not null,
    content_id    bigint,
    content_type  varchar(255),
    created_date  timestamp,
    deleted_by_id bigint,
    primary key (id)
);
