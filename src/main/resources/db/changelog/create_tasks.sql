create table if not exists tags (
        id         bigserial   not null,
        title      varchar(15) not null,
        color      varchar(20) not null,
        is_default boolean,
        primary key (id)
);