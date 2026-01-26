--liquibase formatted sql

--changeset p.tokarev:1.0.0-bic_list
CREATE TABLE bic_list
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY,
    bic       VARCHAR(9)   NOT NULL,
    bank_name VARCHAR(160) NOT NULL,
    date_in   DATE,
    date_out  DATE,
    CONSTRAINT pk_bic_list PRIMARY KEY (id)
);

--changeset p.tokarev:1.0.0-inserts
INSERT INTO bic_list (bic, bank_name, date_in, date_out)
VALUES
    (
        '044030891',
        'КОНКУРСНЫЙ УПРАВЛЯЮЩИЙ КБ "СПБРР" ОАО-ГК"АСВ"',
        DATE '2007-07-19',
        DATE '2016-02-12'
    );

INSERT INTO bic_list (bic, bank_name, date_in, date_out)
VALUES
    (
        '041203789',
        'ФИЛИАЛ N4 ПАО МОСОБЛБАНК',
        DATE '2007-07-30',
        DATE '2016-08-30'
    );
