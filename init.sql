DO $$

    DECLARE
        count_message_response_v int;

    BEGIN
        create table if not exists message_response (
            id serial primary key,
            code varchar(7) not null unique,
            http_status SMALLINT not null,
            message varchar(100) not null,
            status varchar(3) not null
        );

        select count(*) into count_message_response_v from message_response;
        if count_message_response_v = 0 then
            insert into message_response(code, http_status, message, status)
            values('SUC-000', 200,'Default successful.','ACT');
            insert into message_response(code, http_status, message, status)
            values('ERR-001', 200,'Default error.','ACT');
            insert into message_response(code, http_status, message, status)
            values('ERR-002', 200,'Data not found.','ACT');
        end if;

        create or replace view message_response_v as select * from message_response where status = 'ACT';
    END
$$