-- CREATE TABLE IF NOT EXISTS public."black_list"(
--     "id" SERIAL NOT NULL,
--     "domain" VARCHAR(50) NOT NULL,
--     "created_at" BIGINT NOT NULL,
--     CONSTRAINT black_list_pkey PRIMARY KEY (id));

INSERT INTO public."black_list" ("domain", created_at)
VALUES ('mail.ru', 1685004640385),
       ('vk.ru', 1685008240385),
       ('lenta.ru', 1685011840385);

