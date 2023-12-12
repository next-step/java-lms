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
    pay_type varchar(20) not null,
    session_status varchar(20) not null,
    cover_image_id bigint not null,
    enrolment_id bigint not null,
    start_date timestamp not null,
    end_date timestamp not null,
    amount bigint,
    students_capacity integer,
    primary key (id)
);

create table enrolment (
    id bigint generated by default as identity,
    enroll_status varchar(20) not null ,
    primary key (session_id)
)

create table student (
    id bigint generated by default as identity,
    enrolment_id bigint not null,
    session_id bigint not null,
    ns_user_id bigint not null,
    primary key (id)
);

create table cover_image (
    id bigint generated by default as identity,
    file_name varchar(200) not null,
    width integer not null,
    height integer not null,
    capacity_size bigint not null,
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
