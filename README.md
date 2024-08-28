# Upgrade Schedule Project
## 📝 프로젝트 소개

주요 기능:
- 사용자 관리 (회원가입, 로그인, 조회, 수정, 삭제, 일정 할당, 일정 삭제)
- 일정 관리 (생성, 조회, 수정, 삭제, 사용자 할당, 사용자 삭제)
- 댓글 시스템 (생성, 조회, 수정, 삭제)
- 날씨 정보 연동

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

## API 명세

### Schedule
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 일정 작성      | Post  |/api/schedules            |Body  | 등록정보
| 선택한 일정 조회 | Get   |/api/schedules/{scheduleId}|scheduleId |단건 응답 정보
| 일정 목록 조회  | Get   |/api/schedules/?page=?,size=?|Param |다건 응답 정보
| 일정에 할당된 유저 조회  | Get   |/api/schedules/{scheduleId}/users|scheduleId |단건 응답 정보
| 선택한 일정 수정 | Put   |/api/schedule/{scheduleId}|scheduleId, Body  |수정 정보
| 일정 삭제  |Delete   |/api/schedules/{scheduleId}|schedueId |-
| 일정에 유저 할당  |Put   |/api/schedules/{scheduleId}/assign?userId=?|schedulId, userId|등록정보
| 일정에 유저 삭제  |Delete   |/api/schedules/{scheduleId}/users/{userId}|scheduleId, userId|삭제정보


### Comment 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 댓글 작성      | Post  |/api/schedules/{scheduleId}/comments|scheduleId, body  | 등록정보
| 선택한 댓글 조회 | Get   |/api/schedules/{scheduleId}/comments/{commentId} |scheduleId, commentId |단건 응답 정보
| 댓글 목록 조회  | Get   |/api/schedules/{scheduleId}/comments|scheduleId   |다건 응답 정보
| 선택한 댓글 수정 | Put   |/api/schedules/{scheduleId}/comments/{commentId}|scheduleId, commentId, Body  |수정 정보
| 선택한 댓글 삭제 | Delete   |/api/schedules/{scheduleId}/comments/{commentId} |scheduleId, commentId  |-

### User 
| 기능          | Method | URL                    | Request| Response|
| ----         |:----:  |:----:                  |:----:|:----:|
| 유저 회원 가입  | Post  |/api/users/signup          |Body  | 등록정보
| 로그인        | Post  |/api/users/login               |Body  | JwtToken반환
| 유저에게 일정 할당 | Post  |/api/users//{userId}/schedules?scheduleId=? |userId, Param | 수정 정보
| 선택한 유저 조회 | Get   |/api/users/{userId} |userId |단건 응답 정보
| 유저 목록 조회  | Get   |/api/users                   |-   |다건 응답 정보
| 선택한 유저 수정 | Put   |/api/users/{user_id}|userId, Body  |수정 정보
| 선택한 유저 삭제 | Delete   |/api/users/{user_id} |userId  |-
| 유저에 일정 삭제 | Delete   |/api/users/{user_id}/schedules/{scheduleId} |userId, scheduleId  |수정 정보


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
