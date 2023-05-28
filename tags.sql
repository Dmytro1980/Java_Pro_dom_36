DROP TABLE IF EXISTS tags CASCADE ;

CREATE TABLE IF NOT EXISTS public."tags"(
    "id"         SERIAL      NOT NULL,
    "name"       VARCHAR(30) NOT NULL,
    "created_at" BIGINT      NOT NULL,
    CONSTRAINT tags_pkey PRIMARY KEY (id));

INSERT INTO public."tags" ("name", created_at)
VALUES ('google', 1684780709886),
       ('cnn', 1684867109886),
       ('football', 1684957297478),
       ('bbc', 1684968097478);

