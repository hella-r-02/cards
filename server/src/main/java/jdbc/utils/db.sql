CREATE TABLE categories
(
    ID   SERIAL  NOT NULL PRIMARY KEY,
    NAME varchar NOT NULL
);

CREATE TABLE folders
(
    ID            SERIAL         NOT NULL PRIMARY KEY,
    NAME          varchar        NOT NULL,
    NUM_OF_LEVELS integer        not null,
    category_id   integer  NOT NULL,
    FOREIGN KEY (category_id) references categories (id)
);

CREATE table levels
(
    ID        SERIAL  NOT NULL PRIMARY KEY,
    folder_id integer NOT NULL,
    FOREIGN KEY (folder_id) references folders (id),
    next_replay date,
    num_of_level integer not null
);
Create table cards(
                      ID SERIAL NOT NULL PRIMARY KEY,
                      level_id integer  NOT NULL,
                      FOREIGN KEY  (level_id) references  levels (id),
                      question varchar,
                      answer varchar,
                      question_image bytea,
                      answer_image bytea,
                      next_replay date);