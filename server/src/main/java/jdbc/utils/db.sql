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
    category_id   integer unique NOT NULL,
    FOREIGN KEY (category_id) references categories (id)
);

Create table cards
(
    ID             SERIAL         NOT NULL PRIMARY KEY,
    folder_id      integer unique NOT NULL,
    FOREIGN KEY (folder_id) references folders (id),
    question       varchar,
    answer         varchar,
    question_image bytea,
    answer_image   bytea,
    next_replay    date,
    level          integer        not null
)