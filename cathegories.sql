-- CREATE TABLE IF NOT EXISTS public."categories"(
--     "id"         SERIAL      NOT NULL,
--     "name"       VARCHAR(30) NOT NULL,
--     "created_at" BIGINT      NOT NULL,
--     CONSTRAINT "cathegoies_pkey" PRIMARY KEY (id));

INSERT INTO public."categories" ("name", created_at)
VALUES ('sport', 1684784309886),
       ('news', 1684870709886),
       ('search', 1684960897478);

