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
    course_id bigint not null,
    generation bigint not null,
    started_at timestamp not null,
    finished_at timestamp not null,
    created_at timestamp not null,
    updated_at timestamp,
    session_status varchar(10) not null,
    amount bigint not null,
    max_user bigint not null,
    primary key (id)
);

alter table session rename column session_status to session_recruitment_status;
alter table session alter column session_recruitment_status varchar(10) not null;
alter table session add session_progress_status varchar(11);
alter table session add approval_required boolean default false not null;
alter table session add teacher_id bigint;

create table cover_image (
    id bigint generated by default as identity,
    session_id bigint not null,
    size bigint not null,
    image_type varchar(4) not null,
    width bigint not null,
    height bigint not null,
    primary key (id)
);

create table ns_user_session (
    session_id bigint not null,
    ns_user_id bigint not null,
    primary key (session_id, ns_user_id)
);

alter table ns_user_session add enrollment_status varchar(8) default 'APPROVED' not null;

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
