--liquibase formatted sql

--changeset p.tokarev:1.0.0-nspk_members
CREATE TABLE nspk_members
(
    id        BIGINT GENERATED ALWAYS AS IDENTITY,
    nspk_id   VARCHAR(16)  NOT NULL,
    bic       VARCHAR(16)  NOT NULL,
    bank_name VARCHAR(128) NOT NULL,
    CONSTRAINT pk_nspk_members PRIMARY KEY (id)
);

--changeset p.tokarev:1.0.0-inserts
INSERT INTO nspk_members (nspk_id, bic, bank_name)
VALUES
    (
        '100000000020',
        '044525111',
        'РОССЕЛЬХОЗБАНК'
    );

INSERT INTO nspk_members (nspk_id, bic, bank_name)
VALUES
    (
        '100000000140',
        '044525184',
        'МБ Банк'
    );
