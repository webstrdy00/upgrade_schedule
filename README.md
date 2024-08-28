# 💻 STACK

Environment

![인텔리제이](   https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![깃허브](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![깃](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![POSTMAN](https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

Development

![자바](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SPRING BOOT](https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![SQL](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Gradle](https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)


# :grey_exclamation: API Table & Script
## [🖇️ Postman API Script Link](https://documenter.getpostman.com/view/31167272/2sAXjJ6tTB)


# :bar_chart: ERD 
![upgrade_schedule_ERD](https://github.com/user-attachments/assets/4c8d6dfe-5d00-4e45-b0a7-199fc37c634b)


# :scroll: SQL 
```
create table schedules
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    content     varchar(255) not null,
    title       varchar(255) not null
);

create table comments
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)  null,
    modified_at datetime(6)  null,
    content     varchar(255) not null,
    username    varchar(255) not null,
    schedule_id bigint       null,
    constraint
        foreign key (schedule_id) references schedules (id)
);

create table users
(
    id          bigint auto_increment
        primary key,
    created_at  datetime(6)            null,
    modified_at datetime(6)            null,
    email       varchar(255)           not null,
    password    varchar(255)           not null,
    role        enum ('ADMIN', 'USER') not null,
    username    varchar(255)           not null,
    constraint
        unique (email)
);

create table user_schedule
(
    id          bigint auto_increment
        primary key,
    schedule_id bigint null,
    user_id     bigint null,
    constraint FK4wuna1k6hut7vaufl8neitnc1
        foreign key (user_id) references users (id),
    constraint
        foreign key (schedule_id) references schedules (id)
);
```

# :bookmark_tabs: Repository 
