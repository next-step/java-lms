create table course (
    id bigint generated by default as identity,
    title varchar(255) not null,
    cohort bigint not null,
    creator_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table session (
    id bigint generated by default as identity,
    maximum_number_of_student bigint not null,
    started_at timestamp not null,
    ended_at timestamp,
    status varchar(20) not null,
    amount bigint
);

create table ns_user (
    id bigint generated by default as identity,
    user_id varchar(20) not null,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(50),
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table question (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    title varchar(100) not null,
    writer_id bigint,
    primary key (id)
);

create table answer (
    id bigint generated by default as identity,
    created_at timestamp not null,
    updated_at timestamp,
    contents clob,
    deleted boolean not null,
    question_id bigint,
    writer_id bigint,
    primary key (id)
);

create table delete_history (
    id bigint not null,
    content_id bigint,
    content_type varchar(255),
    created_date timestamp,
    deleted_by_id bigint,
    primary key (id)
);
