-- demodb.test_table definition

CREATE TABLE `test_table`
(
    `id`       varchar(20) NOT NULL,
    `name`     varchar(20) NOT NULL,
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `auth`     varchar(100)                                                  DEFAULT NULL,
    `enable`   tinyint(1)                                                    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

create table audit
(
    `id`   varchar(50) not null,
    `role` varchar(50) not null,
    `date` datetime(6) default CURRENT_TIMESTAMP(6),
    constraint audit_group_pk primary key (id, date)
);