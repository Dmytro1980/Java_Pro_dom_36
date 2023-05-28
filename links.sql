DROP  TABLE  IF EXISTS  links CASCADE ;

CREATE TABLE IF NOT EXISTS "links"(
    "id" SERIAL,
    "name" VARCHAR(50) NOT NULL ,
    "value" VARCHAR(100) NOT NULL ,
    "category_id" INT NOT NULL ,
    "created_at" BIGINT NOT NULL,
    CONSTRAINT "links_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "links_category_id_uk" FOREIGN KEY ("category_id") REFERENCES "categories"("id"));

INSERT INTO public."links" ("name", "value", "category_id", "created_at")
VALUES ('Google', 'https://www.google.com', 3, 1684787909886),
       ('CNN WORLD', 'https://edition.cnn.com/world', 2, 1684874309886),
       ('Football', 'https://football.ua', 1, 1684964497478),
       ('BBC', 'https://www.bbc.com/', 2, 1685008011853);

INSERT INTO public."links" ("name", "value", "category_id", "created_at")
VALUES ('Google333', 'https://www.google.com', 3, 1685215612912);

