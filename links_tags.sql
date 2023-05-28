DROP TABLE IF EXISTS  links_tags CASCADE ;


CREATE TABLE IF NOT EXISTS "links_tags"(
    "link_id" INT NOT NULL,
    "tag_id" INT NOT NULL,
    PRIMARY KEY (link_id, tag_id),
    CONSTRAINT "links_tags_links_id_fk" FOREIGN KEY ("link_id") REFERENCES public."links" ("id"),
    CONSTRAINT "links_tags_tags_id_fk" FOREIGN KEY ("tag_id") REFERENCES public."tags" ("id"));



INSERT INTO public."links_tags" ("link_id", "tag_id")
VALUES (1,1),
       (2,2),
       (3,3),
       (4,4),
       (1,7),
       (1,5);

-- INSERT INTO public."links_tags" ("link_id", "tag_id")
-- VALUES (1,7);
--
-- INSERT INTO public."links_tags" ("link_id", "tag_id")
-- VALUES (1,5);

