package com.webstrdy00.upgrade_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSchedule> userScheduleList = new ArrayList<>();

    public void addSchedule(Schedule schedule) {
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setSchedule(schedule);
        userSchedule.setUser(this);
        this.userScheduleList.add(userSchedule);
        schedule.getUserScheduleList().add(userSchedule);
    }
}
