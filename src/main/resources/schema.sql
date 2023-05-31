create table course (
    id bigint generated by default as identity,
    title varchar(255) not null,
    creator_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table session (
    id bigint generated by default as identity,
    course_id    BIGINT    NOT NULL,
    name varchar(255) not null,
    type varchar(255) not null,
    status varchar(255) not null,
    recruitment_status varchar(20) not null,
    image_name varchar(255) not null,
    image_path varchar(255) not null,
    capacity_number varchar(255) not null,
    capacity_number_max varchar(255) not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id),
    FOREIGN KEY (course_id) REFERENCES course (id)
);

create table ns_user (
    id bigint generated by default as identity,
    user_id varchar(20) not null,
    password varchar(20) not null,
    name varchar(20) not null,
    email varchar(50),
    type varchar(20) not null,
    session_type varchar(20) not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table enrollment (
    id bigint generated by default as identity,
    session_id    BIGINT    NOT NULL,
    ns_user_id    BIGINT    NOT NULL,
    status varchar(20) not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id),
    FOREIGN KEY (session_id) REFERENCES session (id),
    FOREIGN KEY (ns_user_id) REFERENCES ns_user (id)
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
