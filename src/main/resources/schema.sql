create table course (
    id bigint generated by default as identity,
    term varchar(20) not null,
    title varchar(255) not null,
    creator_id bigint not null,
    created_at timestamp not null,
    updated_at timestamp,
    primary key (id)
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

create table session (
    id bigint generated by default as identity,
    course_id bigint not null,
    start_date timestamp not null,
    end_date timestamp not  null,
    image_id bigint,
    open_status varchar(15) not null,
    recruit_status varchar(15) not null,
    session_type varchar(20) not null,
    max_size int,
    tuition bigint,
    primary key (id)
);

create table session_image (
    id bigint generated by default as identity,
    width int not null,
    height int not null,
    extension varchar(10) not null,
    file_size int not null,
    file_name varchar(100) not null,
    session_id bigint not null,
    primary key (id)
);

create table registration (
    id bigint generated by default as identity,
    session_id bigint not null,
    user_id bigint not null,
    status varchar(10) not null,
    primary key (id)
);
