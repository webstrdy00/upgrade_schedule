package com.webstrdy00.upgrade_schedule.dto.scheduleDto;

import com.webstrdy00.upgrade_schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String username;
    private String title;
    private String content;

    public boolean hasUsername(){
        return username != null;
    }

    public boolean hasTitle(){
        return title != null;
    }

    public boolean hasContent(){
        return content != null;
    }

    public Schedule toEntity(){
        Schedule schedule = new Schedule();
        schedule.setUsername(this.username);
        schedule.setTitle(this.title);
        schedule.setContent(this.content);
        return schedule;
    }
}
