package com.webstrdy00.upgrade_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "schedules")
public class Schedule extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSchedule> userScheduleList = new ArrayList<>();


    public void addComment(Comment comment){
        comments.add(comment);
        comment.setSchedule(this);
    }

    public void removeComment(Comment comment){
        comments.remove(comment);
        comment.setSchedule(null);
    }

    public void addUser(User user) {
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setSchedule(this);
        userSchedule.setUser(user);
        this.userScheduleList.add(userSchedule);
        user.getUserScheduleList().add(userSchedule);
    }
}
