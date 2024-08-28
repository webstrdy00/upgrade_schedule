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
![ERD](https://github.com/user-attachments/assets/d44c5dc7-c6a5-4a7d-8dcb-480c84f44ae2)


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
```
project-root/
│
├── build/
├── gradle/
├── src/
│   └── main/
│       └── java/
│           └── com.webstrdy00.upgrade_schedule/
│               ├── config/
│               │   ├── JacksonConfig.java
│               │   ├── PasswordEncoder.java
│               │   └── WebMvcConfig.java
│               │
│               ├── controller/
│               │   ├── CommentController.java
│               │   ├── ScheduleController.java
│               │   └── UserController.java
│               │
│               ├── dto/
│               │   ├── commentDto/
│               │   │   ├── CommentRequestDto.java
│               │   │   └── CommentResponseDto.java
│               │   │
│               │   ├── scheduleDto/
│               │   │   ├── ScheduleBriefDto.java
│               │   │   ├── ScheduleListResponseDto.java
│               │   │   ├── ScheduleRequestDto.java
│               │   │   ├── ScheduleResponseDto.java
│               │   │   └── WeatherRequestDto.java
│               │   │
│               │   └── userDto/
│               │       ├── LoginRequestDto.java
│               │       ├── SignupRequestDto.java
│               │       ├── UserBriefDto.java
│               │       ├── UserRequestDto.java
│               │       └── UserResponseDto.java
│               │
│               ├── entity/
│               │   ├── BaseTimeEntity.java
│               │   ├── Comment.java
│               │   ├── Schedule.java
│               │   ├── User.java
│               │   ├── UserRoleEnum.java
│               │   └── UserSchedule.java
│               │
│               ├── exception/
│               │   └── UnauthorizedException.java
│               │
│               ├── jwt/
│               │   ├── JwtAuthenticationFilter.java
│               │   └── JwtUtil.java
│               │
│               ├── repository/
│               │   ├── CommentRepository.java
│               │   ├── ScheduleRepository.java
│               │   └── UserRepository.java
│               │
│               └── service/
│                   ├── CommentService.java
│                   ├── ScheduleService.java
│                   ├── UserService.java
│                   └── WeatherService.java
│
├── resources/
│   ├── static/
│   ├── templates/
│   └── application.yml
│
└── test/
    └── java/
        └── com.webstrdy00.upgrade_schedule/
            └── UpgradeScheduleApplicationTests.java

├── .gitignore
├── build.gradle
├── gradlew
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle

```
