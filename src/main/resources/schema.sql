create table course
(
    id         bigint generated by default as identity,
    title      varchar(255) not null,
    cohort     bigint       not null,
    creator_id bigint       not null,
    created_at timestamp    not null,
    updated_at timestamp,
    primary key (id)
);

create table session
(
    id                        bigint generated by default as identity,
    course_id                 bigint      not null,
    maximum_number_of_student bigint      not null,
    started_at                timestamp   not null,
    ended_at                  timestamp,
    status                    varchar(20) not null,
    progress_status           varchar(20) not null,
    recruitment_status        varchar(20) not null,
    amount                    bigint,
    type                      varchar(10) not null,

    primary key (id),
    constraint fk_course foreign key (course_id) references course (id)
);

create table session_cover_images
(
    id            bigint generated by default as identity,
    session_id    bigint      not null,
    size_of_bytes int         not null,
    width         int         not null,
    height        int         not null,
    image_type    varchar(20) not null,

    constraint fk_session foreign key (session_id) references session (id)
);

create table ns_user
(
    id         bigint generated by default as identity,
    user_id    varchar(20) not null,
    password   varchar(20) not null,
    name       varchar(20) not null,
    email      varchar(50),
    created_at timestamp   not null,
    updated_at timestamp,
    primary key (id)
);

create table enrollment
(
    id         bigint generated by default as identity,
    session_id bigint      not null,
    user_id    bigint      not null,
    status     varchar(20) not null,

    primary key (id),
    constraint fk_enrollment_session foreign key (session_id) references session (id),
    constraint fk_enrollment_ns_user foreign key (user_id) references ns_user (id)
);

create table question
(
    id         bigint generated by default as identity,
    created_at timestamp    not null,
    updated_at timestamp,
    contents   clob,
    deleted    boolean      not null,
    title      varchar(100) not null,
    writer_id  bigint,
    primary key (id)
);

create table answer
(
    id          bigint generated by default as identity,
    created_at  timestamp not null,
    updated_at  timestamp,
    contents    clob,
    deleted     boolean   not null,
    question_id bigint,
    writer_id   bigint,
    primary key (id)
);

create table delete_history
(
    id            bigint not null,
    content_id    bigint,
    content_type  varchar(255),
    created_date  timestamp,
    deleted_by_id bigint,
    primary key (id)
);
