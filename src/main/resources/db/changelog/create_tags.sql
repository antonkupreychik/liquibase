  create table if not exists tasks (
                id          bigserial   not null,
                title       varchar(50) not null,
                description varchar(500),
                priority    varchar(10) not null,
                status      varchar(15) not null,
                user_id     bigint      not null,
                create_date timestamp(6),
                last_update timestamp(6),
                primary key (id)
);