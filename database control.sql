create table customer
(
    id       bigint generated always as identity primary key,
    name     varchar(50)  not null,
    age      int          not null,
    email    varchar(100) not null,
    password varchar      not null
);

create table events
(
    id          bigint generated always as identity primary key,
    name        varchar(100) not null,
    description text         not null,
    place       varchar(100) not null,
    coordinates point,
    event_time  timestamptz  not null,
    creator_id  bigint       not null,

    foreign key (creator_id) references customer (id)
        on delete cascade on update cascade
);