create table course (
    id bigint generated by default as identity,
    title varchar(255) not null,
    creator_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
);

create table session (
    session_id bigint generated by default as identity,
    fee bigint not null,
    max_enrollment number not null,
    start_date timestamp not null,
    end_date timestamp not null,
    image_type varchar(10) not null,
    image_file_size int not null,
    image_width int not null,
    image_height int not null,
    session_status varchar(10) not null
);

create table session_cover_image (
    image_id bigint generated by default as identity,
    session_id bigint not null,
    image_type varchar(10) not null,
    image_file_size int not null,
    image_width int not null,
    image_height int not null
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
